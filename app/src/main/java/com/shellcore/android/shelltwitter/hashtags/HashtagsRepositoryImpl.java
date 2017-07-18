package com.shellcore.android.shelltwitter.hashtags;

import com.shellcore.android.shelltwitter.api.CustomTwitterApiClient;
import com.shellcore.android.shelltwitter.entities.Hashtag;
import com.shellcore.android.shelltwitter.hashtags.events.HashtagsEvent;
import com.shellcore.android.shelltwitter.lib.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import retrofit2.Call;

/**
 * Created by Cesar on 18/07/2017.
 */

public class HashtagsRepositoryImpl implements HashtagsRepository {

    private static final int TWEET_COUNT = 100;

    private EventBus eventBus;
    private CustomTwitterApiClient client;

    public HashtagsRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getHashtags() {
        Call<List<Tweet>> callback = client.getTimelineService()
                .homeTimeline(TWEET_COUNT, true, true, true, true);

        callback.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Hashtag> items = new ArrayList<>();
                for (Tweet tweet :
                        result.data) {
                    if (containsHashtags(tweet)) {
                        Hashtag tweetModel = new Hashtag();
                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);
                        tweetModel.setTweetText(tweet.text);

                        List<String> hashtags = new ArrayList<>();
                        for (HashtagEntity hashtag : tweet.entities.hashtags) {
                            hashtags.add(hashtag.text);
                        }
                        tweetModel.setHashtags(hashtags);
                        items.add(tweetModel);
                    }
                }
                Collections.sort(items, new Comparator<Hashtag>() {
                    @Override
                    public int compare(Hashtag lhs, Hashtag rhs) {
                        return rhs.getFavoriteCount() - lhs.getFavoriteCount();
                    }
                });
                post(items);

            }

            @Override
            public void failure(TwitterException exception) {
                post(exception.getLocalizedMessage());
            }
        });
    }

    private boolean containsHashtags(Tweet tweet) {
        return tweet.entities != null
                && tweet.entities.hashtags != null
                && !tweet.entities.hashtags.isEmpty();
    }


    private void post(List<Hashtag> hashtags, String error) {
        HashtagsEvent event = new HashtagsEvent();
        event.setHashtags(hashtags);
        event.setError(error);
        eventBus.post(event);
    }

    private void post(List<Hashtag> hashtags) {
        post(hashtags, null);
    }

    private void post(String error) {
        post(null, error);
    }
}
