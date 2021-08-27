package com.chersoft.simplenotes.presentation;

import android.app.Application;

import com.chersoft.simplenotes.dg.DaggerMainComponent;
import com.chersoft.simplenotes.dg.MainComponent;
import com.chersoft.simplenotes.dg.MainModule;

public class MainApplication extends Application {
    MainComponent mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
}
