package com.chersoft.simplenotes.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.chersoft.simplenotes.NoteView;
import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.presentation.presenters.NoteActivityPresenter;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModel;

import javax.inject.Inject;

public class NoteActivity extends AppCompatActivity implements NoteView {

    private static final String EXTRA_NOTE_INFO_MODEL = "com.chersoft.extraNoteInfoModel";

    private NoteActivityPresenter noteActivityPresenter;
    private EditText noteEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteActivityPresenter = new NoteActivityPresenter();

        noteEditText = findViewById(R.id.note_text_edit);

        ((MainApplication)getApplicationContext()).mainComponent.inject(noteActivityPresenter);
        noteActivityPresenter.onCreate(this);


    }



    public static Intent createIntent(Context context, NoteInfoModel noteInfoModel){
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra(EXTRA_NOTE_INFO_MODEL, noteInfoModel);
        return intent;
    }

}