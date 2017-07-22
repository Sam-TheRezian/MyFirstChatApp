package com.example.samarth.mychatapp.Packages.Chat;

public interface ChatInteractor {
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void destroyChatListener();
    void subscribeForChatUpdates();
    void unSubscribeForChatUpdates();
}
