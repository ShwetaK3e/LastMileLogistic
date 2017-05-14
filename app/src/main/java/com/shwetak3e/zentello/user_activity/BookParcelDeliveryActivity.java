package com.shwetak3e.zentello.user_activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.adapter.BookDeliveryAdapter;
import com.shwetak3e.zentello.adapter.TodayScheduleAdapter;
import com.shwetak3e.zentello.franchisee_activity.SeeTodayScheduleActivity;
import com.shwetak3e.zentello.models.Frnachisee;
import com.shwetak3e.zentello.models.Parcel;
import com.shwetak3e.zentello.utilities.Constants;
import com.shwetak3e.zentello.utilities.DetectNetworkConnectivity;

import static com.shwetak3e.zentello.activities.SplashActivity.deliveryBoyRouteMap;
import static com.shwetak3e.zentello.activities.SplashActivity.parcels;
import static com.shwetak3e.zentello.activities.SplashActivity.pinRouteMap;

public class BookParcelDeliveryActivity extends AppCompatActivity {

    LinearLayout new_booking_layout;


    Button book_parcel_courier;
    ViewPager viewPager;
    TabLayout tabLayout;
    BroadcastReceiver nonetwork;

    Parcel parcel;
    private static  final String TAG= BookParcelDeliveryActivity.class.getSimpleName();


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
                 parcel.setParcel_owner("User"+parcel.getId());
                if(parcel.isPick_up()) {
                    parcel.setPick_up_person(findpickerName(parcel.getOrigin().getPincode()));
                }else{
                    parcel.setPick_up_person("");
                }
                Log.i(TAG,parcel.getId()+" "+parcel.getBookingDate()+" "+parcel.getMode()+" "+parcel.getWeight()+" "+parcel.isPick_up());
                 parcels.put(parcel.getId(),parcel);
                 startActivity(new Intent(BookParcelDeliveryActivity.this, SeeTodayScheduleActivity.class));

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


    String findpickerName(String pincode){
        String routeName=pinRouteMap.get(pincode);
        Frnachisee.People pickUpPerson=deliveryBoyRouteMap.get(routeName);
        return  pickUpPerson.getName();
    }
}
