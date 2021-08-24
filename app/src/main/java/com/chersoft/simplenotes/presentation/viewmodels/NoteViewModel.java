package com.chersoft.simplenotes.presentation.viewmodels;

import android.content.Context;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;

import java.io.Serializable;

public class NoteViewModel implements Serializable {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private static NoteViewModel instance;

    public static NoteViewModel load(NoteInfoRepository noteInfoRepository, NoteInfoModel noteModel, Context context){

        if (instance != null) return instance;

        return null;

    }

    public static void save(Context context){

    }

    public static void remove(Context context){

    }
}
