package com.github.chat.config;

import com.github.chat.handlers.WebsocketHandler;
import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;

public class WSHandlerConfig {

    private static final WSConnectionPool wsConnectionPool = new WSConnectionPool();

    private static final Broker broker = new Broker();

    private static final WebsocketHandler websocketHandler = new WebsocketHandler(getWsConnectionPool(), getBroker());

    public static WSConnectionPool getWsConnectionPool() {
        return wsConnectionPool;
    }

    public static Broker getBroker() {
        return broker;
    }

    public static WebsocketHandler getWebsocketHandler() {
        return websocketHandler;
    }
}
