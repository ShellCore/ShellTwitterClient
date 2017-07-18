package com.shellcore.android.shelltwitter.hashtags.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shellcore.android.shelltwitter.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Cesar on 18/07/2017.
 */

class HashtagListAdapter extends RecyclerView.Adapter<HashtagListAdapter.ViewHolder> {

    private List<String> hashtags;

    public HashtagListAdapter(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_hashtag_text, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String hashtag = hashtags.get(position);
        holder.txtHashtag.setText(hashtag);
    }

    @Override
    public int getItemCount() {
        return hashtags.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_hashtag)
        TextView txtHashtag;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
