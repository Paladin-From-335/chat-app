package com.github.chat.handlers;

import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.Token;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.Session;

public class WebsocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebsocketHandler.class);

    private final WSConnectionPool wsConnectionPool;

    private final Broker broker;

    public WebsocketHandler(WSConnectionPool wsConnectionPool, Broker broker) {
        this.wsConnectionPool = wsConnectionPool;
        this.broker = broker;

    }

//    @OnMessage
//    public void messages(Session session, String payload) {
//        try {
//            Envelope env = JsonHelper.fromJson(payload, Envelope.class).orElseThrow();
//            log.info("Enter");
//            this.websocketDispatcher.notifyAllSubs(session, env);
//        } catch (Throwable e){
//            log.warn("Enter {}", e.getMessage());
//        }
//    }

    @OnMessage
    public void messages(Session session, String payload) {
        try {
            Envelope env = JsonHelper.fromJson(payload, Envelope.class).orElseThrow();
            switch (env.getTopic()) {
                case auth:
                    Token result = TokenProvider.decoding(env.getPayload());
                    Long id = result.getId();
                    this.wsConnectionPool.addSession(id, session);
                    break;
                case messages:
                    this.broker.broadcast(this.wsConnectionPool.getSessions(), env);
                    break;
                case disconnect:
                    break;
                default:
            }
        } catch (Throwable e) {

            log.warn("Enter {}", e.getMessage());
        }
    }
}
