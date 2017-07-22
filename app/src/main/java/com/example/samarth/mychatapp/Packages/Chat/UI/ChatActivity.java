package com.example.samarth.mychatapp.Packages.Chat.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.example.samarth.mychatapp.Packages.AndroidChatApplication;
import com.example.samarth.mychatapp.Packages.Chat.Adapters.ChatAdapter;
import com.example.samarth.mychatapp.Packages.Chat.ChatPresenter;
import com.example.samarth.mychatapp.Packages.Chat.ChatPresenterImpl;
import com.example.samarth.mychatapp.Packages.Chat.Entities.ChatMessage;
import com.example.samarth.mychatapp.Packages.Domain.AvatarHelper;
import com.example.samarth.mychatapp.Packages.Lib.ImageLoader;
import com.example.samarth.mychatapp.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.imgAvatar) CircleImageView imgAvatar;
    @BindView(R.id.txtUser) TextView txtUser;
    @BindView(R.id.txtStatus) TextView txtStatus;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.msgRecyclerView) RecyclerView msgRecyclerView;
    @BindView(R.id.editTxtMsg) EditText inputMessage;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    private ChatAdapter adapter;
    private ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        chatPresenter = new ChatPresenterImpl(this);
        chatPresenter.onCreate();

        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        setToolbarData(intent);

        setupAdapter();
        setupRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        chatPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        chatPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        chatPresenter.onDestroy();
        super.onDestroy();
    }

    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }

    private void setupRecyclerView() {
        msgRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        msgRecyclerView.setAdapter(adapter);
    }

    private void setToolbarData(Intent intent) {
        String recipient = intent.getStringExtra(EMAIL_KEY);
        chatPresenter.setChatRecipient(recipient);

        boolean online = intent.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        AndroidChatApplication app = (AndroidChatApplication) getApplication();
        ImageLoader imageLoader = app.getImageLoader();
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));
    }

    @Override
    @OnClick(R.id.btnSendMsg)
    public void sendMessage() {
        chatPresenter.sendMessage(inputMessage.getText().toString());
        inputMessage.setText("");
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        msgRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }
}
