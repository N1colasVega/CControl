package com.example.nicolas.ccontrol.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nicolas.ccontrol.R;
import com.example.nicolas.ccontrol.addCatActivity;
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

public class FirstFrament extends Fragment implements View.OnClickListener {
    Button addBut;
    PieChart mChart;
    private float[] yData = {1};
    private String[] xData = {"Список пуст"};
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

        addData();

        Legend l = mChart.getLegend();

        l.setPosition(Legend.LegendPosition.LEFT_OF_CHART);
        l.setXEntrySpace(1);
        l.setYEntrySpace(7);

        return view;
    }

    private void addData(){
        ArrayList<Entry> yVals1 = new ArrayList<>();
        for (int i = 0; i < yData.length ; i++)
            yVals1.add(new Entry(yData[i],i));


        ArrayList<String> xVals1 = new ArrayList<>();
        for (int i = 0; i < xData.length ; i++)
            xVals1.add(xData[i]);

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
                intent = new Intent(FirstFrament.this.getActivity(),addCatActivity.class);
                startActivity(intent);
                break;
        }

    }
}
