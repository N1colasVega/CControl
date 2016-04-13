package com.example.nicolas.ccontrol;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nicolas.ccontrol.fragment.srcFragment;

public class sorceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorce);
        srcFragment srcfragment = (srcFragment) getSupportFragmentManager().findFragmentByTag("srcfragment");
        if(srcfragment == null){
            srcfragment = new srcFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(android.R.id.content, srcfragment, "stcfragment");
            transaction.commit();
        }

    }
}
