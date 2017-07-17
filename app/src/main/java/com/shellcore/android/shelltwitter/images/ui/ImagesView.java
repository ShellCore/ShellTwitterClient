package com.shellcore.android.shelltwitter.images.ui;

import com.shellcore.android.shelltwitter.entities.Image;

import java.util.List;

/**
 * Created by Cesar on 17/07/2017.
 */

public interface ImagesView {
    void showImages();
    void hideImages();
    void showProgressbar();
    void hideProgressbar();

    void onError(String error);
    void setContent(List<Image> items);
}
