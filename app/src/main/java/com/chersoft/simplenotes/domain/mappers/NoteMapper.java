package com.chersoft.simplenotes.domain.mappers;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.models.NoteModel;
import com.chersoft.simplenotes.domain.models.Note;

public class NoteMapper {

    public static @Nullable Note map(NoteModel noteModel){
        return noteModel == null ? null : new Note(noteModel.getText());
    }

    public static NoteModel reverse(Note note){
        NoteModel noteModel = new NoteModel();
        noteModel.setText(note.getText());
        return noteModel;
    }

}
