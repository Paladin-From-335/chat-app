package com.github.chat.config;

import com.github.chat.handlers.WebsocketHandler;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.websocket.server.Constants;
import org.apache.tomcat.websocket.server.WsContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.io.File;
import java.util.function.Consumer;

public class ServerConfig {

    private static final Logger log = LoggerFactory.getLogger(ServerConfig.class);

    public static Tomcat tomcat() throws ServletException {
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.parseInt(webPort));
        Context ctx = tomcat.addWebapp("/login", new File(".").getAbsolutePath());
        ctx.addApplicationListener(WsContextListener.class.getName());
        tomcat.addServlet("", "UsersHandler", HandlerConfig.usersController());
        ctx.addServletMappingDecoded("/*", "UsersHandler");
        return tomcat;
    }

    private static Consumer<Context> chatWebsocketHandler = ctx -> {
        WebsocketHandler websocketHandler = HandlerConfig.websocketHandler();
        ServerContainer serverContainer = (ServerContainer) ctx.getServletContext().getAttribute(Constants.SERVER_CONTAINER_SERVLET_CONTEXT_ATTRIBUTE);
        try {
            serverContainer.addEndpoint(ServerEndpointConfig.Builder.create(WebsocketHandler.class, "/chat")
            .configurator(new ServerEndpointConfig.Configurator() {
                @Override
                public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
                    return super.getEndpointInstance(endpointClass);
                }
            }).build());
        } catch (DeploymentException e) {
            log.warn(e.getMessage());
        }
    };
}
