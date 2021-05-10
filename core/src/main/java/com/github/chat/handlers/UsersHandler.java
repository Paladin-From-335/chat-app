package com.github.chat.handlers;

import com.github.chat.controllers.UsersController;
import com.github.chat.dto.UserAuthDto;
import com.github.chat.dto.UserRegDto;
import com.github.chat.exceptions.BadRequest;
import com.github.chat.exceptions.NotFound;
import com.github.chat.utils.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class UsersHandler extends HttpServlet {

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

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        ServletOutputStream out = resp.getOutputStream();
//
//        Token result = usersController.auth(new UserAuthDto());
//        String str = JsonHandler.toJson(result).orElseThrow(BadRequest::new);
//
//        out.write(str.getBytes());
//        out.flush();
//        out.close();
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!"application/json".equalsIgnoreCase(req.getHeader("Content-Type"))) {
            resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Invalid content type!");
        } else {
            String url = req.getRequestURI();
            String body = req.getReader().lines().collect(Collectors.joining());

            if (url.equals("/auth")) {
                System.out.println("Body" + body);
                ServletOutputStream out = resp.getOutputStream();
                UserAuthDto payload = JsonHelper.fromJson(body, UserAuthDto.class).orElseThrow(BadRequest::new);
                String result = this.usersController.auth(payload);
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write(result.getBytes());
                out.flush();
                out.close();
            }
            if (url.equals("/reg")) {
                System.out.println("Body" + body);
                UserRegDto payload = JsonHelper.fromJson(body, UserRegDto.class).orElseThrow(BadRequest::new);
                this.usersController.reg(payload);
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            }

        }
    }
}
