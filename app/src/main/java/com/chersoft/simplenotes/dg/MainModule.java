package com.chersoft.simplenotes.dg;

import android.content.Context;

import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NoteInfoRepositoryStubImpl;
import com.chersoft.simplenotes.domain.NoteRepository;
import com.chersoft.simplenotes.domain.NoteRepositoryImpl;

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
        return new NoteInfoRepositoryStubImpl();
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
