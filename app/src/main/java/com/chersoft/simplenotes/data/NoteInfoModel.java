package com.chersoft.simplenotes.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static com.chersoft.simplenotes.data.NoteInfoModel.TABLE_NAME;

@Entity(tableName = TABLE_NAME)
public class NoteInfoModel implements Serializable {

    public static final String TABLE_NAME = "notes_info";

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String date;
    public int colorIndex; // индекс цвета в таблице цветов

    public NoteInfoModel(String name, String date){
        this.name = name;
        this.date = date;
        this.colorIndex = 0;
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
