package com.chersoft.simplenotes.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.presentation.presenters.CreateAccountPresenter;

import javax.inject.Inject;

public class CreateAccountActivity extends AppCompatActivity implements CreateAccountView {

    @Inject
    CreateAccountPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((MainApplication) getApplicationContext()).mainComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        EditText userText = findViewById(R.id.create_account_user_name_edit_text);
        EditText passwordText = findViewById(R.id.create_account_password_edit_text);
        EditText passwordRepeatText = findViewById(R.id.create_account_password_repeat_edit_text);

        findViewById(R.id.create_account_button).setOnClickListener( v -> {
            presenter.onRegisterButtonPress(
                    userText.getText().toString(),
                    passwordText.getText().toString(),
                    passwordRepeatText.getText().toString()
            );
        });

        this.progressBar = findViewById(R.id.create_account_activity_progress_bar);
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