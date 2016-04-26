package com.example.nicolas.ccontrol;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutAppActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
    }

    public void back(View v) {
        onBackPressed();
    }
}
