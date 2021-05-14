package com.github.chat.handlers;

import com.github.chat.controllers.UsersController;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.exceptions.BadRequest;
import com.github.chat.exceptions.NotFound;
import com.github.chat.service.IUsersService;
import com.github.chat.utils.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
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
        System.out.println("DOOPT");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setStatus(204);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, NullPointerException {
        System.out.println("DOGET");
        ServletOutputStream out = resp.getOutputStream();
        String result = Optional.of(this.usersController.auth(new UserAuthDto())).orElseThrow(BadRequest::new);
        out.write(result.getBytes());
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("DOPIST");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        String body = req.getReader().lines().collect(Collectors.joining());
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type!");
        } else {
            String url = req.getRequestURI();
            System.out.println("Body:\n" + body);
            System.out.println(url);
            if (url.contains("/auth")) {
                System.out.println("If AUTH");
                UserAuthDto payload = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
                String result = Optional.of(this.usersController.auth(payload)).orElseThrow(BadRequest::new);
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                ServletOutputStream out = resp.getOutputStream();
                out.write(result.getBytes());
                out.flush();
                out.close();
                return;
            }
            if (url.contains("/registration")) {
                System.out.println("IF REGISTR");
                log.info(url);
                UserRegDto payload = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                this.usersController.registration(payload);
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            } else {
                log.warn("BADREQUEST");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

        }
    }
}
