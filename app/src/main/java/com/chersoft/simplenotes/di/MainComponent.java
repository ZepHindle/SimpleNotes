package com.chersoft.simplenotes.di;

import com.chersoft.simplenotes.presentation.CreateAccountActivity;
import com.chersoft.simplenotes.presentation.LogInActivity;
import com.chersoft.simplenotes.presentation.NoteActivity;
import com.chersoft.simplenotes.presentation.NotesListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(NotesListActivity activity);
    void inject(NoteActivity noteActivity);
    void inject(CreateAccountActivity createAccountActivity);
    void inject(LogInActivity logInActivity);
}
