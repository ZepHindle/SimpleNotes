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
    public int colorIndex; // индекс цвета в таблице цветов

    public NoteInfoModel(String name, String date, int colorIndex){
        this.name = name;
        this.date = date;
        this.colorIndex = colorIndex;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getColorIndex() {
        return colorIndex;
    }

    @Override
    public String toString() {
        return "NoteInfoModel{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
