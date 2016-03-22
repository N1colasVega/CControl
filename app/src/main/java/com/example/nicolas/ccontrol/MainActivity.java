package com.example.nicolas.ccontrol;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Объекты
    Button bJanuary,bFebruary,bMarch,bApril,bMay,bJune,bJuly,bAugust,bSeptember,bOctober,bNovember,bDecember;
    TextView Year,sumYear,costYear;
    //Массивы/Коллекции
    ArrayList<Button> setButtom = new ArrayList<>();
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    Double sYear;
    int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Year = (TextView) findViewById(R.id.Year);//Поле - текущий год
        sumYear = (TextView) findViewById(R.id.sumYear);//Поле - доход за год
        costYear = (TextView) findViewById(R.id.costYear);//Поле - расходы за год

        //===============================================================================
        //ОБРАБОТЧИКИ КНОПОК
        bJanuary = (Button) findViewById(R.id.bJanuary);
        bFebruary = (Button) findViewById(R.id.bFebruary);
        bMarch = (Button) findViewById(R.id.bMarch);
        bApril = (Button) findViewById(R.id.bApril);
        bMay = (Button) findViewById(R.id.bMay);
        bJune = (Button) findViewById(R.id.bJune);
        bJuly = (Button) findViewById(R.id. bJuly);
        bAugust = (Button) findViewById(R.id.bAugust);
        bSeptember = (Button) findViewById(R.id.bSeptember);
        bOctober = (Button) findViewById(R.id.bOctober);
        bNovember = (Button) findViewById(R.id.bNovember);
        bDecember = (Button) findViewById(R.id.bDecember);

        setButtom.add(bJanuary);
        setButtom.add(bFebruary);
        setButtom.add(bMarch);
        setButtom.add(bApril);
        setButtom.add(bMay);
        setButtom.add(bJune);
        setButtom.add(bJuly);
        setButtom.add(bAugust);
        setButtom.add(bSeptember);
        setButtom.add(bOctober);
        setButtom.add(bNovember);
        setButtom.add(bDecember);

        for (Button b : setButtom){ b.setOnClickListener(this); }
        //===============================================================================
        //Обработка кнопки в правом нижнем углу
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //При создании меню - добавляем наше меню
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Действия дял пунктов меню
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {//Обработчик нажатия кнопок
        Intent intent;
        switch (v.getId()) {//Действия кнопок
            case R.id.bJanuary:
                intent = new Intent(this,diagramActivity.class);
                startActivity(intent);
                break;
            case R.id.bFebruary:
                break;
            case R.id.bMarch:
                break;
            case R.id.bApril:
                break;
            case R.id.bMay:
                break;
            case R.id.bJune:
                break;
            case R.id.bJuly:
                break;
            case R.id.bAugust:
                break;
            case R.id.bSeptember:
                break;
            case R.id.bOctober:
                break;
            case R.id.bNovember:
                break;
            case R.id.bDecember:
                break;
        }
    }
    //ПРИМЕРЫ РАБОТЫ С БД
    public void example(){
        //СОЗДАНИЕ БАЗЫ ДАННЫХ
        //юзаем конструктор
        mDatabaseHelper = new DatabaseHelper(this, "mydatabase.db", null, 1);//используем простой конструктор(не для даунов,а простой)
        //присваиваем базу
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        //Пример заполнения баз данных
        ContentValues values = new ContentValues();
        // Задайте значения для каждого столбца
        values.put(DatabaseHelper.NAME1_COLUMN, "11111");
        values.put(DatabaseHelper.NAME2_COLUMN, "111111");
        values.put(DatabaseHelper.NAME3_COLUMN, "1111111");
        // Вставляем данные в таблицу
        mSqLiteDatabase.insert("cats", null, values);

        //Пример чтения баз данных
        Cursor cursor = mSqLiteDatabase.query("nameTabel", new String[] {DatabaseHelper.NAME1_COLUMN,
                        DatabaseHelper.NAME2_COLUMN, DatabaseHelper.NAME3_COLUMN},
                null, null,
                null, null, null) ;

        cursor.moveToFirst();

        String Name1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.NAME1_COLUMN));
        int Name2 = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.NAME2_COLUMN));
        int Name3 = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.NAME3_COLUMN));

        //Закрываем курсор
        cursor.close();

        //Второй способ добавления данных(Прямой запрос)
        // ========================================================
        DatabaseHelper db = new DatabaseHelper(this);
        SQLiteDatabase sqdb = db.getWritableDatabase();

        String insertQuery = "INSERT INTO " +
                DatabaseHelper.DATABASE_TABLE +
                " (" + DatabaseHelper.NAME1_COLUMN + ") VALUES ('Васька')";
        sqdb.execSQL(insertQuery);
        // ========================================================
        //Изменение данных(Можно исопльзовать сложные условия и преобразовывать инты в строки)
        ContentValues valuese = new ContentValues();
        values.put(DatabaseHelper.NAME1_COLUMN, "1111111");
        mSqLiteDatabase.update(mDatabaseHelper.DATABASE_TABLE,
                valuese,
                mDatabaseHelper.NAME1_COLUMN + "= ?", new String[]{"2222222"});
    }
}
