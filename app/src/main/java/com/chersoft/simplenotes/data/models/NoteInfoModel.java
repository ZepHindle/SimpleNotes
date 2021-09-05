package com.chersoft.simplenotes.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.chersoft.simplenotes.data.models.NoteInfoModel.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class NoteInfoModel {

    public static final String TABLE_NAME = "notes_info";

    @PrimaryKey
    @NonNull
    public String name;
    public String date;
    public int backgroundColorIndex; // индекс цвета фона в таблице цветов
    public int fontColorIndex; // индекс цвета шрифта в табоице цветов

    public NoteInfoModel(@NonNull String name, String date, int backgroundColorIndex, int fontColorIndex){
        this.name = name;
        this.date = date;
        this.backgroundColorIndex = backgroundColorIndex;
        this.fontColorIndex = fontColorIndex;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getBackgroundColorIndex() {
        return backgroundColorIndex;
    }

    public int getFontColorIndex() {
        return fontColorIndex;
    }

    @Override
    public String toString() {
        return "NoteInfoModel{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", backgroundColorIndex=" + backgroundColorIndex +
                ", fontColorIndex=" + fontColorIndex +
                '}';
    }
}
