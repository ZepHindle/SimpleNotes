package com.chersoft.simplenotes.data;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.room.Room;

import com.chersoft.simplenotes.domain.NoteInfoRepository;

import java.util.ArrayList;
import java.util.List;

public class NoteInfoRepositoryImpl implements NoteInfoRepository {

    private NoteInfoDb database;
    private NotesInfoDao dao;

    public NoteInfoRepositoryImpl(Context applicationContext){
        this.database = Room.databaseBuilder(
                applicationContext,
                NoteInfoDb.class,
                NoteInfoModel.TABLE_NAME
        ).build();
        this.dao = database.getDao();
    }

    @Override
    public void save(List<NoteInfoModel> models) {
        this.dao.insertAll(models.toArray(new NoteInfoModel[0]));
    }

    @Override
    public List<NoteInfoModel> load() {
        return new ArrayList<>(this.dao.getAll());
    }
}
