package com.example.nicolas.ccontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.ccontrol.data_base_control.ControlBD;
import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    //Объекты
    TextView Year,sumYear,costYear;
    //Классы
    ControlBD bdcon = new ControlBD();
    //Переменные
    String format;
    String year, month;
    Double ysum;
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        format = "yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dataTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Год");
        ArrayAdapter adapter1 = (ArrayAdapter) spinner.getAdapter();
        spinner.setSelection(adapter1.getPosition(dataTime));
        spinner.setOnItemSelectedListener(this);

        mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);
        createBase();

        Year = (TextView) findViewById(R.id.Year);//Поле - текущий год
        sumYear = (TextView) findViewById(R.id.sumYear);//Поле - доход за год
        costYear = (TextView) findViewById(R.id.costYear);//Поле - расходы за год

        year = dataTime;
        updateYear();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        updateYear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //При создании меню - добавляем наше меню
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void monthButtonClick(View v) {//Обработчик нажатия кнопок
        Intent intent;
        switch (v.getId()) {//Действия кнопок
            case R.id.bJanuary:
                month = "1";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bFebruary:
                month = "2";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bMarch:
                month = "3";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bApril:
                month = "4";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bMay:
                month = "5";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bJune:
                month = "6";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bJuly:
                month = "7";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bAugust:
                month = "8";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bSeptember:
                month = "9";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bOctober:
                month = "10";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bNovember:
                month = "11";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
            case R.id.bDecember:
                month = "12";
                intent = new Intent(this, DiagramActivity.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                startActivityForResult(intent,1);
                break;
        }
    }

    public  void createBase(){
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

        cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_IMAGE,null,null,null,null,null,null,null);
        if(cursor.moveToNext()){
            int imgIndex = cursor.getColumnIndex(DatabaseHelper.IMAGE_ID);
            int tranIndex = cursor.getColumnIndex(DatabaseHelper.TRANS_ID2);
            int img = cursor.getColumnIndex(DatabaseHelper.IMG_COLUM3);
            int desc = cursor.getColumnIndex(DatabaseHelper.DESC_COLUM3);
            int date = cursor.getColumnIndex(DatabaseHelper.DATE_ADD_COLUM3);
            int dateL = cursor.getColumnIndex(DatabaseHelper.DATE_COLUM_LOCAL4);
            do {
                Log.d("mLog",
                        "ID = " + cursor.getInt(imgIndex)
                                +", Trans_id = " + cursor.getInt(tranIndex)
                                +", Img = " + cursor.getString(img)
                                + ", desc " + cursor.getString(desc)
                                + ", date " + cursor.getString(date)
                                + ", LocalDAte " + cursor.getString(dateL));
            }while (cursor.moveToNext());
        }else Log.d("mLog","IMGE_TABEL 0 rows!!!!");

        mSqLiteDatabase.close();
    }

    private  void updateYear(){
        ysum = 0.0;
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_TRANS,null,"datelocal like '" + year + "%'", null, null, null, null, null);
        if(cursor.moveToFirst()){
            int catSum = cursor.getColumnIndex(DatabaseHelper.SUM_COLUM);
            do{
              ysum = ysum + cursor.getDouble(catSum);
            }while (cursor.moveToNext());
        }else ysum = 0.0;
        costYear.setText("Расходы за год: " + ysum);
        mSqLiteDatabase.close();

    }

    public void aboutAppButton(View view) {
        startActivity(new Intent(this, AboutAppActivity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] choose = getResources().getStringArray(R.array.years);
        year = choose[position];
        updateYear();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}