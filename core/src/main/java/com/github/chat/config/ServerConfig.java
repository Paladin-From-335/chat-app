package com.github.chat.config;

import com.github.chat.controllers.UsersController;
import com.github.chat.handlers.UsersHandler;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class ServerConfig {

    public static Tomcat tomcat() throws ServletException {
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.valueOf(webPort));
        Context ctx =  tomcat.addWebapp("/", new File(".").getAbsolutePath());

        tomcat.addServlet("", "UsersHandler", HandlerConfig.usersController());
        ctx.addServletMappingDecoded("/*", "UsersHandler");
        return tomcat;
    }

}
