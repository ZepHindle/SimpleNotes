package com.chersoft.simplenotes.dg;

import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NoteInfoRepositoryStubImpl;
import com.chersoft.simplenotes.domain.NoteRepository;
import com.chersoft.simplenotes.domain.NoteRepositoryStubImpl;
import com.chersoft.simplenotes.presentation.presenters.NoteActivityPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class MainModule {

    @Singleton
    @Provides
    NoteInfoRepository provideRepository(){
        return new NoteInfoRepositoryStubImpl();
    }

    @Singleton
    @Provides
    NoteRepository provideNoteRepository(){
        return new NoteRepositoryStubImpl();
    }
}
