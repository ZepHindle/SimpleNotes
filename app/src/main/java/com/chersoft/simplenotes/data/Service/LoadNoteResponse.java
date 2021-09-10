package com.chersoft.simplenotes.data.Service;

import java.io.Serializable;

public class LoadNoteResponse implements Serializable {
    private String name;
    private String date;
    private int backgroundColorIndex;
    private int fontColorIndex;
    private String note;

    public LoadNoteResponse(){}

    public LoadNoteResponse(String name, String date, int backgroundColorIndex, int fontColorIndex, String note) {
        this.name = name;
        this.date = date;
        this.backgroundColorIndex = backgroundColorIndex;
        this.fontColorIndex = fontColorIndex;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBackgroundColorIndex() {
        return backgroundColorIndex;
    }

    public void setBackgroundColorIndex(int backgroundColorIndex) {
        this.backgroundColorIndex = backgroundColorIndex;
    }

    public int getFontColorIndex() {
        return fontColorIndex;
    }

    public void setFontColorIndex(int fontColorIndex) {
        this.fontColorIndex = fontColorIndex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
