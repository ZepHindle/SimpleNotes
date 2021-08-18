package com.chersoft.simplenotes.domain;

import com.chersoft.simplenotes.data.NoteInfoModel;

public class DomainSingleton {

    private final NoteInfoRepository repository;

    private DomainSingleton(){
        repository = new NoteInfoRepositoryStubImpl();

        // TODO: remove
        for (int i = 0; i<20; i++)
            repository.add(new NoteInfoModel());
    }


    public NoteInfoRepository getRepository() {
        return repository;
    }


    private static DomainSingleton instance;

    public static DomainSingleton get(){
        if (instance == null){
            instance = new DomainSingleton();
        }
        return instance;
    }
}
