package com.haminou.m.chetty.controller

import com.haminou.m.chetty.model.Message
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.annotation.SendToUser
import org.springframework.stereotype.Controller


@Controller
class MessageController {
    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val messagingTemplate: SimpMessageSendingOperations? = null
    private val topic = "/topic/messages";


    @MessageMapping("/chat/{receiver}")
    @SendTo("/topic/messages")
    @SendToUser()
    fun sendMessage(
        @DestinationVariable receiver: String,
        @Payload message: Message?,
    ): Message? {
        //TODO maybe check if receiver exists, send message
        logger.info("Sending message to receiver:" + receiver)
        return message
    }

    @MessageMapping("/chat/add/{user}")
    @SendTo("/topic/users")
    fun addUser(@Payload message: Message?, headerAccessor: SimpMessageHeaderAccessor): Message? {
        //TODO implement add user when DB is setup
        if (message != null) {
            headerAccessor.sessionAttributes!!["username"] = message.sender
        }
        return message;
    }


}