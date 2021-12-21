package com.haminou.m.chetty.config

import com.haminou.m.chetty.model.Message
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.web.socket.messaging.SessionConnectEvent
import org.springframework.web.socket.messaging.SessionConnectedEvent


class WebSocketEventListener {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private val messagingTemplate: SimpMessageSendingOperations? = null

    @EventListener
    fun handleWebSocketConnectListener(event: SessionConnectEvent?) {
        logger.info("New web socket connection")
    }

    @EventListener
    fun handleWebSocketDisconnectListener(event: SessionConnectedEvent?) {
        val headerAccessor = StompHeaderAccessor.wrap(event!!.message)
        val username = headerAccessor.sessionAttributes!!["username"] as String?
        if (username != null) {
            logger.info("User:$username Disconnected  ")
            val message = Message(Message.MessageType.LEAVE, "", username)
            messagingTemplate!!.convertAndSend("/topic/users", message);
        }
    }

}