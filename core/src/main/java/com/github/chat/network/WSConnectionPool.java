package com.github.chat.network;

import javax.websocket.Session;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WSConnectionPool {

    private ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    public void addSession(String login, Session session) {
        this.sessions.put(login, session);
    }

    public void removeSession(String login, Session session) {
        this.sessions.remove(login);
    }

    public Session getSession(String login) {
        return this.sessions.get(login);
    }

    public List<Session> getSessions() {
        return new LinkedList<>(this.sessions.values());
    }

}
