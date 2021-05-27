package com.github.chat.config;

import com.github.chat.handlers.HttpHandler;

public class HttpHandlerConfig {

    private static final HttpHandler handler = new HttpHandler(ControllerConfig.getUserController());

    public static HttpHandler getHandler() {
        return handler;
    }

    }
