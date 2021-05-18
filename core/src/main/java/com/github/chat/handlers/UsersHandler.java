package com.github.chat.handlers;

import com.github.chat.controllers.UsersController;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.exceptions.BadRequest;
import com.github.chat.exceptions.ExpiredTokenException;
import com.github.chat.exceptions.ForbiddenException;
import com.github.chat.exceptions.NotFound;
import com.github.chat.utils.JsonHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsersHandler extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(UsersHandler.class);

    private final UsersController usersController;

    public UsersHandler(UsersController usersController) {
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
        String result = Optional.of(this.usersController.authorize(new UserAuthDto())).orElseThrow(BadRequest::new);
        out.write(result.getBytes());
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
                            throw new BadRequest();
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
                        System.out.println(body);
                        this.usersController.registration(regDto);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    default:
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        break;
                }
            } catch (BadRequest e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (ForbiddenException e) {
                resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            } catch (ConstraintViolationException e) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            } catch (ExpiredTokenException e) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } catch (Throwable e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }
}
