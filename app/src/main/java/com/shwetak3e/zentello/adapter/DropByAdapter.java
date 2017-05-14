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

public class DropByAdapter extends RecyclerView.Adapter<DropByAdapter.Holder> {

    private List<Parcel> dropbyparcels=new ArrayList<>();

    private Context context;
    private OnMyItemClickListener onMyItemClickListener;

    private static String TAG=DropByAdapter.class.getSimpleName();


    public DropByAdapter(Context context, OnMyItemClickListener onMyItemClickListener) {
        Log.i(TAG,"Adapter Constructor"+parcels.size());
        this.context=context;
        this.onMyItemClickListener=onMyItemClickListener;
        for(Map.Entry<String,Parcel> entry: parcels.entrySet()){
            Log.i(TAG,"Parcel "+ entry.getKey()+" ----"+entry.getValue().getId());
            Parcel parcel=entry.getValue();

            if(!parcel.isPick_up()){
                Log.i(TAG,"Parcel Added id "+parcel.getId());
                dropbyparcels.add(parcel);
                Log.i(TAG,"Parcel Added3 "+dropbyparcels.size());
            }
        }
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"adapter onCreateViewHolder");
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.each_drop_grid,parent,false);
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
        holder.order_id.setText(dropbyparcels.get(position).getId());
        holder.parcel_owner_name.setText(dropbyparcels.get(position).getParcel_owner());
        if(dropbyparcels.get(position).isReahed_warehouse()){
            holder.check_pick.setChecked(true);
        }else{
            holder.check_pick.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return dropbyparcels.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView order_id,parcel_owner_name;
        CheckBox check_pick;
        public Holder(View itemView) {
            super(itemView);
            order_id=(TextView)itemView.findViewById(R.id.order_id);
            parcel_owner_name=(TextView)itemView.findViewById(R.id.parcel_owner);
            check_pick=(CheckBox) itemView.findViewById(R.id.check_pick);
            check_pick.setEnabled(false);
        }
    }

    public interface OnMyItemClickListener{
        public void onClick(String order_id);
    }

}

