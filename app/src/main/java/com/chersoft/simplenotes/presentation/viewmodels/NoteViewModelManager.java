package com.chersoft.simplenotes.presentation.viewmodels;

import android.content.Context;

import com.chersoft.simplenotes.data.NoteModel;
import com.chersoft.simplenotes.domain.NoteInfo;
import com.chersoft.simplenotes.domain.NoteRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class NoteViewModelManager {

    private static final String FILE_NAME = "cache.bin";

    private static NoteViewModel instance;

    public static NoteViewModel load(NoteRepository noteInfoRepository, NoteInfo noteInfo, Context context){

        if (instance != null) return instance;

        // пробуем взять из файла
        try (InputStream in = context.openFileInput(FILE_NAME);
             ObjectInputStream objIn = new ObjectInputStream(in)) {
            instance = (NoteViewModel) objIn.readObject();
        } catch (IOException | ClassNotFoundException ex){}

        // загружаем из репозитория
        NoteModel tmpNoteModel = noteInfoRepository.getByName(noteInfo.getName());

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
