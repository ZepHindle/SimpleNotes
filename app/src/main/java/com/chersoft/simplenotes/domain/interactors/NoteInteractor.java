package com.chersoft.simplenotes.domain.interactors;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.domain.models.Note;
import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.domain.mappers.NoteMapper;
import com.chersoft.simplenotes.domain.repositories.NoteRepository;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModel;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModelManager;

import javax.inject.Inject;

public class NoteInteractor {

    private final NoteRepository noteRepository;
    private final Context applicationContext;

    @Inject
    public NoteInteractor(NoteRepository noteRepository, Context applicationContext){
        this.noteRepository = noteRepository;
        this.applicationContext = applicationContext;
    }

    public NoteViewModel loadViewModel(NoteInfo noteInfo){
        return NoteViewModelManager.load(this, noteInfo, applicationContext);
    }

    public void setByName(String name, Note note){
        noteRepository.setByName(name, NoteMapper.reverse(note));
    }

    public @Nullable Note getByName(String name){
        return NoteMapper.map(noteRepository.getByName(name));
    }
}
