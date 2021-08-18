package com.chersoft.simplenotes.domain;

import com.chersoft.simplenotes.data.NoteInfoModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        notes.add(0, model);
        return 0;//findIndex(model);
    }

    @Override
    public boolean containsName(String name) {
        for (NoteInfoModel model : notes){
            if (model.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
