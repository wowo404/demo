package org.liu.websocket.config;

import org.liu.websocket.handler.CustomWebSocketHandler;
import org.liu.websocket.interceptor.CustomHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class SpringWebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private CustomWebSocketHandler customWebSocketHandler;
    @Autowired
    private CustomHandshakeInterceptor customHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(customWebSocketHandler, "/websocket/socketServer")
                .addInterceptors(customHandshakeInterceptor).setAllowedOrigins("*");

        registry.addHandler(customWebSocketHandler, "/sockjs/socketServer").setAllowedOrigins("http://127.0.0.1:8080")
                .addInterceptors(customHandshakeInterceptor).withSockJS();
    }

}
