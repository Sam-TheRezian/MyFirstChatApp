package com.example.samarth.mychatapp.Packages.AddContact;

import com.example.samarth.mychatapp.Packages.AddContact.Events.AddContactEvent;
import com.example.samarth.mychatapp.Packages.ContactList.Entities.User;
import com.example.samarth.mychatapp.Packages.Domain.FirebaseHelper;
import com.example.samarth.mychatapp.Packages.Lib.EventBus;
import com.example.samarth.mychatapp.Packages.Lib.GreenRobotEventBus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class AddContactRepositoryImpl implements AddContactRepository {
    @Override
    public void addContact(final String email) {
        final String key = email.replace(".", "_");

        final FirebaseHelper helper = FirebaseHelper.getInstance();
        final DatabaseReference userReference = helper.getUserReference(email);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                AddContactEvent event = new AddContactEvent();
                if(user != null){
                    boolean online = user.isOnline();
                    FirebaseHelper helper = FirebaseHelper.getInstance();

                    DatabaseReference userContactsReference = helper.getMyContactsReference();
                    userContactsReference.child(key).setValue(online);

                    String currentUserEmailkey = helper.getAuthUserEmail();
                    currentUserEmailkey = currentUserEmailkey.replace(".", "_");
                    DatabaseReference reverseUserContactsReference = helper.getContactsReference(email);
                    reverseUserContactsReference.child(currentUserEmailkey).setValue(true);
                }
                else
                    event.setError(true);
                EventBus eventBus = GreenRobotEventBus.getInstance();
                eventBus.post(event);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {}
        });
    }
}
