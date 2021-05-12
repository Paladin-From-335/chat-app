package com.github.chat.config;

import com.github.chat.handlers.UsersHandler;
import com.github.chat.handlers.WebsocketHandler;
import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;

public class HandlerConfig {

    public static UsersHandler usersController(){
        return new UsersHandler(ControllerConfig.usersController());
    }

    public static WebsocketHandler websocketHandler() {
        return new WebsocketHandler(new WSConnectionPool(), new Broker());
    }
}
