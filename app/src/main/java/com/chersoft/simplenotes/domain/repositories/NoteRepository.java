package com.chersoft.simplenotes.domain.repositories;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.models.NoteModel;

public interface NoteRepository {

    /**
     * Загружает заметку с заданным именем.
     * @param name имя
     * @return заметка
     */
    @Nullable NoteModel getByName(String name);

    /**
     * Сохраняет заметку с заданным именем.
     * @param name имя
     * @param noteModel заметка
     */
    void setByName(String name, NoteModel noteModel);

    /**
     * Удаляет заметку с заданным именем.
     * @param name имя
     */
    void remove(String name);

    /**
     * Меняет имя заметки
     * @param oldName старое имя
     * @param newName новое имя
     */
    void changeName(String oldName, String newName);
}
