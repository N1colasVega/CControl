package com.example.nicolas.ccontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //При создании меню - добавляем наше меню
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Действия дял пунктов меню
        if (id == R.id.sumeItem) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
