package com.shwetak3e.zentello.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.shwetak3e.zentello.franchisee_activity.PickUpFragment;

/**
 * Created by Pervacio on 3/25/2017.
 */

public class TodayScheduleAdapter extends FragmentPagerAdapter {
    final static int PAGE_COUNT=2;
    final static String[] PAGE_TITLES={"Pick Up","Drop By"};
    Context context;

    public TodayScheduleAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return PickUpFragment.newInstance(position+1);
            case 1:
                return PickUpFragment.newInstance(position+1);
            default:
                return PickUpFragment.newInstance(position+1);
        }

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }
}
