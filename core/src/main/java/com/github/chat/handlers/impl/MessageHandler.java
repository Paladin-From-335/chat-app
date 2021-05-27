package com.github.chat.handlers.impl;

import com.github.chat.handlers.IMessageListener;
import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.Topic;

import javax.websocket.Session;

public class MessageHandler implements IMessageListener {

    private final WSConnectionPool wsConnectionPool;

    private final Broker broker;

    public MessageHandler(WSConnectionPool wsConnectionPool, Broker broker) {
        this.wsConnectionPool = wsConnectionPool;
        this.broker = broker;
    }

    @Override
    public void onMessage(Session session, Envelope env) {
        if (Topic.AUTHORIZATION == env.getTopic()) {
            broker.broadcast(wsConnectionPool.getSessions(), env);
        }
    }
}
