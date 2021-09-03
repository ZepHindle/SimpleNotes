package com.chersoft.simplenotes.domain.repositories;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.models.NoteModel;

public interface NoteRepository {
    @Nullable NoteModel getByName(String name);
    void setByName(String name, NoteModel noteModel);
    void remove(String name);
    void changeName(String oldName, String newName);
}
