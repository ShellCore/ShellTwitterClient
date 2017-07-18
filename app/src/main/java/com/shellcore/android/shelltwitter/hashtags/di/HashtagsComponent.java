package com.shellcore.android.shelltwitter.hashtags.di;

import com.shellcore.android.shelltwitter.hashtags.HashtagsPresenter;
import com.shellcore.android.shelltwitter.hashtags.ui.HashtagsFragment;
import com.shellcore.android.shelltwitter.lib.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Cesar on 18/07/2017.
 */
@Singleton
@Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
    HashtagsPresenter getPresenter();
}
