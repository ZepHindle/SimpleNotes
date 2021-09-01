package com.chersoft.simplenotes.presentation.presenters;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInteractor;
import com.chersoft.simplenotes.domain.NoteRepository;
import com.chersoft.simplenotes.presentation.NoteView;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModel;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModelManager;

import javax.inject.Inject;

public class NotePresenter {

    private final NoteInteractor interactor;

    private NoteView noteView;
    private NoteViewModel noteViewModel;
    private NoteInfoModel noteInfoModel;

    @Inject
    public NotePresenter(NoteInteractor noteInteractor){
        this.interactor = noteInteractor;
    }

    public void onCreate(NoteView noteView, NoteInfoModel noteInfoModel){
        this.noteView = noteView;
        this.noteViewModel = interactor.loadViewModel(noteInfoModel);
        this.noteInfoModel = noteInfoModel;
        noteView.setViewTitle(noteInfoModel.getName());
        noteView.setText(noteViewModel.getText());
    }

    public void onStop(){
        NoteViewModelManager.save(noteView.getContext());
    }

    public void onBackPressed(){
        if (noteViewModel.hasChanges()){
            noteView.showExitDialog();
        } else {
            noteView.close();
            NoteViewModelManager.remove(noteView.getContext());
        }
    }

    public void onChooseSave(boolean needSave){
        if (needSave){
            interactor.setByName(noteInfoModel.getName(), noteViewModel.getNoteModel());
        }
        NoteViewModelManager.remove(noteView.getContext());
        noteView.close();
    }

    public void onTextChanged(String text){
        noteViewModel.setText(text);
        noteViewModel.setHasChanges(true);
    }
}
