package com.example.nicolas.ccontrol.fragment;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.ccontrol.R;

import java.util.ArrayList;

/**
 * Created by Nicolas on 13.04.2016.
 */
public class srcFragment extends ListFragment {

    ArrayList<String> dataSorce = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ArrayAdapter<String>(getActivity(),R.layout.sorce_row,R.id.txtitem,dataSorce);
        setListAdapter(adapter);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.source_frag,container,false);
        return rootView;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ViewGroup viewGroup = (ViewGroup) v;
        TextView txt = (TextView) viewGroup.findViewById(R.id.txtitem);
        Toast.makeText(getActivity(),txt.getText().toString(),Toast.LENGTH_LONG).show();

    }
}
