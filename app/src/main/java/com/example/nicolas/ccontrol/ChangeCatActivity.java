package com.example.nicolas.ccontrol;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;

import java.text.SimpleDateFormat;

public class ChangeCatActivity extends AppCompatActivity implements View.OnClickListener {
    Button changeCat, noChangeCat;
    EditText nameCat, descCat;
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_cat);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);

        changeCat = (Button) findViewById(R.id.changeCat);
        changeCat.setOnClickListener(this);
        noChangeCat = (Button) findViewById(R.id.noChangeCat);
        noChangeCat.setOnClickListener(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("categoryID2",23);

        nameCat = (EditText) findViewById(R.id.catNameText);
        descCat = (EditText) findViewById(R.id.descCatText);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_CAT,null, "category_id = " + id , null, null, null, null, null);
        if(cursor.moveToFirst()){
            int catName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM2);
            int catDesc = cursor.getColumnIndex(DatabaseHelper.DESC_COLUM2);
            do{
                nameCat.setText(cursor.getString(catName));
                descCat.setText(cursor.getString(catDesc));
            }while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        mSqLiteDatabase.close();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeCat:
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                ContentValues valuese = new ContentValues();
                valuese.put(DatabaseHelper.TITLE_COLUM2,nameCat.getText().toString());
                valuese.put(DatabaseHelper.DESC_COLUM2,descCat.getText().toString());
                mSqLiteDatabase.update(mDatabaseHelper.TABLE_CAT, valuese, "category_id = " + id, null);
                mSqLiteDatabase.close();
                finish();
                break;
            case R.id.noChangeCat:
                finish();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
