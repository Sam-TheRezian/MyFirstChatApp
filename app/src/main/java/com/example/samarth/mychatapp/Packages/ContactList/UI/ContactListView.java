package com.example.samarth.mychatapp.Packages.ContactList.UI;

import com.example.samarth.mychatapp.Packages.ContactList.Entities.User;

public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
