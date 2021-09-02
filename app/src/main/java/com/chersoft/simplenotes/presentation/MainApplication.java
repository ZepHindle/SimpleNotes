package com.chersoft.simplenotes.presentation;

import android.app.Application;

import com.chersoft.simplenotes.di.DaggerMainComponent;
import com.chersoft.simplenotes.di.MainComponent;
import com.chersoft.simplenotes.di.MainModule;

public class MainApplication extends Application {
    MainComponent mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
}
