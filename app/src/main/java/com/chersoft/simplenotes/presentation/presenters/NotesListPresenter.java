package com.chersoft.simplenotes.presentation.presenters;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.Service.LoadNoteResponse;
import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.domain.interactors.NotesListInteractor;
import com.chersoft.simplenotes.presentation.NotesListView;
import com.chersoft.simplenotes.presentation.viewmodels.NotesListViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesListPresenter {

    private NotesListView view;
    private final NotesListInteractor interactor;
    private final NotesListViewModel viewModel;

    private Disposable disposable;

    @Inject
    public NotesListPresenter(NotesListInteractor interactor, NotesListViewModel viewModel){
        this.interactor = interactor;
        this.viewModel = viewModel;
    }

    protected NotesListView getView(){
        return view;
    }

    // методы для вызова из NotesListActivity

    /**
     * Вызывается при вызове onCreate активности.
     * @param view view в MVP
     */
    public void onCreate(NotesListView view){
        this.view = view;
        view.setProgressBarVisible(true);
        interactor.load()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<NoteInfo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (disposable != null) disposable.dispose();
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull List<NoteInfo> models) {
                        viewModel.setNotes(new ArrayList<>(models));
                        view.updateNotes();
                        view.setProgressBarVisible(false);
                        disposable = null;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.setProgressBarVisible(false);
                        disposable = null;
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    /**
     * Вызывается при вызове onStop активности.
     */
    public void onStop(){
        if (disposable != null) disposable.dispose();
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

    /**
     * Вызывается при нажатии кнопки "add".
     */
    public void onAddButtonPress(){
        view.showNewNoteDialog();
    }

    /**
     * Вызывается при выборе пункта в главном меню "add note".
     */
    public void onMainMenuAddNote(){
        view.showNewNoteDialog();
    }

    /**
     * Вызывается при выборе пункта в главном меню "create account".
     */
    public void onMainMenuCreateAccount(){
        view.startCreateAccountActivity();
    }

    /**
     * Вызывается при выборе пункта в главном меню "log in".
     */
    public void onMainMenuLogIn(){
        view.startLogInActivity();
    }

    /**
     * Вызывается при выборе пункта в главном меню "upload".
     */
    public void onMainMenuUpload(){
        if (!interactor.isLogined()){
            view.showToast(R.string.you_need_to_login);
            return;
        }
        view.setProgressBarVisible(true);
        Disposable disposable = interactor.createNotesList(viewModel.getNotes())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<ArrayList<LoadNoteResponse>, Throwable>() {
                    @Override
                    public void accept(ArrayList<LoadNoteResponse> loadNoteResponses, Throwable throwable) throws Exception {
                        interactor.uploadFromServer(loadNoteResponses).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                if (response.isSuccessful()){
                                    view.showToast(R.string.notes_uploaded_successfully);
                                } else {
                                    view.showToast(R.string.server_error);
                                }
                                view.setProgressBarVisible(false);
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                view.showToast(R.string.server_error);
                                view.setProgressBarVisible(false);
                            }
                        });
                    }
                });
    }

    /**
     * Вызывается при выборе пункта в главном меню "load".
     */
    public void onMainMenuLoad(){
        if (!interactor.isLogined()){
            view.showToast(R.string.you_need_to_login);
            return;
        }
        view.setProgressBarVisible(true);
        interactor.loadFromServer().enqueue(new Callback<ArrayList<LoadNoteResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<LoadNoteResponse>> call, Response<ArrayList<LoadNoteResponse>> response) {
                if (!response.isSuccessful()){
                    view.showToast(R.string.server_error);
                    return;
                }
                ArrayList<LoadNoteResponse> body = response.body();
                ArrayList<NoteInfo> noteInfos = new ArrayList<>(body.size());
                for (LoadNoteResponse loadNoteResponse : body){
                    noteInfos.add(new NoteInfo(
                            loadNoteResponse.getName(),
                            loadNoteResponse.getDate(),
                            loadNoteResponse.getBackgroundColorIndex(),
                            loadNoteResponse.getFontColorIndex()
                    ));
                }
                viewModel.setNotes(noteInfos);
                view.updateNotes();
                interactor.saveNotes(body)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                view.setProgressBarVisible(false);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                view.setProgressBarVisible(false);
                            }
                        });

            }

            @Override
            public void onFailure(Call<ArrayList<LoadNoteResponse>> call, Throwable t) {
                view.showToast(R.string.server_error);
                view.setProgressBarVisible(false);
            }
        });
    }

    /**
     * Проверяет, содержит ли viewModel заметку с таким именем.
     * @param noteName имя заметки
     * @return содержит ли viewModel заметку с таким именем
     */
    public boolean onNoteContainsName(String noteName){
        return viewModel.containsName(noteName);
    }

    /**
     * Вызывается при завершении диалога "NewNoteDialog".
     * @param noteName имя новой заметки, выбранное в диалоге
     */
    public void onNewNoteDialogDismiss(@Nullable String noteName){
        if (noteName == null) return;
        // добавляем новую заметку
        int index = viewModel.add(new NoteInfo(noteName, new Date().toString()));
        view.addNote(index);
    }

    /**
     * Вызывается при завершении диалога "RenameNoteDialog".
     * @param oldName старое имя заметки
     * @param newName новое имя заметки
     */
    public void onRenameNoteDialogDismiss(String oldName, @Nullable String newName){
        if (newName == null) return;
        int index = viewModel.findIndexByName(oldName);
        NoteInfo noteInfo = viewModel.getByIndex(index);
        noteInfo.setName(newName);
        interactor.changeName(oldName, newName);
        view.updateNote(index);
    }

    /**
     * Вызывается при завершении диалога "NoteColorDialog".
     * @param oldName имя заметки
     * @param backgroundColorIndex индекс цвета фона заметки
     * @param fontColorIndex индекс цвета текста заметки
     */
    public void onNoteColorDialogDismiss(String oldName, int backgroundColorIndex, int fontColorIndex){
        int index = viewModel.findIndexByName(oldName);
        NoteInfo noteInfo = viewModel.getByIndex(index);
        noteInfo.setBackgroundColorIndex(backgroundColorIndex);
        noteInfo.setFontColorIndex(fontColorIndex);
        view.updateNote(index);
    }

    // методы для вызова из адаптера/ItemTouchHelper recycler view

    /**
     * Возвращает количество заметок во viewModel.
     * @return количество заметок во viewModel
     */
    public int onGetCount(){
        return viewModel.getCount();
    }

    /**
     * Возвращает заметку с заданным индексом из viewModel .
     * @param index индекс заметки
     * @return заметка
     */
    public NoteInfo onGetNoteInfo(int index){
        return viewModel.getByIndex(index);
    }

    /**
     * Вызывается при выборе пункте контекстного меню "edit".
     * @param noteInfo заметка
     */
    public void onContextMenuEdit(NoteInfo noteInfo){
        view.showNoteColorDialog(noteInfo);
    }

    /**
     * Вызывается при выборе пункте контекстного меню "rename".
     * @param noteInfo заметка
     */
    public void onContextMenuRename(NoteInfo noteInfo){
        view.showRenameNoteDialog(noteInfo);
    }

    /**
     * Вызывается при выборе пункте контекстного меню "delete".
     * @param noteInfo заметка
     */
    public void onContextMenuDelete(NoteInfo noteInfo){
        removeNote(noteInfo);
    }

    /**
     * Вызывается при свайпе вправо заметки.
     * @param noteInfo заметка
     */
    public void onNoteSwiped(NoteInfo noteInfo){
        removeNote(noteInfo);
    }

    /**
     * Вызывается при нажатии на заметку.
     * @param noteInfo заметка
     */
    public void onNotePress(NoteInfo noteInfo){
        getView().startNoteActivity(noteInfo);
    }

    /**
     * Удаляет заметку из viewModel.
     * @param note заметка
     */
    private void removeNote(NoteInfo note){
        int index = viewModel.findIndex(note);
        if (index < 0) return;
        viewModel.removeByIndex(index);
        interactor.removeNote(note);
        view.removeNote(index);
    }
}
