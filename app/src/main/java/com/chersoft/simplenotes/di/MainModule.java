package com.chersoft.simplenotes.di;

import android.content.Context;

import com.chersoft.simplenotes.data.repositories.NoteInfoRepositoryImpl;
import com.chersoft.simplenotes.data.repositories.NoteRepositoryImpl;
import com.chersoft.simplenotes.domain.repositories.NoteInfoRepository;
import com.chersoft.simplenotes.domain.interactors.NoteInteractor;
import com.chersoft.simplenotes.domain.repositories.NoteRepository;
import com.chersoft.simplenotes.domain.interactors.NotesListInteractor;
import com.chersoft.simplenotes.presentation.presenters.NotePresenter;
import com.chersoft.simplenotes.presentation.presenters.NotesListPresenter;
import com.chersoft.simplenotes.presentation.viewmodels.NotesListViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class MainModule {

    private final Context applicationContext;

    public MainModule(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    @Singleton
    @Provides
    NoteInfoRepository provideNoteInfoRepository(){
        return new NoteInfoRepositoryImpl(applicationContext);
    }

    @Singleton
    @Provides
    NoteRepository provideNoteRepository(){
        return new NoteRepositoryImpl(applicationContext);
    }

    @Singleton
    @Provides
    NotesListViewModel provideNotesListViewModel(){
        return new NotesListViewModel();
    }

    @Provides
    NotesListInteractor provideNotesListInteractor(NoteInfoRepository noteInfoRepository, NoteRepository noteRepository){
        return new NotesListInteractor(noteInfoRepository, noteRepository);
    }

    @Provides
    NotesListPresenter provideNotesListPresenter(NotesListInteractor interactor, NotesListViewModel viewModel){
        return new NotesListPresenter(interactor, viewModel);
    }

    @Provides
    NoteInteractor provideNoteInteractor(NoteRepository noteRepository){
        return new NoteInteractor(noteRepository, applicationContext);
    }

    @Provides
    NotePresenter provideNotePresenter(NoteInteractor noteInteractor){
        return new NotePresenter(noteInteractor);
    }

    @Provides
    Context provideContext(){
        return applicationContext;
    }
}
