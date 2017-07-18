package com.shellcore.android.shelltwitter.images.di;

import com.shellcore.android.shelltwitter.api.CustomTwitterApiClient;
import com.shellcore.android.shelltwitter.entities.Image;
import com.shellcore.android.shelltwitter.images.ImagesInteractor;
import com.shellcore.android.shelltwitter.images.ImagesInteractorImpl;
import com.shellcore.android.shelltwitter.images.ImagesPresenter;
import com.shellcore.android.shelltwitter.images.ImagesPresenterImpl;
import com.shellcore.android.shelltwitter.images.ImagesRepository;
import com.shellcore.android.shelltwitter.images.ImagesRepositoryImpl;
import com.shellcore.android.shelltwitter.images.adapters.ImagesAdapter;
import com.shellcore.android.shelltwitter.images.adapters.OnItemClickListener;
import com.shellcore.android.shelltwitter.images.ui.ImagesView;
import com.shellcore.android.shelltwitter.lib.base.EventBus;
import com.shellcore.android.shelltwitter.lib.base.ImageLoader;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cesar on 17/07/2017.
 */
@Module
public class ImagesModule {

    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesImagesAdapter(List<Image> images, ImageLoader imageLoader, OnItemClickListener clickListener) {
        return new ImagesAdapter(images, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesImagesList() {
        return new ArrayList<Image>();
    }
    
    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        return new ImagesPresenterImpl(eventBus, view, interactor);
    }
    
    @Provides
    @Singleton
    ImagesView providesImagesView() {
        return view;
    }
    
    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository) {
        return new ImagesInteractorImpl(repository);
    }
    
    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new ImagesRepositoryImpl(eventBus, client);
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
