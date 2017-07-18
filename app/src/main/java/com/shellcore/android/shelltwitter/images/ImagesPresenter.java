package com.shellcore.android.shelltwitter.images;

import com.shellcore.android.shelltwitter.images.events.ImagesEvent;

/**
 * Created by Cesar on 17/07/2017.
 */

public interface ImagesPresenter {

    void onResume();
    void onPause();
    void onDestroy();

    void getImageTweets();

    void onEventMainThread(ImagesEvent event);
}
