package com.example.aquafill;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Settings_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings__page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Settings");
    }
}
