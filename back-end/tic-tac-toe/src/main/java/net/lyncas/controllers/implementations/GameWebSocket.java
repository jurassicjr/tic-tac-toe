package net.lyncas.controllers.implementations;

import lombok.Getter;
import net.lyncas.dtos.PlayDTO;
import net.lyncas.enums.EnCommonErrors;
import net.lyncas.services.GameService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@ServerEndpoint("/game/{gameId}")
@ApplicationScoped
public class GameWebSocket {

    public final static Map<String, Session> sessions = new ConcurrentHashMap<>();


    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        sessions.put(username, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        sessions.remove(username);
    }

    @OnError
    public void onError(Session session, @PathParam("username") String username, Throwable throwable) {
        System.out.println("onError> " + username + ": " + throwable);
        sessions.remove(username);
    }
}
