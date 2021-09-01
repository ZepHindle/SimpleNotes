package com.chersoft.simplenotes.presentation.presenters;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NotesListInteractor;
import com.chersoft.simplenotes.presentation.NotesListView;
import com.chersoft.simplenotes.presentation.viewmodels.NotesListViewModel;
import com.chersoft.simplenotes.utils.NoteNameValidation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscribers.BlockingBaseSubscriber;
import io.reactivex.schedulers.Schedulers;

public class NotesListPresenter {

    private NotesListView view;
    private NotesListInteractor interactor;
    private NotesListViewModel viewModel;

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
                .subscribe(new Observer<List<NoteInfoModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}

                    @Override
                    public void onNext(@NonNull List<NoteInfoModel> models) {
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
        interactor.removeNote(model);
        view.removeNote(index);
    }
}
