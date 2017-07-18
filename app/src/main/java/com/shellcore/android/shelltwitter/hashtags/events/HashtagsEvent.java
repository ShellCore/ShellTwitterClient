package com.shellcore.android.shelltwitter.hashtags.events;

import com.shellcore.android.shelltwitter.entities.Hashtag;

import java.util.List;

/**
 * Created by Cesar on 18/07/2017.
 */

public class HashtagsEvent {

    private List<Hashtag> hashtags;
    private String error;


    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
