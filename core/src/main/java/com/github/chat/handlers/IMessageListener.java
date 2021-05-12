package com.github.chat.handlers;

import com.github.chat.payload.Envelope;

import javax.websocket.Session;

public interface IMessageListener {

    void onMessage(Session session, Envelope env);

}
