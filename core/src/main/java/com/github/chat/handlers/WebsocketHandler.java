package com.github.chat.handlers;

import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;
import com.github.chat.payload.EnvelopeWS;
import com.github.chat.payload.PrivateToken;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import static com.github.chat.payload.Topic.*;

@ServerEndpoint("/chat")
public class WebsocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebsocketHandler.class);

    private final WSConnectionPool wsConnectionPool;

    private final Broker broker;

    public WebsocketHandler(WSConnectionPool wsConnectionPool, Broker broker) {
        this.wsConnectionPool = wsConnectionPool;
        this.broker = broker;

    }

    @OnMessage
    public void messages(Session session, String payload) {
        try {
            EnvelopeWS envWS = JsonHelper.fromJson(payload, EnvelopeWS.class).orElseThrow();
            switch (envWS.getTopic()) {
                case authorization:
                    PrivateToken result = TokenProvider.decode(envWS.getPayload());
                    String login = result.getLogin();
                    this.wsConnectionPool.addSession(login, session);
                    break;
                case messages:
                    this.broker.broadcast(this.wsConnectionPool.getSessions(), envWS);
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
