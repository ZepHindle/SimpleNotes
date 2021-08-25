package com.chersoft.simplenotes.presentation.viewmodels;

import android.content.Context;

import com.chersoft.simplenotes.data.NoteInfoModel;
import com.chersoft.simplenotes.data.NoteModel;
import com.chersoft.simplenotes.domain.NoteInfoRepository;
import com.chersoft.simplenotes.domain.NoteRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class NoteViewModel implements Serializable {

    private static final String FILE_NAME = "cache.bin";

    private boolean hasChanges = false;
    private NoteModel noteModel = new NoteModel();

    public String getText() {
        return noteModel.getText();
    }

    public NoteModel getNoteModel() {
        return noteModel;
    }

    public boolean hasChanges() {
        return hasChanges;
    }

    public void setText(String text) {
        noteModel.setText(text);
    }

    public void setHasChanges(boolean hasChanges) {
        this.hasChanges = hasChanges;
    }

    private static NoteViewModel instance;

    public static NoteViewModel load(NoteRepository noteInfoRepository, NoteInfoModel noteModel, Context context){

        if (instance != null) return instance;

        // пробуем взять из файла
        try (InputStream in = context.openFileInput(FILE_NAME);
             ObjectInputStream objIn = new ObjectInputStream(in)) {
            instance = (NoteViewModel) objIn.readObject();
        } catch (IOException | ClassNotFoundException ex){}

        // загружаем из репозитория
        NoteModel tmpNoteModel = noteInfoRepository.getByName(noteModel.getName());

        instance = new NoteViewModel();

        if (tmpNoteModel != null)
            instance.setText(tmpNoteModel.getText());

        return instance;
    }

    public static void save(Context context){
        try (OutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
             ObjectOutputStream objOut = new ObjectOutputStream(outputStream)) {
            objOut.writeObject(instance);
        } catch (IOException ex){}
    }

    public static void remove(Context context){
        File file = context.getFileStreamPath(FILE_NAME);
        file.delete();
        instance = null;
    }
}
