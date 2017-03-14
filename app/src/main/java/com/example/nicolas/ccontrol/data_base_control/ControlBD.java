package com.example.nicolas.ccontrol.data_base_control;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlBD {

    String dataY,dataM,dataD,format,val;
    private DatabaseHelper mDatabaseHelper;

    public ControlBD() {
    }

    public String localDate(){
        format = "yyyy";
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(format);
        dataY = simpleDateFormat1.format(new Date(System.currentTimeMillis()));
        format = "M";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(format);
        dataM = simpleDateFormat2.format(new Date(System.currentTimeMillis()));
        format = "d";
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(format);
        dataD = simpleDateFormat3.format(new Date(System.currentTimeMillis()));
        return val = dataY + dataM + dataD;
    }

    public void addCat(SQLiteDatabase mSqLiteDatabase,String name, String desc, String img, String yyyy, String M){
        ContentValues values = new ContentValues();

        format = "d";
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(format);
        dataD = simpleDateFormat3.format(new Date(System.currentTimeMillis()));

        values.put(DatabaseHelper.TITLE_COLUM2, name);
        values.put(DatabaseHelper.DESC_COLUM2, desc);
        values.put(DatabaseHelper.IMG_COLUM, img);
        values.put(DatabaseHelper.STATUS_COLUM, 1);
        Date c = new Date(System.currentTimeMillis());
        long milliseconds = c.getTime();
        values.put(DatabaseHelper.DATE_ADD_COLUM, milliseconds);
        values.put(DatabaseHelper.DATE_COLUM_LOCAL2,yyyy + M + dataD);

        mSqLiteDatabase.insert(DatabaseHelper.TABLE_CAT, null, values);
    }

    public void addTrans(SQLiteDatabase mSqLiteDatabase,int cat,int source,String name, String desc,Double sum, String adress, String yyyy, String M){
        ContentValues values = new ContentValues();

        format = "d";
        SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(format);
        dataD = simpleDateFormat3.format(new Date(System.currentTimeMillis()));

        values.put(DatabaseHelper.CAT_ID, cat);
        values.put(DatabaseHelper.SOURCE_ID, source);
        values.put(DatabaseHelper.TITLE_COLUM, name);
        values.put(DatabaseHelper.DESC_COLUM, desc);
        values.put(DatabaseHelper.SUM_COLUM, sum);
        values.put(DatabaseHelper.ADRESS_COLUM, adress);
        Date c = new Date(System.currentTimeMillis());
        long milliseconds = c.getTime();
        values.put(DatabaseHelper.DATE_COLUM, milliseconds);
        values.put(DatabaseHelper.DATE_COLUM_LOCAL, yyyy + M + dataD);

        mSqLiteDatabase.insert(DatabaseHelper.TABLE_TRANS, null, values);
    }

    public void addSource(SQLiteDatabase mSqLiteDatabase,String name, Double balance, String img){
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.TITLE_COLUM3, name);
        values.put(DatabaseHelper.BALANCE_COLUM, balance);
        values.put(DatabaseHelper.IMG_COLUM, img);
        Date c = new Date(System.currentTimeMillis());
        long milliseconds = c.getTime();
        values.put(DatabaseHelper.DATE_ADD_COLUM2, milliseconds);
        values.put(DatabaseHelper.DATE_COLUM_LOCAL3,localDate());

        mSqLiteDatabase.insert(DatabaseHelper.TABLE_SOURCE, null, values);
    }

    public void addImg(SQLiteDatabase mSqLiteDatabase,int transid,String img, String desc){
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.TRANS_ID2, transid);
        values.put(DatabaseHelper.IMG_COLUM3, img);
        values.put(DatabaseHelper.DESC_COLUM3, desc);
        Date c = new Date(System.currentTimeMillis());
        long milliseconds = c.getTime();
        values.put(DatabaseHelper.DATE_ADD_COLUM3, milliseconds);
        values.put(DatabaseHelper.DATE_COLUM_LOCAL4,localDate());

        mSqLiteDatabase.insert(DatabaseHelper.TABLE_IMAGE, null, values);
    }

}
