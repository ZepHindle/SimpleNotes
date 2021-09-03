package com.chersoft.simplenotes.presentation.presenters;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.domain.interactors.NotesListInteractor;
import com.chersoft.simplenotes.presentation.NotesListView;
import com.chersoft.simplenotes.presentation.viewmodels.NotesListViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NotesListPresenter {

    private NotesListView view;
    private final NotesListInteractor interactor;
    private final NotesListViewModel viewModel;

    public NotesListPresenter(NotesListInteractor interactor, NotesListViewModel viewModel){
        this.interactor = interactor;
        this.viewModel = viewModel;
    }

    protected NotesListView getView(){
        return view;
    }

    // методы для вызова из NotesListActivity

    public void onCreate(NotesListView view){
        this.view = view;
        interactor.load()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NoteInfo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull List<NoteInfo> models) {
                        viewModel.setNotes(new ArrayList<>(models));
                        view.updateNotes();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    public void onStop(){
        interactor.save(viewModel.getNotes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showToast(R.string.fail_write_to_db);
                    }
                });
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
        int index = viewModel.add(new NoteInfo(noteName, new Date().toString()));
        view.addNote(index);
    }

    public void onRenameNoteDialogDismiss(String oldName, @Nullable String newName){
        if (newName == null) return;
        int index = viewModel.findIndexByName(oldName);
        NoteInfo noteInfo = viewModel.getByIndex(index);
        noteInfo.setName(newName);
        interactor.changeName(oldName, newName);
        view.updateNote(index);
    }

    // методы для вызова из адаптера/ItemTouchHelper recycler view

    public int onGetCount(){
        return viewModel.getCount();
    }

    public NoteInfo onGetNoteInfo(int index){
        return viewModel.getByIndex(index);
    }

    public void onContextMenuEdit(NoteInfo noteInfo){
        // TODO: !!!
        System.out.println("_STUB onContextMenuEdit with model: " + noteInfo);
    }

    public void onContextMenuRename(NoteInfo noteInfo){
        view.showRenameNoteDialog(noteInfo);
    }

    public void onContextMenuDelete(NoteInfo noteInfo){
        removeNote(noteInfo);
    }

    public void onNoteSwiped(NoteInfo noteInfo){
        removeNote(noteInfo);
    }

    public void onNotePress(NoteInfo noteInfo){
        getView().startNoteActivity(noteInfo);
    }


    private void removeNote(NoteInfo model){
        int index = viewModel.findIndex(model);
        if (index < 0) return;
        viewModel.removeByIndex(index);
        interactor.removeNote(model);
        view.removeNote(index);
    }
}
