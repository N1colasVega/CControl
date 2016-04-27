package com.example.nicolas.ccontrol.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.ccontrol.DatabaseHelper;
import com.example.nicolas.ccontrol.R;
import com.example.nicolas.ccontrol.AddCatActivity;
import com.example.nicolas.ccontrol.SourceActivity;
import com.example.nicolas.ccontrol.ControlBD;

import java.util.ArrayList;

public class CatList extends ListFragment implements View.OnClickListener {
    Button addCat;
    TextView textView;
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    String year, month;
    String nameCat;//Будет хранить имена категорий
    //Классы
    ControlBD bdcon = new ControlBD();
    //Массивы/Коллекции
    ArrayList<String> nameData = new ArrayList<>();//данные списка
    ArrayList<Integer> temp = new ArrayList<>();//айдишники категорий

    @Nullable
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {//создаём фрагмент и даём ему лояулт
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        addCat = (Button) view.findViewById(R.id.addBut2);
        textView = (TextView) view.findViewById(R.id.cat);
        Intent intent = CatList.this.getActivity().getIntent();
        addCat.setOnClickListener(this);

        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        ReloadList();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //при старте берем данные из списка(в него надо будет добавить данные из БД)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, nameData);
        setListAdapter(adapter);
    }

    @Override//Нажатие на пункты списка
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(CatList.this.getActivity(),SourceActivity.class);
        intent.putExtra("catid",temp.get(position));
        intent.putExtra("catname",nameData.get(position));
        intent.putExtra("yyyy2", year);
        intent.putExtra("M2", month);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Для возвращения данных, скорее всего не пригодится,но оставим пока
    }

    @Override
    public void onResume() {
        super.onResume();
        ReloadList();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case (R.id.addBut2):
                intent = new Intent(CatList.this.getActivity(),AddCatActivity.class);
                intent.putExtra("getyyyy1",year);
                intent.putExtra("getM1",month);
                startActivityForResult(intent,1);
                break;
        }
    }

    private void ReloadList(){
        nameData.clear();
        temp.clear();
        mDatabaseHelper = new DatabaseHelper(CatList.this.getActivity(), "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_CAT,null, "datelocal like '" + year + month + "%'", null, null, null, null, null);
        if(cursor.moveToFirst()){
            int catName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM2);
            int catID = cursor.getColumnIndex(DatabaseHelper.CAT_ID2);
            do{
                nameData.add(cursor.getString(catName));
                temp.add(cursor.getInt(catID));
            }while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        mSqLiteDatabase.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, nameData);
        setListAdapter(adapter);
    }
}
