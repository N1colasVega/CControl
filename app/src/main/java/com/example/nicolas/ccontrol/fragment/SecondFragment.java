package com.example.nicolas.ccontrol.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nicolas.ccontrol.R;

import java.util.ArrayList;

public class SecondFragment extends ListFragment implements View.OnClickListener {
    Button addCat;

    ArrayList<String> nameData = new ArrayList<>();//данные списка

    ArrayAdapter<String> adapter;

    String nameCat;//Будет хранить имена категорий

    @Nullable
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, nameData);//используем простой элемент simple_list_item_1
        setListAdapter(adapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {//создаём фрагмент и даём ему лояулт
        View view = inflater.inflate(R.layout.list_fragment, container,false);

        addCat = (Button) view.findViewById(R.id.addCat);
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
        //тестовый "тост"
        Toast.makeText(getActivity(), "position" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Для возвращения данных, скорее всего не пригодится,но оставим пока
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.addCat):

                break;
        }
    }
}
