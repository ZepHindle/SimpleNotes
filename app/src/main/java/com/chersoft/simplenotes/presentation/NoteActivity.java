package com.chersoft.simplenotes.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.presentation.presenters.NoteActivityPresenter;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModel;

public class NoteActivity extends AppCompatActivity implements NoteView {

    private static final String EXTRA_NOTE_INFO_MODEL = "com.chersoft.extraNoteInfoModel";

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
                presenter.onTextChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        ((MainApplication)getApplicationContext()).mainComponent.inject(presenter);

        NoteInfoModel noteInfoModel = (NoteInfoModel) getIntent().getSerializableExtra(EXTRA_NOTE_INFO_MODEL);
        presenter.onCreate(this, noteInfoModel);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
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

    public static Intent createIntent(Context context, NoteInfoModel noteInfoModel){
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(EXTRA_NOTE_INFO_MODEL, noteInfoModel);
        return intent;
    }

}