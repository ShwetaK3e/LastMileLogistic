package com.shwetak3e.zentello.franchisee_activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.adapter.TodayScheduleAdapter;
import com.shwetak3e.zentello.utilities.Constants;
import com.shwetak3e.zentello.utilities.DetectNetworkConnectivity;

public class SeeTodayScheduleActivity extends AppCompatActivity {

    LinearLayout see_today_schedule;

    ImageView profilePic,logo;

    TextView franchiseeName;

    ViewPager viewPager;

    TabLayout tabLayout;
    BroadcastReceiver nonetwork;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_today_schedule);

        see_today_schedule=(LinearLayout)findViewById(R.id.new_bzyness_layout);
        nonetwork=new DetectNetworkConnectivity() {
            @Override
            protected void onNetworkChange() {
                Snackbar snackbar=Snackbar.make(see_today_schedule, Constants.NO_NETWORK,Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        };
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(nonetwork,filter);

        profilePic=(ImageView)findViewById(R.id.bprof_pic);

        logo=(ImageView)findViewById(R.id.b_logo);

        franchiseeName=(TextView) findViewById(R.id.franchisee_name);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new TodayScheduleAdapter(getSupportFragmentManager(), this));

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }



}
