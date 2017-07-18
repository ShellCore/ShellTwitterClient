package com.shellcore.android.shelltwitter.hashtags.di;

import com.shellcore.android.shelltwitter.api.CustomTwitterApiClient;
import com.shellcore.android.shelltwitter.entities.Hashtag;
import com.shellcore.android.shelltwitter.hashtags.HashtagsInteractor;
import com.shellcore.android.shelltwitter.hashtags.HashtagsInteractorImpl;
import com.shellcore.android.shelltwitter.hashtags.HashtagsPresenter;
import com.shellcore.android.shelltwitter.hashtags.HashtagsPresenterImpl;
import com.shellcore.android.shelltwitter.hashtags.HashtagsRepository;
import com.shellcore.android.shelltwitter.hashtags.HashtagsRepositoryImpl;
import com.shellcore.android.shelltwitter.hashtags.adapters.HashtagsAdapter;
import com.shellcore.android.shelltwitter.hashtags.adapters.OnItemClickListener;
import com.shellcore.android.shelltwitter.hashtags.ui.HashtagsView;
import com.shellcore.android.shelltwitter.lib.base.EventBus;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 18/07/2017.
 */
@Module
public class HashtagsModule {

    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesHashtagsAdapter(List<Hashtag> hashtags, OnItemClickListener clickListener) {
        return new HashtagsAdapter(hashtags, clickListener);
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return clickListener;
    }

    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(EventBus eventBus, HashtagsView view, HashtagsInteractor interactor) {
        return new HashtagsPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    HashtagsView providesHashtagsView() {
        return view;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository repository) {
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new HashtagsRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session) {
        return new CustomTwitterApiClient(session);
    }
    
    @Provides
    @Singleton
    TwitterSession providesTwitterSession() {
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }
}
