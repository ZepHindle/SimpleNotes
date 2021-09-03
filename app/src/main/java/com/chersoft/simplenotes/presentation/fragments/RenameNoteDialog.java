package com.chersoft.simplenotes.presentation.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.presentation.NotesListActivity;
import com.chersoft.simplenotes.utils.NoteNameValidation;

public class RenameNoteDialog extends DialogFragment {

    private static final String ARG_NOTE_NAME = "com.chersoft.argNoteName";

    private AlertDialog alertDialog;
    private EditText nameEditText;
    private String oldName;
    private @Nullable String noteName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context context = requireContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_note_dialog_layout, null);

        this.nameEditText = view.findViewById(R.id.new_note_dialog_name_edit_text);

        this.alertDialog = new AlertDialog.Builder(context)
                .setTitle(R.string.new_note_dialog_enter_note_name)
                .setView(view)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, null)
                .create();
        return this.alertDialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.oldName = getArguments().getString(ARG_NOTE_NAME);
        nameEditText.setText(oldName);
        Button button = alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
        button.setOnClickListener( v -> {
            String text = nameEditText.getText().toString();
            Activity activity = getActivity();
            if (!(activity instanceof NotesListActivity)) return;
            NotesListActivity notesListActivity = (NotesListActivity) activity;

            if (!NoteNameValidation.noteNameIsValid(text)){
                toast(R.string.note_bad_name);
                noteName = null;
                return;
            }

            if (notesListActivity.getPresenter().onNoteContainsName(text)){
                // уведомляем пользователя только если новое имя не совпадет
                // со старым. иначе молча ничего не меняем
                if (!text.equals(oldName)){
                    toast(R.string.note_already_exists);
                } else {
                    alertDialog.dismiss();
                }
                noteName = null;
                return;
            }

            noteName = text;
            alertDialog.dismiss();
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (!(activity instanceof NotesListActivity)) return;
        NotesListActivity notesListActivity = (NotesListActivity) activity;
        notesListActivity.onRenameNoteDialogDismiss(oldName, noteName);
    }

    private void toast(int stringResId){
        Toast.makeText(requireContext(), stringResId, Toast.LENGTH_LONG).show();
    }


    public static RenameNoteDialog newInstance(NoteInfo noteInfo) {
        Bundle args = new Bundle();
        args.putString(ARG_NOTE_NAME, noteInfo.getName());
        RenameNoteDialog fragment = new RenameNoteDialog();
        fragment.setArguments(args);
        return fragment;
    }
}
