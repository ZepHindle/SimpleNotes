package com.chersoft.simplenotes.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = NoteInfoModel.class, version = 1)
public abstract class NoteInfoDb extends RoomDatabase {
    public abstract NotesInfoDao getDao();
}
