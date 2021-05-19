package com.github.chat.config;

import com.github.chat.controllers.UsersController;
import com.github.chat.handlers.UsersHandler;
//import com.github.chat.handlers.WebsocketHandler;
import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;

public class HandlerConfig {

    private static final UsersHandler handler = new UsersHandler(ControllerConfig.getUserController());

    public static UsersHandler getHandler() {
        return handler;
    }

    //    public static UsersHandler usersHandlers() {
//        return new UsersHandler(ControllerConfig.usersController());
//    }

//    public static WebsocketHandler websocketHandler() {
//        return new WebsocketHandler(new WSConnectionPool(), new Broker());
//    }
}
