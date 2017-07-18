package com.shellcore.android.shelltwitter.images.di;

import com.shellcore.android.shelltwitter.images.ImagesPresenter;
import com.shellcore.android.shelltwitter.images.ui.ImagesFragment;
import com.shellcore.android.shelltwitter.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 17/07/2017.
 */
@Singleton
@Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
}
