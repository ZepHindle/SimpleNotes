package com.chersoft.simplenotes.domain;

import com.chersoft.simplenotes.data.NoteInfoModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class NotesListInteractor {
    private NoteInfoRepository noteInfoRepository;
    private NoteRepository noteRepository;

    @Inject
    public NotesListInteractor(NoteInfoRepository noteInfoRepository, NoteRepository noteRepository){
        this.noteInfoRepository = noteInfoRepository;
        this.noteRepository = noteRepository;
    }

    public Completable save(List<NoteInfoModel> models){
        return noteInfoRepository.save(models);
    }

    public Observable<List<NoteInfoModel>> load(){
        return noteInfoRepository.load();
    }

    public void removeNote(NoteInfoModel note){
        noteRepository.remove(note.getName());
    }

}
