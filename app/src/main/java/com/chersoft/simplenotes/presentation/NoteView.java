package com.chersoft.simplenotes.presentation;

import android.content.Context;

public interface NoteView {
    Context getContext();
    void setText(String text);
    void setViewTitle(String title);
}
