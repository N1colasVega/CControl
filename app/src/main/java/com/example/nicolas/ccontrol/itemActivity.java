package com.example.nicolas.ccontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class itemActivity extends AppCompatActivity {
//Активити одного из пунктов списка категории
    //Тут будут фоточки, само описани и так далее
    TextView dateView,nameView,costView;

    //Классы
    controlBD bdcon = new controlBD();
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    int id;
    String date; //дата транзикции
    Double cous; //общая сумма
    String nameTrans;//Имя транзицкии
    String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        nameView = (TextView) findViewById(R.id.nameView1);
        dateView = (TextView) findViewById(R.id.dateTextV1);
        costView = (TextView) findViewById(R.id.costView);

        Intent intent = getIntent();
        id = intent.getIntExtra("transid",23);

        mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_TRANS,null, "transaction_id = " + id , null, null, null, null, null);
        if(cursor.moveToFirst()){
            int transName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM);
            int transDate = cursor.getColumnIndex(DatabaseHelper.DATE_COLUM);
            int transSum = cursor.getColumnIndex(DatabaseHelper.SUM_COLUM);
            do{
                format = "yyyy.MM.dd.hh:mm";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                String dataTime = simpleDateFormat.format(cursor.getFloat(transDate));
                nameView.setText(cursor.getString(transName));
                dateView.setText("Дата добавления : " + dataTime);
                costView.setText("Стоимость - " + cursor.getDouble(transSum));
            }while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        mSqLiteDatabase.close();

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
