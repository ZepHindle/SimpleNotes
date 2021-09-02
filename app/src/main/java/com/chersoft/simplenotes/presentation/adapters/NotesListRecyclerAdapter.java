package com.chersoft.simplenotes.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chersoft.simplenotes.R;
import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.presentation.presenters.NotesListPresenter;
import com.chersoft.simplenotes.presentation.utils.ColorTable;

public class NotesListRecyclerAdapter extends RecyclerView.Adapter<NotesListRecyclerAdapter.NotesListViewHolder>{

    private final NotesListPresenter presenter;

    public NotesListRecyclerAdapter(NotesListPresenter presenter){
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public NotesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notes_list_item_layout, parent, false);
        return new NotesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListViewHolder holder, int position) {
        holder.bind(presenter.onGetNoteInfo(position));
    }

    @Override
    public int getItemCount() {
        return presenter.onGetCount();
    }

    public class NotesListViewHolder extends RecyclerView.ViewHolder{

        private NoteInfo noteInfo;
        private final TextView nameView;
        private final TextView dateView;

        public NotesListViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.notes_list_item_name_view);
            dateView = view.findViewById(R.id.notes_list_item_date_view);
            ImageButton menuButton = view.findViewById(R.id.notes_list_menu_button);

            PopupMenu menu = new PopupMenu(view.getContext(), menuButton);
            menu.inflate(R.menu.notes_list_item_popup_menu);
            menu.setOnMenuItemClickListener( menuItem -> {
                int id = menuItem.getItemId();
                if (id == R.id.popupmenu_delete){
                    NotesListRecyclerAdapter.this.presenter.onContextMenuDelete(getNoteInfo());
                } else if (id == R.id.popupmenu_edit){
                    NotesListRecyclerAdapter.this.presenter.onContextMenuEdit(getNoteInfo());
                }
                return false;
            });

            menuButton.setOnClickListener( v -> {
                menu.show();
            });

            view.setOnClickListener( v -> {
                NotesListRecyclerAdapter.this.presenter.onNotePress(getNoteInfo());
            });
        }

        public void bind(NoteInfo noteInfo){
            this.noteInfo = noteInfo;
            nameView.setText(noteInfo.getName());
            dateView.setText(noteInfo.getDate());
            this.itemView.setBackgroundColor(ColorTable.getColor(noteInfo.getColorIndex()));
        }

        public NoteInfo getNoteInfo() {
            return noteInfo;
        }
    }
}
