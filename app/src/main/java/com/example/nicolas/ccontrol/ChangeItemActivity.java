package com.example.nicolas.ccontrol;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;

public class ChangeItemActivity extends AppCompatActivity implements View.OnClickListener {
    Button Apply, Cancel;
    EditText nameEd, sumEd, adressEd, descEd;

    Double sumIt; //общая сумма
    String nameIt, adressIt, descIt;

    int id;

    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_item);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Apply = (Button) findViewById(R.id.Apply);
        Cancel = (Button) findViewById(R.id.Cancel);

        Apply.setOnClickListener(this);
        Cancel.setOnClickListener(this);

        nameEd = (EditText) findViewById(R.id.nameFild);
        sumEd = (EditText) findViewById(R.id.sumFild);
        adressEd = (EditText) findViewById(R.id.adressFild);
        descEd = (EditText) findViewById(R.id.descFild);

        Intent intent = getIntent();
        nameIt = intent.getStringExtra("nameIt");
        adressIt = intent.getStringExtra("adressIt");
        descIt = intent.getStringExtra("descIt");
        sumIt = intent.getDoubleExtra("sumIt", 2332.0);
        id = intent.getIntExtra("idTransa", 23);

        nameEd.setText(nameIt);
        adressEd.setText(adressIt);
        descEd.setText(descIt);
        sumEd.setText(sumIt.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Apply:
                mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                ContentValues valuese = new ContentValues();
                valuese.put(DatabaseHelper.TITLE_COLUM, nameEd.getText().toString());
                valuese.put(DatabaseHelper.DESC_COLUM, descEd.getText().toString());
                valuese.put(DatabaseHelper.ADRESS_COLUM, adressEd.getText().toString());
                valuese.put(DatabaseHelper.SUM_COLUM, Double.parseDouble(sumEd.getText().toString()));
                mSqLiteDatabase.update(mDatabaseHelper.TABLE_TRANS, valuese, "transaction_id = " + id, null);
                mSqLiteDatabase.close();
                finish();
                break;
            case R.id.Cancel:
                finish();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.sumeItem:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
