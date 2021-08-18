package com.chersoft.simplenotes.presentation;

public interface NotesListView {
    void addNote(int index);
    void removeNote(int index);
    void updateNote(int index);

    void showNewNoteDialog();

    void showToast(int stringResId);
}
