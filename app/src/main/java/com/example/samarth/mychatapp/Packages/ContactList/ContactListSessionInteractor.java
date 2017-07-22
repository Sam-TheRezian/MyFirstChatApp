package com.example.samarth.mychatapp.Packages.ContactList;

public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
