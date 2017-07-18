package com.shellcore.android.shelltwitter;

import android.app.Application;
import android.app.Fragment;
import android.util.Log;

import com.shellcore.android.shelltwitter.hashtags.di.DaggerHashtagsComponent;
import com.shellcore.android.shelltwitter.hashtags.di.HashtagsComponent;
import com.shellcore.android.shelltwitter.hashtags.di.HashtagsModule;
import com.shellcore.android.shelltwitter.hashtags.ui.HashtagsView;
import com.shellcore.android.shelltwitter.images.adapters.OnItemClickListener;
import com.shellcore.android.shelltwitter.images.di.DaggerImagesComponent;
import com.shellcore.android.shelltwitter.images.di.ImagesComponent;
import com.shellcore.android.shelltwitter.images.di.ImagesModule;
import com.shellcore.android.shelltwitter.images.ui.ImagesView;
import com.shellcore.android.shelltwitter.lib.di.LibsModule;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

/**
 * Created by Cesar on 14/07/2017.
 */

public class TwitterClientApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupTwitter();
    }

    private void setupTwitter() {
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener clickListener) {
        return DaggerImagesComponent.builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }

    public HashtagsComponent getHashtagsComponent( HashtagsView view, com.shellcore.android.shelltwitter.hashtags.adapters.OnItemClickListener clickListener) {
        return DaggerHashtagsComponent.builder()
                .libsModule(new LibsModule(null))
                .hashtagsModule(new HashtagsModule(view, clickListener))
                .build();
    }
}
