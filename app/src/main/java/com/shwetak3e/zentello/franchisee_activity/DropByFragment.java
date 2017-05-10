package com.shwetak3e.zentello.franchisee_activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shwetak3e.zentello.R;

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

    public DropByFragment() {
        // Required empty public constructor
    }


    public static PickUpFragment newInstance(int page) {
        PickUpFragment fragment = new PickUpFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_MSG, page);
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_dropby, container, false);

        dropbyList = (RecyclerView) view.findViewById(R.id.drop_by_list);
        layoutManager = new GridLayoutManager(getActivity(), 1);
        dropbyList.setLayoutManager(layoutManager);
        return view;
    }
}