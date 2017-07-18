package com.shellcore.android.shelltwitter.images.events;

import com.shellcore.android.shelltwitter.entities.Image;

import java.util.List;

/**
 * Created by Cesar on 17/07/2017.
 */

public class ImagesEvent {

    private List<Image> images;
    private String error;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
