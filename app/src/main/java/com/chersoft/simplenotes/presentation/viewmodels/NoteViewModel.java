package com.chersoft.simplenotes.presentation.viewmodels;

import android.content.Context;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.data.NoteModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NoteRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class NoteViewModel implements Serializable {

    private boolean hasChanges = false;
    private NoteModel noteModel = new NoteModel();

    public String getText() {
        return noteModel.getText();
    }

    public NoteModel getNoteModel() {
        return noteModel;
    }

    public boolean hasChanges() {
        return hasChanges;
    }

    public void setText(String text) {
        noteModel.setText(text);
    }

    public void setHasChanges(boolean hasChanges) {
        this.hasChanges = hasChanges;
    }
}
