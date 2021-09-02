package com.chersoft.simplenotes.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.chersoft.simplenotes.data.models.NoteInfoModel;

import java.util.List;

@Dao
public interface NotesInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(NoteInfoModel... notes);

    @Query("SELECT * FROM notes_info")
    List<NoteInfoModel> getAll();

    @Query("DELETE FROM notes_info")
    void deleteAll();
}
