package com.chersoft.simplenotes.domain;

import com.chersoft.simplenotes.data.NoteInfoModel;

import java.util.ArrayList;

public class NoteInfoRepositoryStubImpl implements NoteInfoRepository {

    private ArrayList<NoteInfoModel> notes = new ArrayList<>();

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public NoteInfoModel getByIndex(int index) {
        return notes.get(index);
    }

    @Override
    public int findIndex(NoteInfoModel model) {
        return notes.indexOf(model);
    }

    @Override
    public void removeByIndex(int index) {
        notes.remove(index);
    }

    @Override
    public int add(NoteInfoModel model) {
        notes.add(model);
        return findIndex(model);
    }
}
