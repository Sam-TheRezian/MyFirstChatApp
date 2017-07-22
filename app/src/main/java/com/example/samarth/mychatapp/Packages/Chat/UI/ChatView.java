package com.example.samarth.mychatapp.Packages.Chat.UI;

import com.example.samarth.mychatapp.Packages.Chat.Entities.ChatMessage;

public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
