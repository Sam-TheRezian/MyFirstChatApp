package com.example.samarth.mychatapp.Packages.ContactList.UI;

import com.example.samarth.mychatapp.Packages.ContactList.Entities.User;

public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
