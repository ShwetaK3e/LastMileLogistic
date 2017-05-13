package com.shwetak3e.zentello.user_activity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.models.Parcel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Pervacio on 5/11/2017.
 */

public class ParcelOriginFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MSG = "ARG_MSG";

    // TODO: Rename and change types of parameters
    private int page;

    EditText enter_pincode;
    Button search_franchisee_by_pincode;

    LinearLayout available_franchisee_layout;
    TextView franchisee_name;
    Button pick_up,drop_by;

    ScrollView origin_details_layout;

    TextView drop_by_details;
    FrameLayout pick_up_details;

    RelativeLayout gMapLayout;
    SupportMapFragment supportMapFragment;
    GoogleMap googleMap;
    LatLng latLng;
    LinearLayout edit_address;

    LinearLayout addressFormLayout;
    EditText placeName,zip,locality,street,city,state,country;
    Button save_place;
    Parcel parcel;


    public ParcelOriginFragment() {
        // Required empty public constructor
    }


    public static ParcelOriginFragment newInstance(int page, Parcel parcel) {
        ParcelOriginFragment fragment = new ParcelOriginFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MSG, page);
        fragment.setArguments(args);
        fragment.parcel=parcel;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt(ARG_MSG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parcel_origin, container, false);
        enter_pincode=(EditText)view.findViewById(R.id.pincode_edit);
        search_franchisee_by_pincode=(Button)view.findViewById(R.id.search_franchisee);
        search_franchisee_by_pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                available_franchisee_layout.setVisibility(View.VISIBLE);
            }
        });

        available_franchisee_layout=(LinearLayout)view.findViewById(R.id.available_franchisee_layout);
        available_franchisee_layout.setVisibility(View.INVISIBLE);
        franchisee_name=(TextView)view.findViewById(R.id.franchisee_name);
        pick_up=(Button)view.findViewById(R.id.pick_my_parcel);
        pick_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                origin_details_layout.setVisibility(View.VISIBLE);
                showPickUpDetails();
            }
        });
        drop_by=(Button)view.findViewById(R.id.drop_my_parcel);
        drop_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                origin_details_layout.setVisibility(View.VISIBLE);
                showDropByDetails();
            }
        });

        origin_details_layout=(ScrollView)view.findViewById(R.id.origin_details_layout);
        origin_details_layout.setVisibility(View.INVISIBLE);
        drop_by_details=(TextView)view.findViewById(R.id.drop_by_date);
        drop_by_details.setVisibility(View.INVISIBLE);
        pick_up_details=(FrameLayout)view.findViewById(R.id.pick_up_details);
        pick_up_details.setVisibility(View.INVISIBLE);
        gMapLayout=(RelativeLayout)view.findViewById(R.id.map_layout);
        gMapLayout.setVisibility(View.INVISIBLE);
        supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.loc_map);
        supportMapFragment.getMapAsync(this);
        latLng = new LatLng(12.79037479, 77.50854492);
        edit_address = (LinearLayout) view.findViewById(R.id.loc_edit);
        edit_address.setVisibility(View.INVISIBLE);
        edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressFormLayout.setVisibility(View.VISIBLE);
                gMapLayout.setVisibility(View.INVISIBLE);
            }
        });

        addressFormLayout=(LinearLayout)view. findViewById(R.id.loc_form_layout);
        placeName=(EditText)view.findViewById(R.id.store_name);
        zip=(EditText)view.findViewById(R.id.zip);
        locality=(EditText)view.findViewById(R.id.locality);
        street=(EditText)view.findViewById(R.id.street);
        city=(EditText)view.findViewById(R.id.city);
        state=(EditText)view.findViewById(R.id.state);
        country=(EditText)view.findViewById(R.id.country);

        save_place=(Button)view.findViewById(R.id.save_place);
        save_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMap();
            }
        });
        return view;
    }


    private boolean isValid() {
        if(placeName.getText()!=null && zip.getText()!=null&& locality.getText()!=null && street.getText()!=null&& city.getText()!=null && state.getText()!=null &&  country.getText()!=null) {
          return true;
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        this.googleMap.getUiSettings().setAllGesturesEnabled(false);
        this.googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                ParcelOriginFragment.this.latLng=latLng;
                ParcelOriginFragment.this.googleMap.getUiSettings().setAllGesturesEnabled(true);
                ParcelOriginFragment.this.googleMap.clear();
                ParcelOriginFragment.this.googleMap.addMarker(new MarkerOptions().position(latLng).title("Destination Marker"));
                ParcelOriginFragment.this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
            }
        });
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,14.0f));
    }

    private void showMap() {
        if(isValid()) {
            List<Address> addresses = new ArrayList<>();
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            String address = placeName.getText().toString().trim() + street.getText().toString().trim() + "," + locality.getText().toString().trim() + "," + city.getText().toString().trim() + "," + state.getText().toString().trim() + "," + zip.getText().toString().trim() + "," + country.getText().toString().trim();
            try {
                addresses = geocoder.getFromLocationName(address, 1);
                if (addresses.isEmpty()) {
                    showAddFormFrag();
                    Toast.makeText(getActivity(), "Not A Correct Address. Check the entered Details", Toast.LENGTH_LONG).show();
                } else {
                    showMapFrag();
                    latLng = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                    googleMap.getUiSettings().setAllGesturesEnabled(true);
                    googleMap.clear();
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("Destination Marker"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getActivity(), "Not A Correct Address. Check the entered Details", Toast.LENGTH_LONG).show();
        }
    }

    void showMapFrag() {
        gMapLayout.setVisibility(View.VISIBLE);
        addressFormLayout.setVisibility(View.INVISIBLE);
    }

    void showAddFormFrag() {
        gMapLayout.setVisibility(View.INVISIBLE);
        addressFormLayout.setVisibility(View.VISIBLE);
    }

    void showPickUpDetails() {
        drop_by_details.setVisibility(View.INVISIBLE);
        pick_up_details.setVisibility(View.VISIBLE);
    }

    void showDropByDetails(){
        drop_by_details.setVisibility(View.VISIBLE);
        pick_up_details.setVisibility(View.INVISIBLE);
        drop_by_details.setText("Deliver the parcel at above franchisee by 7:00 PM on 18.05.2017");

    }
}