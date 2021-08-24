package com.chersoft.simplenotes.domain;

import androidx.annotation.Nullable;

import com.chersoft.simplenotes.data.NoteInfoModel;

public interface NoteInfoRepository {

    /**
     * Получает количество заметок.
     * @return количество заметок
     */
    int getCount();

    /**
     * Получает заметку по номеру.
     * @param index номер заметки
     * @return заметка с заданным номером
     */
    NoteInfoModel getByIndex(int index);

    /**
     * Возвращает индекс заданной модели и -1, если она не найдена.
     * @param model модель
     * @return индекс модели или -1, если она отсутствует
     */
    int findIndex(NoteInfoModel model);

    /**
     * Удаляет заметку с заданным номером.
     * @param index номер заметки, которую нужно удалить
     */
    void removeByIndex(int index);

    /**
     * Добавляет заметку.
     * @param model заметка
     * @return номер добавленной заметки
     */
    int add(NoteInfoModel model);

    /**
     * Проверяет, есть ли заметка с таким именем.
     * @param name имя заметки
     * @return есть, ли заметка с таким именем
     */
    boolean containsName(String name);

    @Nullable NoteInfoModel findByName(String name);
}
