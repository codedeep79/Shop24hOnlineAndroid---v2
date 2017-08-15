package com.example.trungnguyenhau.shoponline.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by NguyenTrungHau on 8/13/2017.
 */

public class AdapterViewPagerSlider extends FragmentStatePagerAdapter {

    List<Fragment> fragments;

    public AdapterViewPagerSlider(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        fragments = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
