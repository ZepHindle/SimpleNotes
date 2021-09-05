package com.chersoft.simplenotes.presentation;

import com.chersoft.simplenotes.domain.models.NoteInfo;

public interface NotesListView {
    void addNote(int index);
    void removeNote(int index);
    void updateNote(int index);
    void updateNotes();

    void showNewNoteDialog();
    void showRenameNoteDialog(NoteInfo noteInfo);
    void showNoteColorDialog(NoteInfo noteInfo);
    void startNoteActivity(NoteInfo noteInfoModel);
    void showToast(int stringResId);
}
