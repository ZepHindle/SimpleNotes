package com.chersoft.simplenotes.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
