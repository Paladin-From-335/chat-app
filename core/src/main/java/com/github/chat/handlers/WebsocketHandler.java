package com.github.chat.handlers;

import com.github.chat.config.ControllerConfig;
import com.github.chat.config.ServiceConfig;
import com.github.chat.controllers.IMessageController;
import com.github.chat.network.Broker;
import com.github.chat.network.WSConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.PrivateToken;
import com.github.chat.service.IMessageService;
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

    private final IMessageService messageService = ServiceConfig.getMessageService();

    private String nickname = "";

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
                    String[] token = payload.split("\\.");
                    PrivateToken result = PrivateTokenProvider.decode(token[1]);
                    PrivateTokenProvider.checkToken(result);
                    System.out.println(result);
                    nickname = result.getNickname();
                    System.out.println(nickname);
                    this.wsConnectionPool.addSession(nickname, session);
                    broker.broadcast(wsConnectionPool.getSessions(), envelope);
                    break;
                case MESSAGES:
                    this.broker.broadcast(this.wsConnectionPool.getSessions(), envelope);
                    messageService.save(nickname, envelope.getPayload());
                    break;
                case DISCONNECT:
                    PrivateToken discoResult = PrivateTokenProvider.decode(envelope.getPayload());
                    PrivateTokenProvider.checkToken(discoResult);
                    nickname = discoResult.getNickname();
                    wsConnectionPool.removeSession(nickname);
                    wsConnectionPool.getSession(nickname).close();
                    break;
                default:
                    break;
            }
        } catch (Throwable e) {
            log.warn("Enter {}", e.getMessage());
        }
    }
}
