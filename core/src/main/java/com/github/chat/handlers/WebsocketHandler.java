package com.github.chat.handlers;

import com.github.chat.config.ControllerConfig;
import com.github.chat.controllers.IMessageController;
import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.PrivateToken;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.PrivateTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class WebsocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebsocketHandler.class);

    private final Broker broker;

    private final WSConnectionPool wsConnectionPool;

    private final IMessageController messageController = ControllerConfig.getMessageController();

    public WebsocketHandler(WSConnectionPool wsConnectionPool, Broker broker) {
        this.wsConnectionPool = wsConnectionPool;
        this.broker = broker;
    }

    @OnMessage
    public void messages(Session session, String payload) {
        try {
            Envelope envelope = JsonHelper.fromJson(payload, Envelope.class).orElseThrow();
            switch (envelope.getTopic()) {
                case AUTHORIZATION:
                    PrivateToken result = PrivateTokenProvider.decode(envelope.getPayload());
                    PrivateTokenProvider.checkToken(result);
                    String nickname = result.getNickname();
                    this.wsConnectionPool.addSession(nickname, session);
                    broker.broadcast(wsConnectionPool.getSessions(), envelope);
                    break;
                case MESSAGES:
                    this.broker.broadcast(this.wsConnectionPool.getSessions(), envelope);
//                    messageController.saveMessage(result.getLogin(), envelope.getPayload());
                    break;
//                case DISCONNECT:
//                    wsConnectionPool.removeSession(nickname);
//                    wsConnectionPool.getSession(nickname).close();
//                    break;
                default:
                    break;
            }
        } catch (Throwable e) {
            log.warn("Enter {}", e.getMessage());
        }
    }
}
