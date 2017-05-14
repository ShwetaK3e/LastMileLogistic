package com.shwetak3e.zentello.franchisee_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.adapter.DropByAdapter;
import com.shwetak3e.zentello.adapter.PickUpAdapter;
import com.shwetak3e.zentello.models.Parcel;

import static com.shwetak3e.zentello.activities.SplashActivity.parcels;

/**
 * Created by Pervacio on 5/11/2017.
 */

public class DropByFragment  extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_MSG = "ARG_MSG";

    // TODO: Rename and change types of parameters
    private int page;

    RecyclerView dropbyList;
    RecyclerView.LayoutManager layoutManager;
    DropByAdapter dropByAdapter;

    private static final String TAG=DropByFragment.class.getSimpleName();
    public DropByFragment() {
        // Required empty public constructor
    }


    public static DropByFragment newInstance(int page) {
        DropByFragment fragment = new DropByFragment();
        Log.i(TAG,"newInstance");
        Bundle args = new Bundle();
        args.putInt(ARG_MSG, page);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"onCreate");
        if (getArguments() != null) {
            page = getArguments().getInt(ARG_MSG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dropby, container, false);
        Log.i(TAG,"onCreateView");
        dropbyList = (RecyclerView) view.findViewById(R.id.drop_by_list);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        dropbyList.setLayoutManager(layoutManager);
        dropbyList.setLayoutManager(layoutManager);
        dropByAdapter=new DropByAdapter(getActivity(), new DropByAdapter.OnMyItemClickListener() {
            @Override
            public void onClick(String parcel_id) {
                Parcel parcel=parcels.get(parcel_id);
                parcel.setReahed_warehouse(true);
                parcels.remove(parcel_id);
                parcels.put(parcel_id,parcel);
              startActivity(new Intent(getActivity(),SignatureActivity.class));
            }
        });
        dropbyList.setAdapter(dropByAdapter);
        return view;
    }
}