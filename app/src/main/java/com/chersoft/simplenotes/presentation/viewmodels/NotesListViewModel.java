package com.chersoft.simplenotes.presentation.viewmodels;

import com.chersoft.simplenotes.domain.models.NoteInfo;

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

    public int findIndexByName(String name) {
        for (int i = 0; i<notes.size(); i++){
            if (notes.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public void setNotes(ArrayList<NoteInfo> notes) {
        this.notes = notes;
    }

    public ArrayList<NoteInfo> getNotes() {
        return notes;
    }
}
