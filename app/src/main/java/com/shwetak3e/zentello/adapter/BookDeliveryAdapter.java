package com.shwetak3e.zentello.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shwetak3e.zentello.franchisee_activity.DropByFragment;
import com.shwetak3e.zentello.franchisee_activity.PickUpFragment;
import com.shwetak3e.zentello.models.Parcel;
import com.shwetak3e.zentello.user_activity.ParcelDestFragment;
import com.shwetak3e.zentello.user_activity.ParcelDetailsFragment;
import com.shwetak3e.zentello.user_activity.ParcelOriginFragment;

/**
 * Created by Pervacio on 3/25/2017.
 */

public class BookDeliveryAdapter extends FragmentPagerAdapter {
    final static int PAGE_COUNT=3;
    final static String[] PAGE_TITLES={"Parcel","Origin","Destination"};
    Context context;
    Parcel parcel;

    public BookDeliveryAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return ParcelDetailsFragment.newInstance(position+1,parcel);
            case 1:
                return ParcelOriginFragment.newInstance(position+1,parcel);
            case 2:
                return ParcelDestFragment.newInstance(position+1,parcel);
            default:
                return ParcelDetailsFragment.newInstance(position+1,parcel);
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
