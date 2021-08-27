package com.chersoft.simplenotes.domain;

import com.chersoft.simplenotes.data.NoteInfoModel;

import java.util.List;

public interface NoteInfoRepository {
    // TODO: сделать, чтобы возвращались Observable
    void save(List<NoteInfoModel> models);
    List<NoteInfoModel> load();
}
