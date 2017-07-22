package com.example.samarth.mychatapp.Packages.Chat;

import com.example.samarth.mychatapp.Packages.Chat.Events.ChatEvent;

public interface ChatPresenter {
    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}

