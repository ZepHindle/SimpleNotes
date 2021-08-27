package com.chersoft.simplenotes.dg;

import android.content.Context;

import com.chersoft.simplenotes.data.NoteInfoRepositoryImpl;
import com.chersoft.simplenotes.data.NoteRepositoryImpl;
import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NoteRepository;
import com.chersoft.simplenotes.presentation.viewmodels.NotesListViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class MainModule {

    private Context applicationContext;

    public MainModule(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    @Singleton
    @Provides
    NoteInfoRepository provideRepository(){
        return new NoteInfoRepositoryImpl(applicationContext);
    }

    @Singleton
    @Provides
    NotesListViewModel provideNotesListViewModel(){
        return new NotesListViewModel();
    }

    @Singleton
    @Provides
    NoteRepository provideNoteRepository(){
        return new NoteRepositoryImpl(applicationContext);
    }

    @Provides
    Context provideContext(){
        return applicationContext;
    }
}
