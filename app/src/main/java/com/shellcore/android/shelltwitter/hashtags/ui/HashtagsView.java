package com.shellcore.android.shelltwitter.hashtags.ui;

import com.shellcore.android.shelltwitter.entities.Hashtag;

import java.util.List;

/**
 * Created by Cesar on 18/07/2017.
 */

public interface HashtagsView {

    void showHashtags();
    void hideHashtags();
    void showProgressbar();
    void hideProgressbar();

    void onError(String error);

    void setContent(List<Hashtag> hashtags);
}
