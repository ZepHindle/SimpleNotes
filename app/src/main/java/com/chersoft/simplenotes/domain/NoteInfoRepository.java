package com.chersoft.simplenotes.domain;

import com.chersoft.simplenotes.data.NoteInfoModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface NoteInfoRepository {
    Completable save(List<NoteInfoModel> models);
    Observable<List<NoteInfoModel>> load();
}
