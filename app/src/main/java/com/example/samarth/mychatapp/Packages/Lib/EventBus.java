package com.example.samarth.mychatapp.Packages.Lib;

public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
