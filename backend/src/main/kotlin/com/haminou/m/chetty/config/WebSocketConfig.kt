package com.haminou.m.chetty.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    //Websocket endpoint which clients connects to
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        //SockJS: fallback options for browsers that don’t support websocket.
        registry.addEndpoint("/ws")
            .setAllowedOrigins("http://localhost:8080")
            .withSockJS()
    }

    // Broker to route messages from one client to another.
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        // Enables simple in-memory message broker
        // destination “/app” will be routed to message-handling methods
        // destination “/topic” will be routed to message broker
        config.setApplicationDestinationPrefixes("/app")
        config.enableSimpleBroker("/topic")
    }
}