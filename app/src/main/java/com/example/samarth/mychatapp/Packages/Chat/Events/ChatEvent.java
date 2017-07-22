package com.example.samarth.mychatapp.Packages.Chat.Events;

import com.example.samarth.mychatapp.Packages.Chat.Entities.ChatMessage;

public class ChatEvent {
    ChatMessage msg;

    public ChatEvent(ChatMessage msg) {
        this.msg = msg;
    }

    public ChatMessage getMessage() {
        return msg;
    }
}
