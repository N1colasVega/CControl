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
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Объекты
    Button bJanuary,bFebruary,bMarch,bApril,bMay,bJune,bJuly,bAugust,bSeptember,bOctober,bNovember,bDecember;
    TextView Year,sumYear,costYear;
    //Массивы/Коллекции
    ArrayList<Button> setButtom = new ArrayList<>();
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Классы
    controlBD bdcon = new controlBD();
    //Переменные
    String format;
    String year, month;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createBase();

        Year = (TextView) findViewById(R.id.Year);//Поле - текущий год
        sumYear = (TextView) findViewById(R.id.sumYear);//Поле - доход за год
        costYear = (TextView) findViewById(R.id.costYear);//Поле - расходы за год

        format = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dataTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        Year.setText("Год : " + dataTime);
        year = dataTime;
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
                month = "1";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bFebruary:
                month = "2";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bMarch:
                month = "3";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bApril:
                month = "4";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bMay:
                month = "5";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bJune:
                month = "6";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bJuly:
                month = "7";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bAugust:
                month = "8";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bSeptember:
                month = "9";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bOctober:
                month = "10";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bNovember:
                month = "11";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bDecember:
                month = "12";
                intent = new Intent(this,diagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
        }
    }

    public  void createBase(){

        mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();


        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_CAT,null, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            int catIndex = cursor.getColumnIndex(DatabaseHelper.CAT_ID2);
            int catName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM2);
            int descCat = cursor.getColumnIndex(DatabaseHelper.DESC_COLUM2);
            int imgCat = cursor.getColumnIndex(DatabaseHelper.IMG_COLUM);
            int statCat = cursor.getColumnIndex(DatabaseHelper.STATUS_COLUM);
            int dateCat = cursor.getColumnIndex(DatabaseHelper.DATE_ADD_COLUM);
            int locDate = cursor.getColumnIndex(DatabaseHelper.DATE_COLUM_LOCAL2);
            do{
                Log.d("mLog",
                        "ID = " + cursor.getInt(catIndex)
                        + ", title " + cursor.getString(catName)
                        + ", desc " + cursor.getString(descCat)
                        + ", img " + cursor.getString(imgCat)
                        + ", status " + cursor.getInt(statCat)
                        + ", date " + cursor.getLong(dateCat)
                        + ", LocalDAte " + cursor.getString(locDate)
                );
            }while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");

        cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_TRANS,null, null, null, null, null, null, null);
        if(cursor.moveToFirst()){
            int transIndex = cursor.getColumnIndex(DatabaseHelper.TRANS_ID);
            int catIndex = cursor.getColumnIndex(DatabaseHelper.CAT_ID);
            int sourceIndex = cursor.getColumnIndex(DatabaseHelper.SOURCE_ID);
            int transName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM);
            int descTrans = cursor.getColumnIndex(DatabaseHelper.DESC_COLUM);
            int sum = cursor.getColumnIndex(DatabaseHelper.SUM_COLUM);
            int adress = cursor.getColumnIndex(DatabaseHelper.ADRESS_COLUM);
            int dateCat = cursor.getColumnIndex(DatabaseHelper.DATE_COLUM);
            int locDate = cursor.getColumnIndex(DatabaseHelper.DATE_COLUM_LOCAL);
            do{
                Log.d("mLog",
                        "ID = " + cursor.getInt(transIndex)
                        +", Cat_id = " + cursor.getInt(catIndex)
                        +", Sour_id = " + cursor.getInt(sourceIndex)
                                + ", title " + cursor.getString(transName)
                                + ", desc " + cursor.getString(descTrans)
                                + ", sum " + cursor.getDouble(sum)
                                + ", adress " + cursor.getInt(adress)
                                + ", date " + cursor.getLong(dateCat)
                                + ", LocalDAte " + cursor.getString(locDate)
                );
            }while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        mSqLiteDatabase.close();
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
        values.put(DatabaseHelper.TITLE_COLUM, "11111");
        values.put(DatabaseHelper.DATE_COLUM, "111111");
        values.put(DatabaseHelper.ADRESS_COLUM, "1111111");
        // Вставляем данные в таблицу
        mSqLiteDatabase.insert("transaction", null, values);

        //Пример чтения баз данных
        Cursor cursor = mSqLiteDatabase.query("nameTabel", new String[] {DatabaseHelper.TITLE_COLUM,
                        DatabaseHelper.DATE_COLUM, DatabaseHelper.ADRESS_COLUM},
                null, null,
                null, null, null) ;

        cursor.moveToFirst();

        String Name1 = cursor.getString(cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM));
        int Name2 = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.DATE_COLUM));
        int Name3 = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ADRESS_COLUM));

        //Закрываем курсор
        cursor.close();

        //Второй способ добавления данных(Прямой запрос)
        // ========================================================
        DatabaseHelper db = new DatabaseHelper(this);
        SQLiteDatabase sqdb = db.getWritableDatabase();

        String insertQuery = "INSERT INTO " +
                DatabaseHelper.TABLE_TRANS +
                " (" + DatabaseHelper.TITLE_COLUM + ") VALUES ('Васька')";
        sqdb.execSQL(insertQuery);
        // ========================================================
        //Изменение данных(Можно исопльзовать сложные условия и преобразовывать инты в строки)
        ContentValues valuese = new ContentValues();
        values.put(DatabaseHelper.TITLE_COLUM, "1111111");
        mSqLiteDatabase.update(mDatabaseHelper.TABLE_TRANS,
                valuese,
                mDatabaseHelper.TITLE_COLUM + "= ?", new String[]{"2222222"});

        //Пример удаления
        mSqLiteDatabase.delete(DatabaseHelper.TABLE_CAT,"category_id = 4",null);
    }
}
