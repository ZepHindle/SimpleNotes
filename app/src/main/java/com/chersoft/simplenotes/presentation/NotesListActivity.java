package com.chersoft.simplenotes.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NoteInfoRepositoryStubImpl;
import com.chersoft.simplenotes.presentation.adapters.NotesListRecyclerAdapter;
import com.chersoft.simplenotes.presentation.presenters.NotesListPresenter;

public class NotesListActivity extends AppCompatActivity implements NotesListView{

    private NotesListPresenter presenter;

    private RecyclerView recyclerView;

    // инициализация

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteInfoRepository repository = new NoteInfoRepositoryStubImpl();
        presenter = new NotesListPresenter(this, repository);
        setUpUI();
        presenter.onCreate();

        // TODO: tmp thing
        for (int i = 0; i<10000; i++)
            repository.add(new NoteInfoModel());
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

    // методы меню, диалогов и т.д.

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
}