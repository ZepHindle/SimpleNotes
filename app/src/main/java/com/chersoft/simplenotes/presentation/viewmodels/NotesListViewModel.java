package com.chersoft.simplenotes.presentation.viewmodels;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;

import java.util.ArrayList;

public class NotesListViewModel {

    private ArrayList<NoteInfoModel> notes = new ArrayList<>();

    public int getCount() {
        return notes.size();
    }

    public NoteInfoModel getByIndex(int index) {
        return notes.get(index);
    }

    public int findIndex(NoteInfoModel model) {
        return notes.indexOf(model);
    }

    public void removeByIndex(int index) {
        notes.remove(index);
    }

    public int add(NoteInfoModel model) {
        notes.add(0, model);
        return 0;//findIndex(model);
    }

    public boolean containsName(String name) {
        for (NoteInfoModel model : notes){
            if (model.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public NoteInfoModel findByName(String name) {
        for (NoteInfoModel model : notes){
            if (model.getName().equals(name)) {
                return model;
            }
        }
        return null;
    }

    public void setNotes(ArrayList<NoteInfoModel> notes) {
        this.notes = notes;
    }

    public ArrayList<NoteInfoModel> getNotes() {
        return notes;
    }

    /*
    public void loadFromRepository(NoteInfoRepository repository){
        notes = new ArrayList<>(repository.load());
    }

    public void saveToRepository(NoteInfoRepository repository){
        repository.save(notes);
    }
    */
}
