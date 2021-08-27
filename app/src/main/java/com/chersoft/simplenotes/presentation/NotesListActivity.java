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
import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.presentation.adapters.NotesListRecyclerAdapter;
import com.chersoft.simplenotes.presentation.fragments.NewNoteDialog;
import com.chersoft.simplenotes.presentation.presenters.NotesListPresenter;

public class NotesListActivity extends AppCompatActivity implements NotesListView{

    private static final String NEW_NOTE_DIALOG_TAG = "com.chersoft.newNoteDialogTag";

    private NotesListPresenter presenter;
    private RecyclerView recyclerView;

    // инициализация

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NoteInfoRepository repository = DomainSingleton.get().getRepository();
        presenter = new NotesListPresenter(this);
        ((MainApplication)getApplicationContext()).mainComponent.inject(presenter);
        setUpUI();
        presenter.onCreate();
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
                presenter.onNoteSwiped(vh.getModel());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public NotesListPresenter getPresenter() {
        return presenter;
    }

    // методы меню, диалогов и т.д.

    /**
     * Вызывается при завершении диалога.
     * @param noteName имя для новой заметки или null, если пользователь ввел некорректное имя
     */
    public void onNewNoteDialogDismiss(@Nullable String noteName){
        presenter.onNewNoteDialogDismiss(noteName);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.main_menu_item_add_note){
            presenter.onMainMenuAddNote();
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
    public void showNewNoteDialog() {
        FragmentManager fm = getSupportFragmentManager();
        NewNoteDialog dialog = new NewNoteDialog();
        dialog.show(fm, NEW_NOTE_DIALOG_TAG);
    }

    @Override
    public void startNoteActivity(NoteInfoModel noteInfoModel) {
        Intent intent = NoteActivity.createIntent(this, noteInfoModel);
        startActivity(intent);
    }

    @Override
    public void showToast(int stringResId) {
        Toast.makeText(this, stringResId, Toast.LENGTH_LONG).show();
    }
}