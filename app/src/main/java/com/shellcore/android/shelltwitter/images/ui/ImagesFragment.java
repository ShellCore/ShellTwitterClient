package com.shellcore.android.shelltwitter.images.ui;


import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.shellcore.android.shelltwitter.R;
import com.shellcore.android.shelltwitter.entities.Image;
import com.shellcore.android.shelltwitter.images.ImagesPresenter;
import com.shellcore.android.shelltwitter.images.adapters.ImagesAdapter;
import com.shellcore.android.shelltwitter.images.adapters.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ImagesFragment extends Fragment implements ImagesView, OnItemClickListener {

    // Components
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    // Servicios
    private ImagesPresenter presenter;

    // Variables
    private ImagesAdapter adapter;

    public ImagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl()));
        startActivity(intent);
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
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
    public void setContent(List<Image> items) {
        adapter.setImages(items);
    }
}
