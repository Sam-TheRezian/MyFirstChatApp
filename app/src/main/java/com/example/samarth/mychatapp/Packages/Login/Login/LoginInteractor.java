package com.example.samarth.mychatapp.Packages.Login.Login;

public interface LoginInteractor {
    void checkAlreadyAuthenticated();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
