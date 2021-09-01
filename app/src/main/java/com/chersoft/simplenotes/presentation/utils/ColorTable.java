package com.chersoft.simplenotes.presentation.utils;

import android.graphics.Color;

import androidx.annotation.ColorInt;

import java.util.ArrayList;

public final class ColorTable {

    private static ArrayList<Integer> colors = new ArrayList<>();

    static {
        colors.add(Color.parseColor("#5e35b1"));
        colors.add(Color.parseColor("#3949ab"));
        colors.add(Color.parseColor("#1e88e5"));
        colors.add(Color.parseColor("#00897b"));
        colors.add(Color.parseColor("#43a047"));
        colors.add(Color.parseColor("#7cb342"));
        colors.add(Color.parseColor("#c0ca33"));
        colors.add(Color.parseColor("#ffb300"));
        colors.add(Color.parseColor("#f4511e"));
        colors.add(Color.parseColor("#6d4c41"));
    }

    public static int getTableSize(){
        return colors.size();
    }

    public static @ColorInt int getColor(int index){
        return colors.get(index);
    }
}
