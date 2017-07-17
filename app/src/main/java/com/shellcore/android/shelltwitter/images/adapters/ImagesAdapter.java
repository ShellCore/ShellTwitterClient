package com.shellcore.android.shelltwitter.images.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shellcore.android.shelltwitter.R;
import com.shellcore.android.shelltwitter.entities.Image;
import com.shellcore.android.shelltwitter.lib.base.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 17/07/2017.
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<Image> images;
    private ImageLoader imageLoader;
    private OnItemClickListener clickListener;

    public ImagesAdapter(List<Image> images, ImageLoader imageLoader, OnItemClickListener clickListener) {
        this.images = images;
        this.imageLoader = imageLoader;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_images, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image imageTweet = images.get(position);
        holder.setOnClickListener(imageTweet, clickListener);
        holder.txtTweet.setText(imageTweet.getTweetText());

        imageLoader.load(holder.imgMedia, imageTweet.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setImages(List<Image> newImages) {
        images.addAll(newImages);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View v;

        @BindView(R.id.img_media)
        ImageView imgMedia;
        @BindView(R.id.txt_tweet)
        TextView txtTweet;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            v = itemView;
        }

        public void setOnClickListener(final Image image, final OnItemClickListener listener) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(image);
                }
            });
        }
    }
}
