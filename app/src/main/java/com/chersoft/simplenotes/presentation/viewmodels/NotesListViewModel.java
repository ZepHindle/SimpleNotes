package com.chersoft.simplenotes.presentation.viewmodels;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInfo;
import com.chersoft.simplenotes.domain.NoteInfoRepository;

import java.util.ArrayList;

public class NotesListViewModel {

    private ArrayList<NoteInfo> notes = new ArrayList<>();

    public int getCount() {
        return notes.size();
    }

    public NoteInfo getByIndex(int index) {
        return notes.get(index);
    }

    public int findIndex(NoteInfo model) {
        return notes.indexOf(model);
    }

    public void removeByIndex(int index) {
        notes.remove(index);
    }

    public int add(NoteInfo model) {
        notes.add(0, model);
        return 0;//findIndex(model);
    }

    public boolean containsName(String name) {
        for (NoteInfo model : notes){
            if (model.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public NoteInfo findByName(String name) {
        for (NoteInfo model : notes){
            if (model.getName().equals(name)) {
                return model;
            }
        }
        return null;
    }

    public void setNotes(ArrayList<NoteInfo> notes) {
        this.notes = notes;
    }

    public ArrayList<NoteInfo> getNotes() {
        return notes;
    }
}
