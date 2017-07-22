package com.example.samarth.mychatapp.Packages;

import android.app.Application;

import com.example.samarth.mychatapp.Packages.Lib.GlideImageLoader;
import com.example.samarth.mychatapp.Packages.Lib.ImageLoader;
import com.google.firebase.database.FirebaseDatabase;

public class AndroidChatApplication extends Application {
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
        setupImageLoader();
    }

    private void setupImageLoader() {
        imageLoader = new GlideImageLoader(this);
    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
