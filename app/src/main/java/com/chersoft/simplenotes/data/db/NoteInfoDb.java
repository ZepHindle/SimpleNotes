package com.chersoft.simplenotes.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.chersoft.simplenotes.data.models.NoteInfoModel;

@Database(entities = NoteInfoModel.class, version = 1)
public abstract class NoteInfoDb extends RoomDatabase {
    public abstract NotesInfoDao getDao();
}
