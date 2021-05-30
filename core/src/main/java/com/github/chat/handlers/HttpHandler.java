package com.github.chat.handlers;

import com.github.chat.controllers.IUsersController;
import com.github.chat.dto.ForgotDto;
import com.github.chat.dto.RoomCreateDto;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.entity.Room;
import com.github.chat.exceptions.*;
import com.github.chat.payload.PrivateToken;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.PrivateTokenProvider;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HttpHandler extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(HttpHandler.class);

    private final IUsersController usersController;

    private boolean isRightSecretCode = false;

    private Map<String, String> secretCodeMap;

    public HttpHandler(IUsersController usersController) {
        this.usersController = usersController;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (BadRequest e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid body!");
        } catch (NotFound e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found!");
        }
    }

    @Override
    public void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setStatus(204);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {
        ServletOutputStream out = resp.getOutputStream();
//        String result = Optional.of(this.usersController.authorize(new UserAuthDto())).orElseThrow(BadRequest::new);
//        out.write(result.getBytes());
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        String body = req.getReader().lines().collect(Collectors.joining());
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type!");
        } else {
            String url = req.getRequestURI();
            try (ServletOutputStream out = resp.getOutputStream()) {
                switch (url) {
                    case "/login/auth":
                        UserAuthDto userAuthDto = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
                        if (userAuthDto == null) {
                            throw new NullPointerException();
                        }
                        String result = Optional.of(this.usersController.authorize(userAuthDto)).orElseThrow(BadRequest::new);
                        resp.setContentType("application/json");
                        resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                        out.write(result.getBytes());
                        break;
                    case "/login/registration":
                        UserRegDto regDto = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                        if (regDto == null) {
                            throw new BadRequest();
                        }
                        this.usersController.registration(regDto);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "/login/recovery":
                        ForgotDto forgotDto = JsonHelper.fromJson(body, ForgotDto.class).orElseThrow(BadRequest::new);
                        if (forgotDto == null) {
                            throw new BadRequest();
                        }
                        this.secretCodeMap.put(this.usersController.forgotReq(forgotDto), forgotDto.getEmail());
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "/login/recovery/code":
                        forgotDto = JsonHelper.fromJson(body, ForgotDto.class).orElseThrow(BadRequest::new);
                        System.out.println("Secret code rec: " + this.secretCodeMap.get(forgotDto.getEmail()));
                        if (forgotDto == null) {
                            throw new BadRequest();
                        }
                        if (this.secretCodeMap.get(forgotDto.getEmail())
                                .equals(forgotDto.getSecureCode())) {
                            resp.setStatus(HttpServletResponse.SC_OK);
                        } else {
                            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        }
                    case "/login/recovery/change" :
                        forgotDto = JsonHelper.fromJson(body, ForgotDto.class).orElseThrow(BadRequest::new);
                        if(forgotDto == null) {
                            throw new BadRequest();
                        }
                            this.usersController.updatePassword(forgotDto);
                            resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    default:
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        break;
                }
            } catch (BadRequest | UserAlreadyExistException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                System.out.println(e.getMessage() + " 400");
            } catch (ExpiredTokenException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                System.out.println(e.getMessage() + " 401");
            } catch (ForbiddenException e) {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                System.out.println(e.getMessage() + " 403");
            } catch (ConstraintViolationException e) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                System.out.println(e.getMessage() + " 409");
            } catch (Throwable e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                System.out.println(e.getMessage() + " 500");
            }

        }
    }


}
