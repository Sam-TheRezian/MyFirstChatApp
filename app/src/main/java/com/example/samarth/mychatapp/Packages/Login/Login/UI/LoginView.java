package com.example.samarth.mychatapp.Packages.Login.Login.UI;

public interface LoginView {
    void enableInputs();
    void disableInputs();

    void showProgress();
    void hideProgress();

    void handleSignUp();
    void handleSignIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);
}
