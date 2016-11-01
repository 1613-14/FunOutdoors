package com.phone.funoutdoors.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by White丶 on 2016/10/27.
 */
public class OutdoorViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String[] titles;
    public OutdoorViewPagerAdapter(FragmentManager fm,List<Fragment> list,String[] titles) {
        super(fm);
        this.list=list;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
