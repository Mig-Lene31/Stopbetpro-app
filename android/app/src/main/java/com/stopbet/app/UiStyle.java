package com.stopbet.app;

import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.TextView;

public class UiStyle {

    public static void applyTitle(TextView tv) {
        tv.setTextColor(0xFFFFFFFF);
        tv.setTextSize(22);
        tv.setTypeface(null, Typeface.BOLD);
    }

    public static void applyText(TextView tv) {
        tv.setTextColor(0xFFDDDDDD);
        tv.setTextSize(16);
        tv.setTypeface(null, Typeface.NORMAL);
    }

    public static void applyInput(EditText et) {
        et.setTextColor(0xFFFFFFFF);
        et.setHintTextColor(0xFFBBBBBB);
        et.setTextSize(18);
        et.setTypeface(null, Typeface.BOLD);
    }

    public static int background() {
        return 0xFF1E3A5F; // azul profissional padrão
    }
}
