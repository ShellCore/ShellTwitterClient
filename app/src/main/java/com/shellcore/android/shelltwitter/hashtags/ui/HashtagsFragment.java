package com.shellcore.android.shelltwitter.hashtags.ui;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.shellcore.android.shelltwitter.R;
import com.shellcore.android.shelltwitter.TwitterClientApp;
import com.shellcore.android.shelltwitter.entities.Hashtag;
import com.shellcore.android.shelltwitter.hashtags.HashtagsPresenter;
import com.shellcore.android.shelltwitter.hashtags.adapters.HashtagsAdapter;
import com.shellcore.android.shelltwitter.hashtags.adapters.OnItemClickListener;
import com.shellcore.android.shelltwitter.hashtags.di.HashtagsComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HashtagsFragment extends Fragment implements HashtagsView, OnItemClickListener {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.container)
    FrameLayout container;

    @Inject
    HashtagsAdapter adapter;
    @Inject
    HashtagsPresenter presenter;


    public HashtagsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();
        setupRecyclerView();
        presenter.getHashtagsTweets();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(Hashtag hashtag) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hashtag.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void showHashtags() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHashtags() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setContent(List<Hashtag> hashtags) {
        adapter.setItems(hashtags);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp) getActivity().getApplication();
        HashtagsComponent component = app.getHashtagsComponent(this, this);
        component.inject(this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
