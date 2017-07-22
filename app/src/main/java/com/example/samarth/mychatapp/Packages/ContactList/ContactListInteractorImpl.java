package com.example.samarth.mychatapp.Packages.ContactList;

public class ContactListInteractorImpl implements ContactListInteractor{

    ContactListRepositoryImpl contactListRepository;

    public ContactListInteractorImpl() {
        this.contactListRepository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribeForContactEvents() {
        contactListRepository.subscribeForContactListUpdates();
    }

    @Override
    public void unsubscribeForContactEvents() {
        contactListRepository.unSubscribeForContactListUpdates();
    }

    @Override
    public void destroyContactListListener() {
        contactListRepository.destroyContactListListener();
    }

    @Override
    public void removeContact(String email) {
        contactListRepository.removeContact(email);
    }
}
