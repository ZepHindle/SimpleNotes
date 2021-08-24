package com.chersoft.simplenotes.presentation;

import android.app.Application;

import com.chersoft.simplenotes.dg.DaggerMainComponent;
import com.chersoft.simplenotes.dg.MainComponent;

public class MainApplication extends Application {

    MainComponent mainComponent = DaggerMainComponent.create();

}
