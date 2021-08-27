package com.chersoft.simplenotes.presentation.presenters;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.presentation.NotesListView;
import com.chersoft.simplenotes.presentation.viewmodels.NotesListViewModel;
import com.chersoft.simplenotes.utils.NoteNameValidation;

import java.util.Date;

import javax.inject.Inject;

public class NotesListPresenter {

    private final NotesListView view;

    @Inject
    NoteInfoRepository repository;
    @Inject
    NotesListViewModel viewModel;

    public NotesListPresenter(NotesListView view){
        this.view = view;
    }

    protected NotesListView getView(){
        return view;
    }

    protected NoteInfoRepository getRepository() {
        return repository;
    }

    // методы для вызова из NotesListActivity

    public void onCreate(){
        // TODO: repository: load
        //viewModel.loadFromRepository(repository);
    }

    public void onStop(){
        // TODO: repository: save
        //viewModel.saveToRepository(repository);
    }

    public void onMainMenuAddNote(){
        view.showNewNoteDialog();
    }

    public boolean onNoteContainsName(String noteName){
        return viewModel.containsName(noteName);
    }

    public void onNewNoteDialogDismiss(@Nullable String noteName){
        if (noteName == null) return;
        // добавляем новую заметку
        int index = viewModel.add(new NoteInfoModel(noteName, new Date().toString()));
        view.addNote(index);
    }

    // методы для вызова из адаптера/ItemTouchHelper recycler view

    public int onGetCount(){
        return viewModel.getCount();
    }

    public NoteInfoModel onGetNoteInfo(int index){
        return viewModel.getByIndex(index);
    }

    public void onContextMenuEdit(NoteInfoModel model){
        // TODO: !!!
        System.out.println("_STUB onContextMenuEdit with model: " + model);
    }

    public void onContextMenuDelete(NoteInfoModel model){
        removeNote(model);
    }

    public void onNoteSwiped(NoteInfoModel model){
        removeNote(model);
    }

    public void onNotePress(NoteInfoModel noteInfoModel){
        getView().startNoteActivity(noteInfoModel);
    }


    private void removeNote(NoteInfoModel model){
        int index = viewModel.findIndex(model);
        if (index < 0) return;
        viewModel.removeByIndex(index);
        view.removeNote(index);
    }
}
