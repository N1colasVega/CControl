package com.example.nicolas.ccontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nicolas.ccontrol.fragment.Diagram1;
import com.example.nicolas.ccontrol.fragment.CatList1;

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
                return  new Diagram1();
            case 1://Фрагмент со списком
                CatList1 CatList1 = new CatList1();
                data.putInt("currentPage", position + 1);
                CatList1.setArguments(data);
                return CatList1;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
