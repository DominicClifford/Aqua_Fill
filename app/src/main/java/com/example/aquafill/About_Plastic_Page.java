package com.example.aquafill;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class About_Plastic_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_plastic_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("About Plastic");
    }
}
