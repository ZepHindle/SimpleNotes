package com.chersoft.simplenotes.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.presentation.presenters.LogInPresenter;

import javax.inject.Inject;

public class LogInActivity extends AppCompatActivity implements LogInActivityView{

    @Inject
    LogInPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((MainApplication) getApplicationContext()).mainComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText userNameText = findViewById(R.id.login_user_name);
        EditText passwordText = findViewById(R.id.login_password);

        findViewById(R.id.login_button).setOnClickListener(v -> {
            presenter.onLogInButton(userNameText.getText().toString(),
                    passwordText.getText().toString());
        });

        this.progressBar = findViewById(R.id.login_activity_progress_bar);
        presenter.onCreate(this);
    }

    @Override
    public void toast(int stringResId) {
        Toast.makeText(this, stringResId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setProgressBarVisible(boolean visible) {
        if (visible){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}