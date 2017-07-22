package com.example.samarth.mychatapp.Packages.ContactList;

public interface ContactListInteractor {
    void subscribeForContactEvents();
    void unsubscribeForContactEvents();
    void destroyContactListListener();
    void removeContact(String email);
}
