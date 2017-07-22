package com.example.samarth.mychatapp.Packages.AddContact;

import com.example.samarth.mychatapp.Packages.AddContact.Events.AddContactEvent;

public interface AddContactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
