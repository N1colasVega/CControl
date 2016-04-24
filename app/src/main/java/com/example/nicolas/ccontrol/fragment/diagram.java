package com.example.nicolas.ccontrol.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nicolas.ccontrol.DatabaseHelper;
import com.example.nicolas.ccontrol.R;
import com.example.nicolas.ccontrol.addCatActivity;
import com.example.nicolas.ccontrol.controlBD;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class diagram extends Fragment implements View.OnClickListener {
    Button addBut;
    PieChart mChart;
    //Массивы/Коллекции
    ArrayList<Float> yData = new ArrayList<Float>();
    ArrayList<String> xData = new ArrayList<String>();
    //Базы данных
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSqLiteDatabase;
    //Классы
    controlBD bdcon = new controlBD();
    //Переменные
    float f = 111;
    String year, month;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_fragment, container,false);

        addBut = (Button) view.findViewById(R.id.addBut);
        addBut.setOnClickListener(this);
        mChart = (PieChart) view.findViewById(R.id.chart);//Находим диаграмму

        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setDescriptionPosition(100, 600);

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);

        mChart.setHoleRadius(10);
        mChart.setTransparentCircleRadius(10);

        mChart.setRotationAngle(0);
        mChart.setRotationEnabled(true);

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {
                if (entry == null) return;
            }

            @Override
            public void onNothingSelected() {

            }
        });

        Intent intent = diagram.this.getActivity().getIntent();

        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");

        addData();

        Legend l = mChart.getLegend();

        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        l.setXEntrySpace(1);
        l.setYEntrySpace(7);

        return view;
    }

    private void addData(){
        boolean state = false;
        ArrayList<Integer> temp = new ArrayList<Integer>();

        mDatabaseHelper = new DatabaseHelper(diagram.this.getActivity(), "finalBase2.db", null, 1);//используем простой конструктор(не для даунов,а простой)
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        xData.clear();
        yData.clear();
        temp.clear();
        Cursor cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_CAT,null, "datelocal like '" + year + month + "%'", null, null, null, null, null);
        if(cursor.moveToFirst()){
            state = true;
            int catName = cursor.getColumnIndex(DatabaseHelper.TITLE_COLUM2);
            int catID = cursor.getColumnIndex(DatabaseHelper.CAT_ID2);
            do{
                xData.add(cursor.getString(catName));
                temp.add(cursor.getInt(catID));

            }while (cursor.moveToNext());
        }else xData.add("Нет категорий");

        if(state){
        for (int i = 0; i < temp.size()  ; i++) {
            Double summa;
            summa = 0.0;
            String zap = temp.get(i) + "";
            cursor = mSqLiteDatabase.query(DatabaseHelper.TABLE_TRANS, null, "category_id = " + temp.get(i), null, null, null, null, null);
            if(cursor.moveToFirst()){
                int sum = cursor.getColumnIndex(DatabaseHelper.SUM_COLUM);
                do{
                    summa = summa + cursor.getDouble(sum);
                }while (cursor.moveToNext());
            }else summa = 10.0;
            yData.add(summa.floatValue());
        }}else {
            yData.add(f);
        }

        ArrayList<Entry> yVals1 = new ArrayList<>();
        yVals1.clear();
        for (int i = 0; i < yData.size() ; i++)
            yVals1.add(new Entry(yData.get(i),i));

        ArrayList<String> xVals1 = new ArrayList<>();
        xVals1.clear();
        for (int i = 0; i < xData.size() ; i++)
            xVals1.add(xData.get(i));

        PieDataSet dataSet = new PieDataSet(yVals1,"");
        dataSet.setSelectionShift(5);
        dataSet.setSliceSpace(3);

        ArrayList<Integer> colors = new ArrayList<>();


        for(int c : ColorTemplate.VORDIPLOM_COLORS) colors.add(c);


        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        PieData data = new PieData(xVals1, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        mChart.setData(data);

        mChart.highlightValues(null);

        mChart.invalidate();

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.addBut:
                intent = new Intent(diagram.this.getActivity(),addCatActivity.class);
                intent.putExtra("getyyyy1",year);
                intent.putExtra("getM1",month);
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        addData();
    }
}
