package com.example.nicolas.ccontrol.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.ccontrol.ChangeItemActivity;
import com.example.nicolas.ccontrol.add_delete.DeleteCatActivity;
import com.example.nicolas.ccontrol.add_delete.DeleteItemActivity;
import com.example.nicolas.ccontrol.data_base_control.DatabaseHelper;
import com.example.nicolas.ccontrol.R;
import com.example.nicolas.ccontrol.add_delete.AddItemActivity;
import com.example.nicolas.ccontrol.data_base_control.ControlBD;
import com.example.nicolas.ccontrol.ItemActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Nicolas on 13.04.2016.
 */
public class SrcFragment extends ListFragment implements View.OnClickListener {
    TextView catName;
    Button addTransaction;
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Переменные
    int id;
    String nameCat;//Будет хранить имена категорий
    String year, month;
    Double sumIt; //общая сумма
    String nameIt,adressIt, descIt;
    //Классы
    ControlBD bdcon = new ControlBD();
    //Массивы/Коллекции
    ArrayList<String> dataSorce = new ArrayList<>();
    ArrayList<Integer> temp = new ArrayList<>();//айдишники элементов
    ArrayAdapter<String> adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.sorce_row,R.id.txtitem,dataSorce);
        setListAdapter(adapter);
        setRetainInstance(true);
        registerForContextMenu(getListView());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.source_frag, container,false);

        catName = (TextView) view.findViewById(R.id.categoryName);
        addTransaction = (Button) view.findViewById(R.id.addTransaction);
        addTransaction.setOnClickListener(this);

        Intent intent = SrcFragment.this.getActivity().getIntent();
        id = intent.getIntExtra("catid",23);
        nameCat = intent.getStringExtra("catname");
        year = intent.getStringExtra("yyyy2");
        month = intent.getStringExtra("M2");
        catName.setText(nameCat);

        ReloadSrc();

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_item, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.edit:
                mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();

                Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_TRANS,null, "transaction_id = " + temp.get(info.position), null, null, null, null, null);
                if(cursor.moveToFirst()){
                    int transName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM);
                    int transSum = cursor.getColumnIndex(DatabaseHelper.SUM_COLUM);
                    int transDesc = cursor.getColumnIndex(DatabaseHelper.DESC_COLUM);
                    int transAdress = cursor.getColumnIndex(DatabaseHelper.ADRESS_COLUM);
                    do{
                        nameIt = cursor.getString(transName);
                        adressIt = cursor.getString(transAdress);
                        descIt =  cursor.getString(transDesc);
                        sumIt = cursor.getDouble(transSum);
                    }while (cursor.moveToNext());
                }else Log.d("mLog", "0 rows");

                intent = new Intent(SrcFragment.this.getActivity(), ChangeItemActivity.class);
                intent.putExtra("nameIt",nameIt);
                intent.putExtra("sumIt",sumIt);
                intent.putExtra("adressIt",adressIt);
                intent.putExtra("descIt",descIt);
                intent.putExtra("idTransa",temp.get(info.position));
                startActivityForResult(intent,1);
                break;
            case R.id.delete:
                int id = temp.get(info.position);
                intent = new Intent(SrcFragment.this.getActivity(), DeleteItemActivity.class);
                intent.putExtra("item2ID", id);
                startActivityForResult(intent, 1);
                break;

            default:
                return super.onContextItemSelected(item);
        }
        return true;

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ViewGroup viewGroup = (ViewGroup) v;
        TextView txt = (TextView) viewGroup.findViewById(R.id.txtitem);
        Toast.makeText(getActivity(),txt.getText().toString(),Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SrcFragment.this.getActivity(),ItemActivity.class);
        intent.putExtra("transid", temp.get(position));
        startActivityForResult(intent, 1);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case (R.id.addTransaction):
                intent = new Intent(SrcFragment.this.getActivity(),AddItemActivity.class);
                intent.putExtra("categoryId",id);
                intent.putExtra("getyyyy1",year);
                intent.putExtra("getM1",month);
                startActivityForResult(intent,1);
                break;
        }
    }

    private void ReloadSrc(){
        temp.clear();
        dataSorce.clear();
        mDatabaseHelper = new DatabaseHelper(SrcFragment.this.getActivity(), "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_TRANS,null, "category_id =" + id , null, null, null, null, null);
        if(cursor.moveToFirst()){
            int transName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM);
            int transID = cursor.getColumnIndex(DatabaseHelper.TRANS_ID);
            do{
                dataSorce.add(cursor.getString(transName));
                temp.add(cursor.getInt(transID));
            }while (cursor.moveToNext());
        }else Log.d("mLog", "0 rows");
        mSqLiteDatabase.close();

        adapter = new ArrayAdapter<String>(getActivity(),R.layout.sorce_row,R.id.txtitem,dataSorce);
        setListAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        ReloadSrc();
    }
}
