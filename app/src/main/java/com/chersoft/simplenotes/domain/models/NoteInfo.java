package com.chersoft.simplenotes.domain.models;

import java.io.Serializable;

public class NoteInfo implements Serializable {
    private String name;
    private String date;
    private int backgroundColorIndex; // индекс цвета фона в таблице цветов
    private int fontColorIndex; // индекс цвета шрифта в табоице цветов


    public NoteInfo(String name, String date, int backgroundColorIndex, int fontColorIndex) {
        this.name = name;
        this.date = date;
        this.backgroundColorIndex = backgroundColorIndex;
        this.fontColorIndex = fontColorIndex;
    }

    public NoteInfo(String name, String date) {
        this.name = name;
        this.date = date;
        this.backgroundColorIndex = 0;
        this.fontColorIndex = 1;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBackgroundColorIndex(int backgroundColorIndex) {
        this.backgroundColorIndex = backgroundColorIndex;
    }

    public void setFontColorIndex(int fontColorIndex) {
        this.fontColorIndex = fontColorIndex;
    }
}
