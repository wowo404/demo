package org.liu.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.liu.websocket.constants.Constants;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Component
public class CustomHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    // 初次握手访问前
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            HttpServletRequest servletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
            String sessionId = servletRequest.getSession().getId();//获取浏览器的sessionid
            log.debug("sessionId:" + sessionId);
            String username = (String) servletRequest.getSession().getAttribute(Constants.SESSION_KEY_USERNAME);
            log.debug("获取session里面的name-------------------" + username);
            map.put(Constants.SESSION_KEY_USERNAME, username);
        }
        return true;
    }

    // 初次握手访问后
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
