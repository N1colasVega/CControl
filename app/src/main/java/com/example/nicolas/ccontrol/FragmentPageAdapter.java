package com.example.nicolas.ccontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.nicolas.ccontrol.fragment.FirstFrament;
import com.example.nicolas.ccontrol.fragment.SecondFragment;

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
                return  new FirstFrament();
            case 1://Фрагмент со списком
                SecondFragment secondFragment = new SecondFragment();
                data.putInt("currentPage", position + 1);
                secondFragment.setArguments(data);
                return secondFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
