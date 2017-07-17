package com.shellcore.android.shelltwitter.hashtags;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shellcore.android.shelltwitter.R;

public class HashtagsFragment extends Fragment {


    public HashtagsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hashtags, container, false);
    }

}
