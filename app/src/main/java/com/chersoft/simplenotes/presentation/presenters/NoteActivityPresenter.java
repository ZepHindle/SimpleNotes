package com.chersoft.simplenotes.presentation.presenters;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteRepository;
import com.chersoft.simplenotes.presentation.NoteView;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModel;

import javax.inject.Inject;

public class NoteActivityPresenter {

    @Inject
    NoteRepository noteRepository;

    private NoteView noteView;
    private NoteViewModel noteViewModel;
    private NoteInfoModel noteInfoModel;

    public void onCreate(NoteView noteView, NoteInfoModel noteInfoModel){
        this.noteView = noteView;
        this.noteViewModel = NoteViewModel.load(noteRepository, noteInfoModel, noteView.getContext());
        this.noteInfoModel = noteInfoModel;
        noteView.setViewTitle(noteInfoModel.getName());
        noteView.setText(noteViewModel.getText());
    }

    public void onStop(){
        NoteViewModel.save(noteView.getContext());
    }

    public void onBackPressed(){
        if (noteViewModel.hasChanges()){
            noteView.showExitDialog();
        } else {
            noteView.close();
            NoteViewModel.remove(noteView.getContext());
        }
    }

    public void onChooseSave(boolean needSave){
        if (needSave){
            noteRepository.setByName(noteInfoModel.getName(), noteViewModel.getNoteModel());
        }
        NoteViewModel.remove(noteView.getContext());
        noteView.close();
    }

    public void onTextChanged(String text){
        noteViewModel.setText(text);
        noteViewModel.setHasChanges(true);
    }
}
