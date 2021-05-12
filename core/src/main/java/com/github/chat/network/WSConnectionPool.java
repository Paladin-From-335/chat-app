package com.github.chat.network;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WSConnectionPool {

    private ConcurrentHashMap<Long, Session> sessions = new ConcurrentHashMap<>();

    public void addSession(long id, Session session) {
        this.sessions.put(id, session);
    }

    public void removeSession(long id, Session session) {
        this.sessions.remove(id);
    }

    public Session getSession(long id) {
        return this.sessions.get(id);
    }

    public List<Session> getSessions() {
        return new LinkedList<>(this.sessions.values());
    }

}
