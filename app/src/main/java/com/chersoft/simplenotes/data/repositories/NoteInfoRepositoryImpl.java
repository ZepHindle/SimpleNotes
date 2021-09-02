package com.chersoft.simplenotes.data.repositories;

import android.content.Context;

import androidx.room.Room;

import com.chersoft.simplenotes.data.db.NoteInfoDb;
import com.chersoft.simplenotes.data.models.NoteInfoModel;
import com.chersoft.simplenotes.data.db.NotesInfoDao;
import com.chersoft.simplenotes.domain.repositories.NoteInfoRepository;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Observable;

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
    public Completable save(List<NoteInfoModel> models) {
        return Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                dao.deleteAll();
                dao.insertAll(models.toArray(new NoteInfoModel[0]));
            }
        });
        //return this.dao.insertAll(models.toArray(new NoteInfoModel[0]));
    }

    @Override
    public Observable<List<NoteInfoModel>> load() {
        return Observable.fromCallable(new Callable<List<NoteInfoModel>>() {
            @Override
            public List<NoteInfoModel> call() throws Exception {
                return dao.getAll();
            }
        });
    }
}
