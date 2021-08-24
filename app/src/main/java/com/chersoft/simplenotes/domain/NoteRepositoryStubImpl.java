package com.chersoft.simplenotes.domain;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.NoteModel;

import java.util.HashMap;
import java.util.Map;

public class NoteRepositoryStubImpl implements NoteRepository {

    private Map<String, NoteModel> notes = new HashMap<>();

    @Nullable
    @Override
    public NoteModel getByName(String name) {
        return notes.get(name);
    }

    @Override
    public void setByName(String name, NoteModel noteModel) {
        notes.put(name, noteModel);
    }
}
