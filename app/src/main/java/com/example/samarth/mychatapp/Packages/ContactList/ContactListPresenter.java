package com.example.samarth.mychatapp.Packages.ContactList;

import com.example.samarth.mychatapp.Packages.ContactList.Events.ContactListEvent;

public interface ContactListPresenter {
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);

}
