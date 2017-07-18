package com.shellcore.android.shelltwitter.hashtags.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shellcore.android.shelltwitter.R;
import com.shellcore.android.shelltwitter.entities.Hashtag;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 18/07/2017.
 */

public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder> {

    private List<Hashtag> hashtags;
    private OnItemClickListener clickListener;

    public HashtagsAdapter(List<Hashtag> hashtags, OnItemClickListener clickListener) {
        this.hashtags = hashtags;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_hashtags, parent, false);
        return new ViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hashtag tweet = hashtags.get(position);
        holder.setOnClickListener(tweet, clickListener);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.setItems(tweet.getHashtags());


    }

    @Override
    public int getItemCount() {
        return hashtags.size();
    }

    public void setItems(List<Hashtag> newHashtags) {
        hashtags.addAll(newHashtags);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private HashtagListAdapter listAdapter;

        private List<String> items;

        @BindView(R.id.txt_tweet)
        TextView txtTweet;
        @BindView(R.id.rec_hashtags)
        RecyclerView recHashtags;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;

            items = new ArrayList<>();
            listAdapter = new HashtagListAdapter(items);

            CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, 3);

            recHashtags.setLayoutManager(layoutManager);
            recHashtags.setAdapter(listAdapter);
        }

        public void setItems(List<String> items) {
            this.items.clear();
            this.items.addAll(items);
            listAdapter.notifyDataSetChanged();
        }

        public void setOnClickListener(final Hashtag hashtag, final OnItemClickListener clickListener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(hashtag);
                }
            });
        }
    }
}
