package com.shellcore.android.shelltwitter.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.shellcore.android.shelltwitter.LoginActivity;
import com.shellcore.android.shelltwitter.R;
import com.shellcore.android.shelltwitter.hashtags.ui.HashtagsFragment;
import com.shellcore.android.shelltwitter.images.ui.ImagesFragment;
import com.shellcore.android.shelltwitter.main.adapter.MainSectionsPagerAdapter;
import com.twitter.sdk.android.core.TwitterCore;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter() {
        String[] titles = new String[] {
                getString(R.string.main_header_images),
                getString(R.string.main_header_hashtags)
        };

        Fragment[] fragments = new Fragment[] {
                new ImagesFragment(),
                new HashtagsFragment()
        };

        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getFragmentManager(), titles, fragments);

        container.setAdapter(adapter);
        tabs.setupWithViewPager(container);
    }

    private void logout() {
        TwitterCore.getInstance().getSessionManager().clearActiveSession();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
