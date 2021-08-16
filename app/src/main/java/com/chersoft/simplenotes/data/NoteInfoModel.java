package com.chersoft.simplenotes.data;

import com.chersoft.simplenotes.utils.ColorTable;

import java.util.Date;
import java.util.Random;

public class NoteInfoModel {
    private String name;
    private Date date;
    private int colorIndex; // индекс цвета в таблице цветов

    //TODO: remove stub constructor
    public NoteInfoModel(){
        this.name = "note" + new Random().nextInt();
        this.date = new Date();
        this.colorIndex = Math.abs((new Random().nextInt())% ColorTable.getTableSize());
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
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
