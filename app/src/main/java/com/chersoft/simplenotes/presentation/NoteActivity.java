package com.chersoft.simplenotes.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.presentation.fragments.NoteExitDialog;
import com.chersoft.simplenotes.presentation.presenters.NoteActivityPresenter;

public class NoteActivity extends AppCompatActivity implements NoteView {

    private static final String EXTRA_NOTE_INFO_MODEL = "com.chersoft.extraNoteInfoModel";

    private boolean firstTextWatcherCall;
    private NoteActivityPresenter presenter;
    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        presenter = new NoteActivityPresenter();

        noteEditText = findViewById(R.id.note_text_edit);
        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (firstTextWatcherCall) {
                    firstTextWatcherCall = false;
                } else {
                    presenter.onTextChanged(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        firstTextWatcherCall = true;

        ((MainApplication)getApplicationContext()).mainComponent.inject(presenter);

        NoteInfoModel noteInfoModel = (NoteInfoModel) getIntent().getSerializableExtra(EXTRA_NOTE_INFO_MODEL);
        presenter.onCreate(this, noteInfoModel);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        presenter.onBackPressed();
    }

    public void onChooseSave(boolean needSave){
        presenter.onChooseSave(needSave);
    }

    // реализация методов NoteView

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setText(String text) {
        noteEditText.setText(text);
    }

    @Override
    public void setViewTitle(String title) {
        setTitle(title);
    }

    @Override
    public void showExitDialog() {
        FragmentManager fm = getSupportFragmentManager();
        NoteExitDialog dialog = new NoteExitDialog();
        dialog.show(fm, "com.chersoft.noteExitDialog");
    }

    @Override
    public void close() {
        this.finish();
    }

    public static Intent createIntent(Context context, NoteInfoModel noteInfoModel){
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(EXTRA_NOTE_INFO_MODEL, noteInfoModel);
        return intent;
    }

}