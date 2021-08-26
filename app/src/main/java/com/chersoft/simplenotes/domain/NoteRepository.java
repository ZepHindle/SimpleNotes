package com.chersoft.simplenotes.domain;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.NoteModel;

public interface NoteRepository {
    @Nullable NoteModel getByName(String name);
    void setByName(String name, NoteModel noteModel);
    // TODO: void changeName(String oldName, String newName);
}
