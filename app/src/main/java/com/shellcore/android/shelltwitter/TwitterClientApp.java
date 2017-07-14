package com.shellcore.android.shelltwitter;

import android.app.Application;
import android.util.Log;

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
}
