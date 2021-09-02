package com.chersoft.simplenotes.domain.interactors;

import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.domain.mappers.NoteInfoMapper;
import com.chersoft.simplenotes.domain.repositories.NoteInfoRepository;
import com.chersoft.simplenotes.domain.repositories.NoteRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class NotesListInteractor {
    private final NoteInfoRepository noteInfoRepository;
    private final NoteRepository noteRepository;

    @Inject
    public NotesListInteractor(NoteInfoRepository noteInfoRepository, NoteRepository noteRepository){
        this.noteInfoRepository = noteInfoRepository;
        this.noteRepository = noteRepository;
    }

    public Completable save(List<NoteInfo> noteInfos){
        return noteInfoRepository.save(NoteInfoMapper.reverseList(noteInfos));
    }

    public Observable<List<NoteInfo>> load(){
        return noteInfoRepository.load().map(NoteInfoMapper::mapList);
    }

    public void removeNote(NoteInfo note){
        noteRepository.remove(note.getName());
    }

}
