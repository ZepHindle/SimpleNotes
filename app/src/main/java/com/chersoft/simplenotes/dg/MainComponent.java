package com.chersoft.simplenotes.dg;

import android.content.Context;

import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NoteRepository;
import com.chersoft.simplenotes.presentation.NoteActivity;
import com.chersoft.simplenotes.presentation.NotesListActivity;
import com.chersoft.simplenotes.presentation.presenters.NoteActivityPresenter;
import com.chersoft.simplenotes.presentation.presenters.NotesListPresenter;
import com.chersoft.simplenotes.presentation.viewmodels.NotesListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    NoteInfoRepository getNoteInfoRepository();
    NotesListViewModel getNotesListViewModel();
    NoteRepository getNoteRepository();
    Context getApplicationContext();
    void inject(NotesListPresenter notesListPresenter);
    void inject(NoteActivityPresenter noteActivityPresenter);
}
