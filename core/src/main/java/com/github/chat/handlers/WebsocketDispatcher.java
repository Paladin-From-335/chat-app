package com.github.chat.handlers;

import com.github.chat.payload.Envelope;
import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

public class WebsocketDispatcher {

    private List<IMessageListener> listeners = new ArrayList<>();

    public void subscribe(IMessageListener ml) {
        this.listeners.add(ml);
    }

    public void notifyAllSubs(Session session, Envelope env) {
        this.listeners.forEach(l -> l.onMessage(session, env));
    }
}
