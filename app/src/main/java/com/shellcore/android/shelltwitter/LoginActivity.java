package com.shellcore.android.shelltwitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.shellcore.android.shelltwitter.main.MainActivity;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    // Componentes
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.btn_twitter_login)
    TwitterLoginButton btnTwitterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (TwitterCore.getInstance().getSessionManager().getActiveSession() != null) {
            navigateToMainScreen();
        }

        btnTwitterLogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                navigateToMainScreen();
            }

            @Override
            public void failure(TwitterException exception) {
                String errorMsg = String.format(getString(R.string.login_error_message), exception.getLocalizedMessage());
                Snackbar.make(container, errorMsg, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        btnTwitterLogin.onActivityResult(requestCode, resultCode, data);
    }
}
