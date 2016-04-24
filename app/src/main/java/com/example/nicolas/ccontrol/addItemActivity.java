package com.example.nicolas.ccontrol;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addItemActivity extends AppCompatActivity implements View.OnClickListener {
    //Объекты
    Button addTrans;
    EditText nameEd,sumEd;
    //Классы
    controlBD bdcon = new controlBD();
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    int id;
    String name;
    Double sum;
    String year,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addTrans = (Button) findViewById(R.id.addTrans);
        nameEd = (EditText) findViewById(R.id.nameOfTrans);
        sumEd = (EditText) findViewById(R.id.sumOfTrans);

        Intent intent = getIntent();
        year = intent.getStringExtra("yyyy2");
        month = intent.getStringExtra("M2");
        id = intent.getIntExtra("categoryId",23);

        addTrans.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addTrans:
                mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                name = nameEd.getText().toString();
                sum =  Double.parseDouble(sumEd.getText().toString());
                bdcon.addTrans(mSqLiteDatabase,id,1,name,"",sum,"",year,month);
                finish();
                break;
        }
    }
}
