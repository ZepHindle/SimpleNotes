package com.chersoft.simplenotes.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.presentation.adapters.NotesListRecyclerAdapter;
import com.chersoft.simplenotes.presentation.fragments.NewNoteDialog;
import com.chersoft.simplenotes.presentation.fragments.NoteColorDialog;
import com.chersoft.simplenotes.presentation.fragments.RenameNoteDialog;
import com.chersoft.simplenotes.presentation.presenters.NotesListPresenter;

import javax.inject.Inject;

public class NotesListActivity extends AppCompatActivity implements NotesListView{

    private static final String NEW_NOTE_DIALOG_TAG = "com.chersoft.newNoteDialogTag";
    private static final String RENAME_NOTE_DIALOG_TAG = "com.chersoft.renameDialogTag";
    private static final String NOTE_COLOR_DIALOG_TAG = "com.chersoft.noteColorDialogTag";

    @Inject
    NotesListPresenter presenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((MainApplication)getApplicationContext()).mainComponent.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();
        presenter.onCreate(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.notes_list_main_menu, menu);
        return true;
    }

    private void setUpUI(){
        recyclerView = findViewById(R.id.notes_list_recycler_view);
        recyclerView.setAdapter(new NotesListRecyclerAdapter(presenter));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                NotesListRecyclerAdapter.NotesListViewHolder vh = (NotesListRecyclerAdapter.NotesListViewHolder) viewHolder;
                presenter.onNoteSwiped(vh.getNoteInfo());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public NotesListPresenter getPresenter() {
        return presenter;
    }

    // методы меню, диалогов и т.д.

    /**
     * Вызывается при завершении диалога создания новой хаметки.
     * @param noteName имя для новой заметки или null, если пользователь ввел некорректное имя
     */
    public void onNewNoteDialogDismiss(@Nullable String noteName){
        presenter.onNewNoteDialogDismiss(noteName);
    }

    /**
     * Вызывается при завершении диалога переименования заметки.
     * @param oldName старое имя заметки
     * @param newName новое имя заметки
     */
    public void onRenameNoteDialogDismiss(String oldName, @Nullable String newName){
        presenter.onRenameNoteDialogDismiss(oldName, newName);
    }

    /**
     * Вызывается при завершении диалога изменения цветов заметки.
     * @param oldName старое имя заметки
     * @param backgroundColorIndex новый индекс цвета фона в таблице цветов
     * @param fontColorIndex новаый индекс цвета шрифта в таблицу цветов
     */
    public void onNoteColorDialogDismiss(String oldName, int backgroundColorIndex, int fontColorIndex){
        presenter.onNoteColorDialogDismiss(oldName, backgroundColorIndex, fontColorIndex);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_menu_item_add_note){
            presenter.onMainMenuAddNote();
        } else if (id == R.id.main_menu_item_create_account){
            presenter.onMainMenuCreateAccount();
        } else if (id == R.id.main_menu_item_login){
            presenter.onMainMenuLogIn();
        }
        return super.onOptionsItemSelected(item);
    }

    // реализация NotesListView

    @Override
    public void addNote(int index) {
        recyclerView.getAdapter().notifyItemInserted(index);
    }

    @Override
    public void removeNote(int index) {
        recyclerView.getAdapter().notifyItemRemoved(index);
    }

    @Override
    public void updateNote(int index) {
        recyclerView.getAdapter().notifyItemChanged(index);
    }

    @Override
    public void updateNotes() {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showNewNoteDialog() {
        FragmentManager fm = getSupportFragmentManager();
        NewNoteDialog dialog = new NewNoteDialog();
        dialog.show(fm, NEW_NOTE_DIALOG_TAG);
    }

    @Override
    public void showRenameNoteDialog(NoteInfo noteInfo) {
        FragmentManager fm = getSupportFragmentManager();
        RenameNoteDialog dialog = RenameNoteDialog.newInstance(noteInfo);
        dialog.show(fm, RENAME_NOTE_DIALOG_TAG);
    }

    @Override
    public void showNoteColorDialog(NoteInfo noteInfo) {
        FragmentManager fm = getSupportFragmentManager();
        NoteColorDialog dialog = NoteColorDialog.newInstance(noteInfo);
        dialog.show(fm, NOTE_COLOR_DIALOG_TAG);
    }

    @Override
    public void startNoteActivity(NoteInfo noteInfo) {
        Intent intent = NoteActivity.createIntent(this, noteInfo);
        startActivity(intent);
    }

    @Override
    public void startCreateAccountActivity() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void startLogInActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    @Override
    public void showToast(int stringResId) {
        Toast.makeText(this, stringResId, Toast.LENGTH_LONG).show();
    }
}