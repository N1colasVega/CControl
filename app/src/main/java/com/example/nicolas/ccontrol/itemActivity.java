package com.example.nicolas.ccontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class itemActivity extends AppCompatActivity {
//Активити одного из пунктов списка категории
    //Тут будут фоточки, само описани и так далее
    TextView dateView,nameView;

    String date; //дата транзикции
    Double cous; //общая сумма
    String nameTrans;//Имя транзицкии

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        dateView = (TextView) findViewById(R.id.nameView);
        dateView = (TextView) findViewById(R.id.dateTextV);


    }
}
