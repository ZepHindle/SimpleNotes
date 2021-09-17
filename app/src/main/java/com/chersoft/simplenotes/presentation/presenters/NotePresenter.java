package com.chersoft.simplenotes.presentation.presenters;

import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.domain.interactors.NoteInteractor;
import com.chersoft.simplenotes.presentation.NoteView;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModel;
import com.chersoft.simplenotes.presentation.viewmodels.NoteViewModelManager;

import javax.inject.Inject;

public class NotePresenter {

    private final NoteInteractor interactor;

    private NoteView noteView;
    private NoteViewModel noteViewModel;
    private NoteInfo noteInfo;

    @Inject
    public NotePresenter(NoteInteractor noteInteractor){
        this.interactor = noteInteractor;
    }

    /**
     * Вызывается при вызове onCreate активности.
     * @param noteView view в MVP
     * @param noteInfo информация о заметке
     */
    public void onCreate(NoteView noteView, NoteInfo noteInfo){
        this.noteView = noteView;
        this.noteViewModel = interactor.loadViewModel(noteInfo);
        this.noteInfo = noteInfo;
        noteView.setViewTitle(noteInfo.getName());
        noteView.setText(noteViewModel.getText());
    }

    /**
     * Вызывается при вызове onStop активности.
     */
    public void onStop(){
        NoteViewModelManager.save(noteView.getContext());
    }

    /**
     * Вызывается при нажатии на кнопку "back".
     */
    public void onBackPressed(){
        if (noteViewModel.hasChanges()){
            noteView.showExitDialog();
        } else {
            noteView.close();
            NoteViewModelManager.remove(noteView.getContext());
        }
    }

    /**
     * Вызывается, когда пользователь выбрал сохранить/не сохранять в диалоге.
     * @param needSave нужно ли сохранить заметку
     */
    public void onChooseSave(boolean needSave){
        if (needSave){
            interactor.setByName(noteInfo.getName(), noteViewModel.getNoteModel());
        }
        NoteViewModelManager.remove(noteView.getContext());
        noteView.close();
    }

    /**
     * Вызывается при изменении текста.
     * @param text новый текст
     */
    public void onTextChanged(String text){
        if (!noteViewModel.getText().equals(text)){
            noteViewModel.setText(text);
            noteViewModel.setHasChanges(true);
        }
    }
}
