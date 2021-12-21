package com.haminou.m.chetty.model

import com.google.gson.Gson


data class Message(var type: MessageType?, var content: String?, var sender: String?) {
    
    enum class MessageType {
        CHAT, JOIN, LEAVE
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}