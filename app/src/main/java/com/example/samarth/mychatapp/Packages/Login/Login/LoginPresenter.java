package com.example.samarth.mychatapp.Packages.Login.Login;

import com.example.samarth.mychatapp.Packages.Login.Login.Events.LoginEvent;

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void onEventMainThread(LoginEvent event);
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
}
