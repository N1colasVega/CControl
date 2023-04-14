package com.example.nicolas.ccontrol.add_delete;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolas.ccontrol.data_base_control.ControlBD;
import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;
import com.example.nicolas.ccontrol.R;

public class AddCatActivity extends AppCompatActivity implements View.OnClickListener {
    //Объекты
    Button Add;
    TextView catName;
    //Классы
    ControlBD bdcon = new ControlBD();
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    String name;
    String yyyy,M;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Класс добавления пунктов меню
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cat);
        Add = (Button) findViewById(R.id.addCat);
        Add.setOnClickListener(this);

        Intent intent = getIntent();
        yyyy = intent.getStringExtra("getyyyy1");
        M = intent.getStringExtra("getM1");

        catName = (TextView) findViewById(R.id.catName);

        mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addCat:
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                name = catName.getText().toString();
                bdcon.addCat(mSqLiteDatabase, name, "", "",yyyy,M);
                mSqLiteDatabase.close();
                finish();
                break;
        }
    }
}
