package com.sxt.baiwei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker//开启消息代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * 建立连接点信息
     * 首先需要建立连接才能发送消息
     * 将 "/ws/ep" 注册为一个 STOMP 端点。客户端在订阅或发布消息到目的地路径前，要连接到该端点。
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws/endpointChat").withSockJS();
    }

    /**
     * 配置消息队列
     * 基于内存的STOMP消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue","/topic");
    }
}
