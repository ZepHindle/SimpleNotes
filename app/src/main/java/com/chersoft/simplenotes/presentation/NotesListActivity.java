package com.chersoft.simplenotes.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteInfoRepository repository = new NoteInfoRepositoryStubImpl();
        presenter = new NotesListPresenter(this, repository);
        setUpUI();
        presenter.onCreate();
    }

    private void setUpUI(){
        recyclerView = findViewById(R.id.notes_list_recycler_view);
        recyclerView.setAdapter(new NotesListRecyclerAdapter(presenter));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
}