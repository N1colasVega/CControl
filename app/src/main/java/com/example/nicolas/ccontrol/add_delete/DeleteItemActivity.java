package com.example.nicolas.ccontrol.add_delete;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicolas.ccontrol.R;
import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;

public class DeleteItemActivity extends AppCompatActivity implements View.OnClickListener {
    Button okDelItem,noDelItem;
    TextView textView;
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    int itemID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        okDelItem = (Button) findViewById(R.id.okDelItem);
        okDelItem.setOnClickListener(this);
        noDelItem = (Button) findViewById(R.id.noDelItem);
        noDelItem.setOnClickListener(this);

        textView =(TextView) findViewById(R.id.doDelView2);

        Intent intent = getIntent();
        itemID = intent.getIntExtra("item2ID",23);

        mDatabaseHelper = new DatabaseHelper(this, "finalBase2.db", null, 1);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.query(mDatabaseHelper.TABLE_TRANS,null,"transaction_id = " + itemID, null, null,null,null,null);
        cursor.moveToFirst();
        int itemName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM);
        textView.setText("Действительно удалить расход: \"" + cursor.getString(itemName) + "\"?");
        mSqLiteDatabase.close();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.okDelItem:
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
                mSqLiteDatabase.delete(DatabaseHelper.TABLE_TRANS, "transaction_id = " + itemID, null);
                mSqLiteDatabase.close();
                intent.putExtra("deletestate",1);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.noDelItem:
                finish();
                break;
        }
    }
}
