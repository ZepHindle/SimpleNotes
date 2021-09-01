package com.chersoft.simplenotes.domain;

import android.content.Context;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.data.NoteModel;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModel;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModelManager;

import javax.inject.Inject;

public class NoteInteractor {

    private NoteRepository noteRepository;
    private Context applicationContext;

    @Inject
    public NoteInteractor(NoteRepository noteRepository, Context applicationContext){
        this.noteRepository = noteRepository;
        this.applicationContext = applicationContext;
    }

    public NoteViewModel loadViewModel(NoteInfoModel noteInfoModel){
        return NoteViewModelManager.load(noteRepository, noteInfoModel, applicationContext);
    }

    public void setByName(String name, NoteModel noteModel){
        noteRepository.setByName(name, noteModel);
    }
}
