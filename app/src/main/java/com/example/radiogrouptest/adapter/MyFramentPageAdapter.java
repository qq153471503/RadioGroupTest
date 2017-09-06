package com.example.radiogrouptest.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;


/**
 * Created by KunGe on 2017/8/22.
 */

public class MyFramentPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> list;

    public MyFramentPageAdapter(FragmentManager supportFragmentManager, ArrayList<Fragment> fragmentArrayList) {
        super(supportFragmentManager);
        this.list = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}

