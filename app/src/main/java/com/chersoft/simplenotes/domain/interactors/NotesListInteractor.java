package com.chersoft.simplenotes.domain.interactors;

import com.chersoft.simplenotes.data.Service.LoadNoteResponse;
import com.chersoft.simplenotes.data.Service.Service;
import com.chersoft.simplenotes.data.models.NoteModel;
import com.chersoft.simplenotes.domain.models.NoteInfo;
import com.chersoft.simplenotes.domain.mappers.NoteInfoMapper;
import com.chersoft.simplenotes.domain.models.UserAccount;
import com.chersoft.simplenotes.domain.repositories.NoteInfoRepository;
import com.chersoft.simplenotes.domain.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;

public class NotesListInteractor {
    private final NoteInfoRepository noteInfoRepository;
    private final NoteRepository noteRepository;
    private final UserAccount userAccount;
    private final Service service;

    @Inject
    public NotesListInteractor(NoteInfoRepository noteInfoRepository, NoteRepository noteRepository,
                               UserAccount userAccount, Service service){
        this.noteInfoRepository = noteInfoRepository;
        this.noteRepository = noteRepository;
        this.userAccount = userAccount;
        this.service = service;
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

    public void changeName(String oldName, String newName){
        // менять NoteInfoRepository не нужно т.к. он каждый раз перезаписывается
        noteRepository.changeName(oldName, newName);
    }


    public boolean isLogined(){
        return userAccount.getPassword() != null;
    }

    public Single<ArrayList<LoadNoteResponse>> createNotesList(List<NoteInfo> noteInfos){
        return Single.fromCallable(() -> {
            ArrayList<LoadNoteResponse> result = new ArrayList<>(noteInfos.size());
            for (NoteInfo noteInfo : noteInfos){
                NoteModel noteModel = noteRepository.getByName(noteInfo.getName());

                if (noteModel == null) {
                    noteModel = new NoteModel();
                    noteModel.setText("");
                }

                result.add(new LoadNoteResponse(
                        noteInfo.getName(),
                        noteInfo.getDate(),
                        noteInfo.getBackgroundColorIndex(),
                        noteInfo.getFontColorIndex(),
                        noteModel.getText()
                ));
            }
            return result;
        });
    }

    public Call<Void> upload(ArrayList<LoadNoteResponse> list){
        return service.getServiceAPI().upload(userAccount.getUserName(), userAccount.getPassword(), list);
    }
}
