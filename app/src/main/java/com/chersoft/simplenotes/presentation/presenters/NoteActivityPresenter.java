package com.chersoft.simplenotes.presentation.presenters;

import com.chersoft.simplenotes.NoteView;
import com.chersoft.simplenotes.domain.NoteRepository;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModel;

import javax.inject.Inject;

public class NoteActivityPresenter {

    @Inject
    NoteRepository noteRepository;

    private NoteView noteView;
    private NoteViewModel noteViewModel;

    public void onCreate(NoteView noteView){
        this.noteView = noteView;
        // TODO: get view model!
    }

}
