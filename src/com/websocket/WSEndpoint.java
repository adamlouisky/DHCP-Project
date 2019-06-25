package com.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;

@ServerEndpoint("/eventendpoint")
public class WSEndpoint {
    private static Logger logger= LoggerFactory.getLogger(WSEndpoint.class);
    private static Session s;
    private static Set<Session> ss;
    @OnMessage
    public String onMessge(String message, Session session){

        logger.debug("Received: {}", message);

//        try {
//           // session.getBasicRemote().sendText("Welcome!!!!!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @OnOpen
    public void onOpen(Session session){
        logger.debug("Create a New Session");
        s=session;
        ss=session.getOpenSessions();
    }

    @OnClose
    public void onClose(Session session){
        logger.debug("Close a Session");
    }

    @OnError
    public void onError(Throwable t){
        logger.debug("Session Error");
    }

    public static void sendMsg(String msg){
        try {
            s.getBasicRemote().sendText(msg);

            for (Session session: ss){
                session.getBasicRemote().sendText(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
