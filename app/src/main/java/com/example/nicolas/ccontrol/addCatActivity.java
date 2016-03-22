package com.example.nicolas.ccontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolas.ccontrol.R;
import com.example.nicolas.ccontrol.fragment.SecondFragment;

public class addCatActivity extends AppCompatActivity implements View.OnClickListener {
    Button Add;
    TextView catName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Класс добавления пунктов меню
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cat);
        Add = (Button) findViewById(R.id.Add);
        Add.setOnClickListener(this);

        catName = (TextView) findViewById(R.id.catName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Add:
                break;
        }
    }
}
