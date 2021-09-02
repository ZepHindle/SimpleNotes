package com.chersoft.simplenotes.presentation.viewmodels;

import com.chersoft.simplenotes.domain.models.Note;

import java.io.Serializable;

public class NoteViewModel implements Serializable {

    private boolean hasChanges = false;
    private Note note = new Note("");

    public String getText() {
        return note.getText();
    }

    public Note getNoteModel() {
        return note;
    }

    public boolean hasChanges() {
        return hasChanges;
    }

    public void setText(String text) {
        note.setText(text);
    }

    public void setHasChanges(boolean hasChanges) {
        this.hasChanges = hasChanges;
    }
}
