package com.shellcore.android.shelltwitter.hashtags;

import com.shellcore.android.shelltwitter.hashtags.events.HashtagsEvent;

/**
 * Created by Cesar on 18/07/2017.
 */

public interface HashtagsPresenter {

    void onResume();
    void onPause();
    void onDestroy();

    void getHashtagsTweets();

    void onEventMainThread(HashtagsEvent event);
}
