package com.shellcore.android.shelltwitter.hashtags;

import com.shellcore.android.shelltwitter.hashtags.events.HashtagsEvent;
import com.shellcore.android.shelltwitter.hashtags.ui.HashtagsView;
import com.shellcore.android.shelltwitter.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Cesar on 18/07/2017.
 */

public class HashtagsPresenterImpl implements HashtagsPresenter {

    private EventBus eventBus;
    private HashtagsView view;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(EventBus eventBus, HashtagsView view, HashtagsInteractor interactor) {
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
    public void getHashtagsTweets() {
        if (view != null) {
            view.hideHashtags();
            view.showProgressbar();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event) {
        if (view != null) {
            view.hideProgressbar();
            view.showHashtags();

            String errorMsg = event.getError();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContent(event.getHashtags());
            }
        }
    }
}
