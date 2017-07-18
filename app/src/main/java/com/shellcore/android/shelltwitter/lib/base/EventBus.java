package com.shellcore.android.shelltwitter.lib.base;

/**
 * Created by Cesar on 14/07/2017.
 */

public interface EventBus {

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
