package com.chersoft.simplenotes.domain.models;

public class Note {
    private String text;

    public Note(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
