package com.example.aquafill;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class About_App_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("About App");

        //TextView ourAim = (TextView) findViewById(R.id.abApp_AimSubHead);
        //ourAim.setPaintFlags(ourAim.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //test
    }
}
