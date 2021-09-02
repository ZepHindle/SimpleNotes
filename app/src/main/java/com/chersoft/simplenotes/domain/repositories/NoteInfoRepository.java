package com.chersoft.simplenotes.domain.repositories;

import com.chersoft.simplenotes.data.models.NoteInfoModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface NoteInfoRepository {
    Completable save(List<NoteInfoModel> models);
    Observable<List<NoteInfoModel>> load();
}
