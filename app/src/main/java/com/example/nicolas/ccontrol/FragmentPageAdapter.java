package com.example.nicolas.ccontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nicolas.ccontrol.fragment.Diagram;
import com.example.nicolas.ccontrol.fragment.CatList;

/**
 * Created by Nicolas on 19.03.2016.
 */
public class FragmentPageAdapter extends FragmentPagerAdapter  {
//ТУТ НИЧЕГО НЕ ТРОГАТЬ
    public FragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle data = new Bundle();
        switch (position){
            case 0://Фрагмент с диаграммой
                return  new Diagram();
            case 1://Фрагмент со списком
                CatList CatList = new CatList();
                data.putInt("currentPage", position + 1);
                CatList.setArguments(data);
                return CatList;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
