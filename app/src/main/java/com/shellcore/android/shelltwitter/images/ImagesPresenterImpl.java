package com.shellcore.android.shelltwitter.images;

import com.shellcore.android.shelltwitter.images.events.ImagesEvent;
import com.shellcore.android.shelltwitter.images.ui.ImagesView;
import com.shellcore.android.shelltwitter.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 17/07/2017.
 */

public class ImagesPresenterImpl implements ImagesPresenter {

    private EventBus eventBus;
    private ImagesView view;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getImageTweets() {
        if (view != null) {
            view.hideImages();
            view.showProgressbar();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        if (view != null) {
            view.hideProgressbar();
            view.showImages();

            String errorMsg = event.getError();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContent(event.getImages());
            }
        }
    }
}
