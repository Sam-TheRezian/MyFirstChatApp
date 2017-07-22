package com.example.samarth.mychatapp.Packages.Chat;

import com.example.samarth.mychatapp.Packages.Chat.Entities.ChatMessage;
import com.example.samarth.mychatapp.Packages.Chat.Events.ChatEvent;
import com.example.samarth.mychatapp.Packages.Chat.UI.ChatView;
import com.example.samarth.mychatapp.Packages.ContactList.Entities.User;
import com.example.samarth.mychatapp.Packages.Lib.EventBus;
import com.example.samarth.mychatapp.Packages.Lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class ChatPresenterImpl implements ChatPresenter {
    EventBus eventBus;
    ChatView chatView;
    ChatInteractor chatInteractor;
    ChatSessionInteractor chatSessionInteractor;

    public ChatPresenterImpl(ChatView chatView) {
        this.chatView = chatView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.chatInteractor = new ChatInteractorImpl();
        this.chatSessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onPause() {
        chatInteractor.unSubscribeForChatUpdates();
        chatSessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onResume() {
        chatInteractor.subscribeForChatUpdates();
        chatSessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyChatListener();
        chatView = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        this.chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if(chatView != null){
            ChatMessage msg = event.getMessage();
            chatView.onMessageReceived(msg);
        }
    }
}
