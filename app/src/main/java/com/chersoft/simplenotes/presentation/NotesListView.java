package com.chersoft.simplenotes.presentation;

import com.chersoft.simplenotes.data.NoteInfoModel;

public interface NotesListView {
    void addNote(int index);
    void removeNote(int index);
    void updateNote(int index);
    void updateNotes();

    void showNewNoteDialog();
    void startNoteActivity(NoteInfoModel noteInfoModel);
    void showToast(int stringResId);
}
