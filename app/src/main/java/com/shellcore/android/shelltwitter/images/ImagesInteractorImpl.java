package com.shellcore.android.shelltwitter.images;

/**
 * Created by Cesar on 17/07/2017.
 */

public class ImagesInteractorImpl implements ImagesInteractor {

    private ImagesRepository repository;

    public ImagesInteractorImpl(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
