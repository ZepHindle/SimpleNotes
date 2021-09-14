package com.chersoft.simplenotes.presentation.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import java.util.ArrayList;

public final class ColorTable {

    private static final ArrayList<Integer> colors = new ArrayList<>();

    static {
        colors.add(Color.parseColor("#FFFFFF"));
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

    /**
     * Возвращает количество цветов за таблице.
     * @return количество цветов в таблице
     */
    public static int getTableSize(){
        return colors.size();
    }

    /**
     * Возвращает цвет в таблице с заданным индексом.
     * @param index индекс цвета
     * @return цвет
     */
    public static @ColorInt int getColor(int index){
        return colors.get(index);
    }

    /**
     * Задает view цвет фона и цвет текста.
     * @param view view
     * @param backgroundColorIndex индекс цвета фона
     * @param fontColorIndex индекс цвета текста
     */
    public static void customize(View view, int backgroundColorIndex, int fontColorIndex){
        view.setBackgroundColor(getColor(backgroundColorIndex));
        if (view instanceof TextView){
            TextView textView = (TextView) view;
            textView.setTextColor(getColor(fontColorIndex));
        }
    }
}
