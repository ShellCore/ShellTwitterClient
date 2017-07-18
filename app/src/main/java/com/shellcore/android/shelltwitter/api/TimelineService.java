package com.shellcore.android.shelltwitter.api;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Cesar on 14/07/2017.
 */

public interface TimelineService {
    @GET("/1.1/statuses/home_timeline.json")
    Call<List<Tweet>> homeTimeline(@Query("count") Integer count,
                                       @Query("trim_user") Boolean trimUser,
                                       @Query("exclude_replies") Boolean excludeReplies,
                                       @Query("contributor_details") Boolean contributorDetails,
                                       @Query("include_entities") Boolean includeEntities);
}
