package com.example.nicolas.ccontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nicolas.ccontrol.fragment.diagram;
import com.example.nicolas.ccontrol.fragment.catList;

/**
 * Created by Nicolas on 19.03.2016.
 */
public class FragmentPageAdapter1 extends FragmentPagerAdapter  {
//ТУТ НИЧЕГО НЕ ТРОГАТЬ
    public FragmentPageAdapter1(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle data = new Bundle();
        switch (position){
            case 0://Фрагмент с диаграммой
                return  new diagram();
            case 1://Фрагмент со списком
                catList catList = new catList();
                data.putInt("currentPage", position + 1);
                catList.setArguments(data);
                return catList;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
