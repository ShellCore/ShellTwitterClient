package com.shellcore.android.shelltwitter.hashtags;

/**
 * Created by Cesar on 18/07/2017.
 */

public class HashtagsInteractorImpl implements HashtagsInteractor {

    private HashtagsRepository repository;

    public HashtagsInteractorImpl(HashtagsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getHashtags();
    }
}
