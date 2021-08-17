package com.chersoft.simplenotes.presentation.presenters;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.presentation.NotesListView;

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
        System.out.println("_STUB onMainMenuAddNote()");
    }

    /// методы для вызова из адаптера/ItemTouchHelper recycler view

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
