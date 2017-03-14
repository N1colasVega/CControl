package com.example.nicolas.ccontrol.add_delete;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolas.ccontrol.R;
import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;

import java.util.ArrayList;

public class DeleteCatActivity extends AppCompatActivity implements View.OnClickListener {
    Button okDelCat,noDelCat;
    TextView textView;
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    int catID;
    //Массивы/Коллекции
    ArrayList<Integer> temp = new ArrayList<>();//айдишники элементов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_cat);

        okDelCat = (Button) findViewById(R.id.okDelCat);
        okDelCat.setOnClickListener(this);
        noDelCat = (Button) findViewById(R.id.noDelCat);
        noDelCat.setOnClickListener(this);

        textView =(TextView) findViewById(R.id.doDelView);

        Intent intent = getIntent();
        catID = intent.getIntExtra("categoryID",23);

        mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_TRANS, null, "category_id =" + catID, null, null, null, null, null);
        if(cursor.moveToFirst()){
            int transID = cursor.getColumnIndex(DatabaseHelper.TRANS_ID);
            do{
                temp.add(cursor.getInt(transID));
            }while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");

        cursor = mSqLiteDatabase.query(mDatabaseHelper.TABLE_CAT,null,"category_id = " + catID, null, null,null,null,null);
        cursor.moveToFirst();
        int catName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM2);
        textView.setText("Действительно удалить " + cursor.getString(catName) + "?");
        mSqLiteDatabase.close();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.okDelCat:
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                mSqLiteDatabase.delete(DatabaseHelper.TABLE_TRANS, "category_id = " + catID, null);
                mSqLiteDatabase.delete(DatabaseHelper.TABLE_CAT, "category_id = " + catID, null);
                for (int i = 0; i < temp.size(); i++) {
                    mSqLiteDatabase.delete(DatabaseHelper.TABLE_IMAGE, "transaction_id = " + temp.get(i), null);
                }
                mSqLiteDatabase.close();
                intent.putExtra("deletecatstate",1);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.noDelCat:
                finish();
                break;
        }
    }
}
