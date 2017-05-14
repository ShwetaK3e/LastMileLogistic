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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.shwetak3e.zentello.activities.SplashActivity.parcels;

/**
 * Created by Pervacio on 5/10/2017.
 */

public class PickUpAdapter extends RecyclerView.Adapter<PickUpAdapter.Holder> {

    private List<Parcel> pickupparcels=new ArrayList<>();


    private Context context;
    private OnMyItemClickListener onMyItemClickListener;

    private static String TAG=PickUpAdapter.class.getSimpleName();


    public PickUpAdapter(Context context, OnMyItemClickListener onMyItemClickListener) {
        Log.i(TAG,"Adapter Constructor"+parcels.size());
        this.context=context;
        this.onMyItemClickListener=onMyItemClickListener;
        for(Map.Entry<String,Parcel> entry: parcels.entrySet()){
            Parcel parcel=entry.getValue();
            Log.i(TAG,"Parcel "+ entry.getKey()+" ----"+entry.getValue().getId());
            if(parcel.isPick_up()){
                Log.i(TAG,"Parcel Added id "+parcel.getId());
                pickupparcels.add(parcel);
                Log.i(TAG,"Parcel Added "+pickupparcels.size());
            }

        }

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
            holder.order_id.setText(pickupparcels.get(position).getId());
            holder.parcel_owner_name.setText(pickupparcels.get(position).getParcel_owner());
            holder.pickup_person_name.setText(pickupparcels.get(position).getPick_up_person());
            if(pickupparcels.get(position).isReahed_warehouse()){
                holder.check_pick.setChecked(true);
            }else{
                holder.check_pick.setChecked(false);
            }

    }

    @Override
    public int getItemCount() {
        return pickupparcels.size();
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
            check_pick.setEnabled(false);
        }
    }

    public interface OnMyItemClickListener{
        public void onClick(String parcel_id);
    }

}

