package com.github.chat.config;

import com.github.chat.handlers.UsersHandler;
import com.github.chat.handlers.WebsocketHandler;
import com.github.chat.utils.ServerRunner;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.websocket.server.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;

//import com.github.chat.handlers.WebsocketHandler;

public class ServerConfig {

    private static final Logger log = LoggerFactory.getLogger(ServerConfig.class);


    public static ServerRunner start() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8081";
        }
        tomcat.setPort(Integer.parseInt(webPort));
        Context ctx = tomcat.addWebapp("", new File(".").getAbsolutePath());
        tomcat.addServlet("", UsersHandler.class.getName(), HandlerConfig.getHandler());
        ctx.addServletMappingDecoded("/*", UsersHandler.class.getName());
        tomcat.start();
        tomcat.getServer().await();
        return new ServerRunner(tomcat, ctx, List.of(chatWebsocketHandler));
    }

    private static final Consumer<Context> chatWebsocketHandler = ctx -> {
        WebsocketHandler websocketHandler = HandlerConfig.websocketHandler();
        ServerContainer scon = (ServerContainer) ctx.getServletContext().getAttribute(ServerContainer.class.getName());
        try {
            scon.addEndpoint(ServerEndpointConfig.Builder.create(WebsocketHandler.class, "/chat")
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
                            return (T) websocketHandler;
                        }
                    }).build());
        } catch (DeploymentException e) {
            log.warn(e.getMessage());
        }
    };
}
