package com.chersoft.simplenotes.data.repositories;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.models.NoteModel;
import com.chersoft.simplenotes.domain.repositories.NoteRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NoteRepositoryImpl implements NoteRepository {

    private static final String FILE_EXT = ".note";

    private final Context applicationContext;

    public NoteRepositoryImpl(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    @Nullable
    @Override
    public NoteModel getByName(String name) {
        try (FileInputStream in = applicationContext.openFileInput(name + FILE_EXT);
             ObjectInputStream objIn = new ObjectInputStream(in)){
            NoteModel noteModel = (NoteModel) objIn.readObject();
            return noteModel;
        } catch (IOException | ClassNotFoundException | ClassCastException ex){}
        return null;
    }

    @Override
    public void setByName(String name, NoteModel noteModel) {
        try (FileOutputStream out = applicationContext.openFileOutput(name + FILE_EXT, Context.MODE_PRIVATE);
             ObjectOutputStream objOut = new ObjectOutputStream(out)){
            objOut.writeObject(noteModel);
        } catch (IOException ex){}
    }

    @Override
    public void remove(String name) {
        File file = applicationContext.getFileStreamPath(name + FILE_EXT);
        file.delete();
    }

    @Override
    public void changeName(String oldName, String newName) {
        File file = applicationContext.getFileStreamPath(oldName + FILE_EXT);
        boolean flag = file.renameTo(applicationContext.getFileStreamPath(newName + FILE_EXT));
    }
}
