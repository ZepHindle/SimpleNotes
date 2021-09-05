package com.chersoft.simplenotes.presentation.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.presentation.NotesListActivity;
import com.chersoft.simplenotes.presentation.utils.ColorTable;

public class NoteColorDialog extends DialogFragment {

    private static final String ARG_NOTE_INFO = "com.chersoft.argNoteName";

    private NoteInfo noteInfo;

    private Spinner backgroundColorSpinner;
    private Spinner fontColorSpinner;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        this.noteInfo = (NoteInfo) getArguments().getSerializable(ARG_NOTE_INFO);

        Context context = requireContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.note_color_dialog_layout, null);

        this.backgroundColorSpinner = view.findViewById(R.id.background_color_spinner);
        this.fontColorSpinner = view.findViewById(R.id.font_color_spinner);

        MyAdapter myAdapter = new MyAdapter(context,  android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        backgroundColorSpinner.setAdapter(myAdapter);
        fontColorSpinner.setAdapter(myAdapter);

        backgroundColorSpinner.setSelection(noteInfo.getBackgroundColorIndex());
        fontColorSpinner.setSelection(noteInfo.getFontColorIndex());

        return new AlertDialog.Builder(context)
                .setView(view)
                .create();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (!(activity instanceof NotesListActivity)) return;
        NotesListActivity notesListActivity = (NotesListActivity) activity;
        notesListActivity.onNoteColorDialogDismiss(noteInfo.getName(),
                backgroundColorSpinner.getSelectedItemPosition(), fontColorSpinner.getSelectedItemPosition());
    }

    public static NoteColorDialog newInstance(NoteInfo noteInfo) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_INFO, noteInfo);
        NoteColorDialog fragment = new NoteColorDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private static class MyAdapter extends ArrayAdapter<String>{
        public MyAdapter(Context context, int resourceId) {
            super(context, resourceId, getStubValues());
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            changeViewColor(position, view);
            return view;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            changeViewColor(position, view);
            return view;
        }

        private void changeViewColor(int position, @Nullable View view){
            view.setBackgroundColor(ColorTable.getColor(position));
        }

        private static String [] getStubValues(){
            int count = ColorTable.getTableSize();
            String[] stubValues = new String[count];
            for (int i = 0; i<count; i++){
                stubValues[i] = "";
            }
            return stubValues;
        }
    }
}
