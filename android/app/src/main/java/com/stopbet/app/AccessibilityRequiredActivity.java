package com.stopbet.app;

import android.app.Activity;
import android.os.Bundle;

public class AccessibilityRequiredActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        finish(); // não faz mais nada
    }
}
