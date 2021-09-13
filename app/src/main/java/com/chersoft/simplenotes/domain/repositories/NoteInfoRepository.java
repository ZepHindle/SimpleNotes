package com.chersoft.simplenotes.domain.repositories;

import com.chersoft.simplenotes.data.models.NoteInfoModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface NoteInfoRepository {
    /**
     * Сохраняет заметки.
     * @param models заметки
     * @return completable
     */
    Completable save(List<NoteInfoModel> models);

    /**
     * Загружает заметки.
     * @return observable
     */
    Observable<List<NoteInfoModel>> load();
}
