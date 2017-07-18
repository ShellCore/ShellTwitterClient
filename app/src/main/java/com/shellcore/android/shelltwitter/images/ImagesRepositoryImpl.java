package com.shellcore.android.shelltwitter.images;

import com.shellcore.android.shelltwitter.api.CustomTwitterApiClient;
import com.shellcore.android.shelltwitter.entities.Image;
import com.shellcore.android.shelltwitter.images.events.ImagesEvent;
import com.shellcore.android.shelltwitter.lib.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Cesar on 17/07/2017.
 */

public class ImagesRepositoryImpl implements ImagesRepository {

    private final static int TWEET_COUNT = 100;

    private EventBus eventBus;
    private CustomTwitterApiClient client;

    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Call<List<Tweet>> callback = client.getTimelineService()
                .homeTimeline(TWEET_COUNT, true, true, true, true);
        callback.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Image> images = new ArrayList<>();
                for (Tweet tweet : result.data) {
                    if (containsImages(tweet)) {
                        Image tweetModel = new Image();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index = tweetText.indexOf("http");
                        if (index > 0) {
                            tweetText = tweetText.substring(0, index);
                        }
                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageUrl(imageUrl);

                        images.add(tweetModel);
                    }
                }

                Collections.sort(images, new Comparator<Image>() {
                    @Override
                    public int compare(Image img1, Image img2) {
                        return img2.getFavoriteCount() - img1.getFavoriteCount();
                    }
                });
                post(images);
            }

            @Override
            public void failure(TwitterException exception) {
                post(exception.getLocalizedMessage());
            }
        });
    }

    private boolean containsImages(Tweet tweet) {
        return tweet.entities != null
                && tweet.entities.media != null
                && !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> items, String error) {
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(items);
        eventBus.post(event);
    }

    private void post(List<Image> items) {
        post(items, null);
    }

    private void post(String error) {
        post(null, error);
    }
}
