package com.chersoft.simplenotes.data.models;

import java.io.Serializable;

public class NoteModel implements Serializable {
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
