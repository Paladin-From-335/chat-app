package com.github.chat.handlers.impl;

import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.PrivateToken;
import com.github.chat.payload.Topic;
import com.github.chat.handlers.IMessageListener;
import com.github.chat.utils.TokenProvider;

import javax.websocket.Session;

public class AuthHandler implements IMessageListener {

    private final WSConnectionPool wsConnectionPool;

    private final Broker broker;

    public AuthHandler(WSConnectionPool wsConnectionPool, Broker broker) {
        this.wsConnectionPool = wsConnectionPool;
        this.broker = broker;
    }

    @Override
    public void onMessage(Session session, Envelope env) {
        if(Topic.auth == env.getTopic()) {
            PrivateToken result = TokenProvider.decode(env.getPayload());
            Long id = result.getId();
            this.wsConnectionPool.addSession(id, session);
        }

    }
}
