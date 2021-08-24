package com.chersoft.simplenotes.dg;

import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NoteRepository;
import com.chersoft.simplenotes.presentation.NoteActivity;
import com.chersoft.simplenotes.presentation.NotesListActivity;
import com.chersoft.simplenotes.presentation.presenters.NoteActivityPresenter;
import com.chersoft.simplenotes.presentation.presenters.NotesListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    NoteInfoRepository getNoteInfoRepository();
    NoteRepository getNoteRepository();
    void inject(NotesListPresenter notesListPresenter);
    void inject(NoteActivityPresenter noteActivityPresenter);
}
