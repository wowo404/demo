package org.liu.websocket.handler;

import lombok.extern.slf4j.Slf4j;
import org.liu.websocket.constants.Constants;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CustomWebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> users = new HashMap<>();

    private String userName = "";

    // 连接成功时候，会触发页面上onopen方法
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.debug("链接成功......");
        userName = (String) webSocketSession.getAttributes().get(Constants.SESSION_KEY_USERNAME);
        log.debug("第一次连接获取的username:" + userName);
        users.put(userName, webSocketSession);
        // 查询未读消息
        int count = 5;
        webSocketSession.sendMessage(new TextMessage("您有" + count + "条未读消息"));
    }

    // 接受消息处理消息,js调用websocket.send时候，会调用该方法
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if (message.getPayload().startsWith("#anyone#")) { //单发某人
            sendMessageToUser((String) session.getAttributes().get(Constants.SESSION_KEY_USERNAME), new TextMessage("服务器单发：" + message.getPayload()));
        } else if (message.getPayload().startsWith("#everyone#")) {
            sendMessageToUsers(new TextMessage("服务器群发：" + message.getPayload()));
        } else {
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        if (webSocketSession.isOpen()) {
            webSocketSession.close();
        }
        log.debug("链接出错，关闭链接......");
        String userName = (String) webSocketSession.getAttributes().get(Constants.SESSION_KEY_USERNAME);
        users.remove(userName);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.debug("链接关闭......" + closeStatus.toString());
        String userName = (String) webSocketSession.getAttributes().get(Constants.SESSION_KEY_USERNAME);
        users.remove(userName);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给某个用户发送消息，模拟给admin发信息
     */
    public void sendMessageToUser(String userName, TextMessage message) throws IOException {
        WebSocketSession webSocketSession = users.get(userName);
        if (null == webSocketSession) {
            log.error("缓存中不存在当前用户的信息，用户名：" + userName);
            return;
        }
        if (webSocketSession.isOpen()) {
            webSocketSession.sendMessage(message);
        }
    }


    /**
     * 给所有在线用户发送消息
     */
    public void sendMessageToUsers(TextMessage message) throws IOException {
        for (Map.Entry<String, WebSocketSession> entry : users.entrySet()) {
            WebSocketSession webSocketSession = entry.getValue();
            if (null == webSocketSession) {
                log.error("缓存中不存在当前用户的信息，用户名：" + userName);
                return;
            }
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(message);
            }
        }
    }


}
