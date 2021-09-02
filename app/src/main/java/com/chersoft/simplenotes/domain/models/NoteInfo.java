package com.chersoft.simplenotes.domain.models;

import java.io.Serializable;

public class NoteInfo implements Serializable {
    private String name;
    private String date;
    private int colorIndex; // индекс цвета в таблице цветов

    public NoteInfo(String name, String date, int colorIndex) {
        this.name = name;
        this.date = date;
        this.colorIndex = colorIndex;
    }

    public NoteInfo(String name, String date) {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setColorIndex(int colorIndex) {
        this.colorIndex = colorIndex;
    }
}
