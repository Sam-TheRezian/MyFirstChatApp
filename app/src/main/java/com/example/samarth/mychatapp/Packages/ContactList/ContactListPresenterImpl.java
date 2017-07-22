package com.example.samarth.mychatapp.Packages.ContactList;

import com.example.samarth.mychatapp.Packages.ContactList.Entities.User;
import com.example.samarth.mychatapp.Packages.ContactList.Events.ContactListEvent;
import com.example.samarth.mychatapp.Packages.ContactList.UI.ContactListView;
import com.example.samarth.mychatapp.Packages.Lib.EventBus;
import com.example.samarth.mychatapp.Packages.Lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class ContactListPresenterImpl implements ContactListPresenter{

    EventBus eventBus;
    ContactListView contactListView;
    ContactListInteractor contactListInteractor;
    ContactListSessionInteractor contactListSessionInteractor;

    public ContactListPresenterImpl(ContactListView contactListView) {
        this.contactListView = contactListView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.contactListInteractor = new ContactListInteractorImpl();
        this.contactListSessionInteractor = new ContactListSessionInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.unsubscribeForContactEvents();
    }

    @Override
    public void onResume() {
        contactListSessionInteractor.changeConnectionStatus(User.ONLINE);
        contactListInteractor.subscribeForContactEvents();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        contactListInteractor.destroyContactListListener();
        contactListView = null;
    }

    @Override
    public void signOff() {
        contactListSessionInteractor.changeConnectionStatus(User.OFFLINE);
        contactListInteractor.destroyContactListListener();
        contactListInteractor.unsubscribeForContactEvents();
        contactListSessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return contactListSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        contactListInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    public void onContactAdded(User user){
        if(contactListView != null)
            contactListView.onContactAdded(user);

    }

    public void onContactChanged(User user){
        if(contactListView != null)
            contactListView.onContactChanged(user);
    }

    public void onContactRemoved(User user){
        if(contactListView != null)
            contactListView.onContactRemoved(user);
    }
}
