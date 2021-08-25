package com.chersoft.simplenotes.presentation.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.presentation.NoteActivity;

public class NoteExitDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.do_you_want_to_save_the_changes)
                .setPositiveButton(android.R.string.yes, (a, b) -> {
                    if (!(getActivity() instanceof NoteActivity)) return;
                    ((NoteActivity) getActivity()).onChooseSave(true);
                }).setNegativeButton(android.R.string.no, (a, b) -> {
                    if (!(getActivity() instanceof NoteActivity)) return;
                    ((NoteActivity) getActivity()).onChooseSave(false);
                }).create();
    }
}
