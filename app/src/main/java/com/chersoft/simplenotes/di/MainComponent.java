package com.chersoft.simplenotes.di;

import com.chersoft.simplenotes.presentation.NoteActivity;
import com.chersoft.simplenotes.presentation.NotesListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    /*NoteInfoRepository getNoteInfoRepository();
    NotesListViewModel getNotesListViewModel();
    NoteRepository getNoteRepository();
    Context getApplicationContext();*/
    void inject(NotesListActivity activity);
    void inject(NoteActivity noteActivity);
}
