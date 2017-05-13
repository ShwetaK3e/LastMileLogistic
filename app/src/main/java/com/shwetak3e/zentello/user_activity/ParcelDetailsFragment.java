package com.shwetak3e.zentello.user_activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.franchisee_activity.PickUpFragment;
import com.shwetak3e.zentello.models.Parcel;

/**
 * Created by Pervacio on 5/11/2017.
 */

public class ParcelDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MSG = "ARG_MSG";

    // TODO: Rename and change types of parameters
    private int page;

    TextInputEditText booking_date_edit,weight_edit,length_edit,width_edit,height_edit,mode_edit;
    Button save_details;
    Parcel parcel;


    public ParcelDetailsFragment() {
        // Required empty public constructor
    }


    public static ParcelDetailsFragment newInstance(int page,Parcel parcel) {
        ParcelDetailsFragment fragment = new ParcelDetailsFragment();
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
        View view = inflater.inflate(R.layout.fragment_fill_parcel_details, container, false);
        booking_date_edit=(TextInputEditText)view.findViewById(R.id.booking_date_edit);
        weight_edit=(TextInputEditText)view.findViewById(R.id.weight_edit);
        length_edit=(TextInputEditText)view.findViewById(R.id.length_edit);
        width_edit=(TextInputEditText)view.findViewById(R.id.width_edit);
        height_edit=(TextInputEditText)view.findViewById(R.id.height_edit);
        mode_edit=(TextInputEditText)view.findViewById(R.id.mode_edit);
        save_details=(Button)view.findViewById(R.id.save_details);
        save_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveParcelDetails();
            }
        });
        return view;
    }

    private void saveParcelDetails() {
        if(isValid()){
            parcel.setBookingDate(booking_date_edit.getText().toString().trim());
            parcel.setLength(Float.parseFloat(length_edit.getText().toString()));
            parcel.setWidth(Float.parseFloat(width_edit.getText().toString()));
            parcel.setHeight(Float.parseFloat(height_edit.getText().toString()));
            parcel.setWeight(Float.parseFloat(weight_edit.getText().toString()));
            parcel.setMode(mode_edit.getText().toString());
        }
    }

    private boolean isValid() {
        if(booking_date_edit.getText()!=null &weight_edit.getText()!=null&& length_edit.getText()!=null && width_edit.getText()!=null&& height_edit.getText()!=null && mode_edit.getText()!=null){
            return true;
        }
        return false;
    }
}