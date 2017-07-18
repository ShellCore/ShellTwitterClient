package com.shellcore.android.shelltwitter.lib.di;

import android.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.shellcore.android.shelltwitter.lib.GlideImageLoader;
import com.shellcore.android.shelltwitter.lib.GreenRobotEventBus;
import com.shellcore.android.shelltwitter.lib.base.EventBus;
import com.shellcore.android.shelltwitter.lib.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 17/07/2017.
 */
@Module
public class LibsModule {

    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return new GlideImageLoader(requestManager);
    }
    
    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment) {
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return fragment;
    }
    
    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
