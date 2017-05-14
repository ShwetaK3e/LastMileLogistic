package com.shwetak3e.zentello.user_activity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.adapter.BookDeliveryAdapter;
import com.shwetak3e.zentello.adapter.TodayScheduleAdapter;
import com.shwetak3e.zentello.models.Parcel;
import com.shwetak3e.zentello.utilities.Constants;
import com.shwetak3e.zentello.utilities.DetectNetworkConnectivity;

import static com.shwetak3e.zentello.activities.SplashActivity.parcels;

public class BookParcelDeliveryActivity extends AppCompatActivity {

    LinearLayout new_booking_layout;


    Button book_parcel_courier;
    ViewPager viewPager;
    TabLayout tabLayout;
    BroadcastReceiver nonetwork;

    Parcel parcel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);

        parcel=new Parcel();
        new_booking_layout=(LinearLayout)findViewById(R.id.new_booking_layout);
        nonetwork=new DetectNetworkConnectivity() {
            @Override
            protected void onNetworkChange() {
                Snackbar snackbar=Snackbar.make(new_booking_layout, Constants.NO_NETWORK,Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        };
        IntentFilter filter=new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(nonetwork,filter);

        book_parcel_courier=(Button)findViewById(R.id.book_parcel);
        book_parcel_courier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 parcel.setId(String.valueOf(parcels.size()));
                 parcels.put(parcel.getId(),parcel);
            }
        });


        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new BookDeliveryAdapter(getSupportFragmentManager(), this,parcel));

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }

}
