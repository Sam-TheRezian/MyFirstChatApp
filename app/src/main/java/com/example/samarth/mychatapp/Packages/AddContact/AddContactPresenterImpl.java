package com.example.samarth.mychatapp.Packages.AddContact;

import com.example.samarth.mychatapp.Packages.AddContact.Events.AddContactEvent;
import com.example.samarth.mychatapp.Packages.AddContact.UI.AddContactView;
import com.example.samarth.mychatapp.Packages.Lib.EventBus;
import com.example.samarth.mychatapp.Packages.Lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class AddContactPresenterImpl implements AddContactPresenter {

    EventBus eventBus;
    AddContactView addContactView;
    AddContactInteractor addContactInteractor;

    public AddContactPresenterImpl(AddContactView addContactView) {
        this.addContactView = addContactView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.addContactInteractor = new AddContactInteractorImpl(new AddContactRepositoryImpl());
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        addContactView = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        addContactView.hideInput();
        addContactView.showProgress();
        this.addContactInteractor.addContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if(addContactView != null){
            addContactView.hideProgress();
            addContactView.showInput();
            if(event.isError())
                addContactView.contactNotAdded();
            else
                addContactView.contactAdded();
        }
    }
}
