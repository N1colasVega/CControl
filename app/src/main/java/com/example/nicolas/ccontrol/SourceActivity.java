package com.example.nicolas.ccontrol;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nicolas.ccontrol.fragment.SrcFragment;

public class SourceActivity extends AppCompatActivity {
    //Переменные
    int id;
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorce);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SrcFragment srcfragment = (SrcFragment) getSupportFragmentManager().findFragmentByTag("srcfragment");
        if(srcfragment == null){
            srcfragment = new SrcFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content, srcfragment, "stcfragment");
            transaction.commit();
        }
        Intent intent = getIntent();
        id = intent.getIntExtra("catid",23);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.changeCategory:
                break;
            case R.id.deleteCategory:
                mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                mSqLiteDatabase.delete(DatabaseHelper.TABLE_TRANS,"category_id = " + id,null);
                mSqLiteDatabase.delete(DatabaseHelper.TABLE_CAT,"category_id = " + id,null);
                mSqLiteDatabase.close();
                finish();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
