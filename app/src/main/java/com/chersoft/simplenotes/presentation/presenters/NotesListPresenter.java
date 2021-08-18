package com.chersoft.simplenotes.presentation.presenters;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.presentation.NotesListView;
import com.chersoft.simplenotes.utils.NoteNameValidation;

import java.util.Date;

public class NotesListPresenter {

    private final NotesListView view;
    private final NoteInfoRepository repository;

    public NotesListPresenter(NotesListView view, NoteInfoRepository repository){
        this.view = view;
        this.repository = repository;
    }

    protected NotesListView getView(){
        return view;
    }

    protected NoteInfoRepository getRepository() {
        return repository;
    }

    // методы для вызова из NotesListActivity

    public void onCreate(){

    }

    public void onStop(){

    }

    public void onMainMenuAddNote(){
        view.showNewNoteDialog();
    }

    public boolean onNewNoteDialogPositiveButtonPressed(String noteName){
        if (!NoteNameValidation.noteNameIsValid(noteName)){
            getView().showToast(R.string.note_bad_name);
            return false;
        }
        if (repository.containsName(noteName)){
            getView().showToast(R.string.note_already_exists);
            return false;
        }
        // добавляем новую заметку
        int index = repository.add(new NoteInfoModel(noteName, new Date()));
        view.addNote(index);
        return true;
    }

    // методы для вызова из адаптера/ItemTouchHelper recycler view

    public int onGetCount(){
        return repository.getCount();
    }

    public NoteInfoModel onGetNoteInfo(int index){
        return repository.getByIndex(index);
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


    private void removeNote(NoteInfoModel model){
        int index = repository.findIndex(model);
        if (index < 0) return;
        repository.removeByIndex(index);
        view.removeNote(index);
    }
}
