package com.shwetak3e.zentello.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.models.Parcel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pervacio on 5/10/2017.
 */

public class PickUpAdapter extends RecyclerView.Adapter<PickUpAdapter.Holder> {

    private Map<String,Parcel> parcels=new HashMap<>();

    private Context context;
    private OnMyItemClickListener onMyItemClickListener;

    private static String TAG=PickUpAdapter.class.getSimpleName();


    public PickUpAdapter(Context context, Map<String ,Parcel> parcels, OnMyItemClickListener onMyItemClickListener) {
        Log.i(TAG,"Adapter Constructor");
        this.context=context;
        this.parcels=parcels;
        this.onMyItemClickListener=onMyItemClickListener;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"adapter onCreateViewHolder");
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.each_pick_grid,parent,false);
        final Holder holder= new Holder(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemClickListener.onClick(holder.order_id.getText().toString());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Log.i(TAG,"adapter onBindViewHolder");
    }

    @Override
    public int getItemCount() {
        return parcels.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView order_id,parcel_owner_name,pickup_person_name;
        CheckBox check_pick;
        public Holder(View itemView) {
            super(itemView);
            order_id=(TextView)itemView.findViewById(R.id.order_id);
            parcel_owner_name=(TextView)itemView.findViewById(R.id.parcel_owner);
            pickup_person_name=(TextView)itemView.findViewById(R.id.pick_up_person);
            check_pick=(CheckBox) itemView.findViewById(R.id.check_pick);
        }
    }

    public interface OnMyItemClickListener{
        public void onClick(String order_id);
    }

}

