package com.shwetak3e.zentello.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.models.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.shwetak3e.zentello.activities.SplashActivity.parcels;

/**
 * Created by Pervacio on 5/10/2017.
 */

public class LoadingSheetAdapter extends RecyclerView.Adapter<LoadingSheetAdapter.Holder> {

    List<String> booking_ids=new ArrayList<>();


    private Context context;
    private OnMyItemClickListener onMyItemClickListener;

    private static String TAG=LoadingSheetAdapter.class.getSimpleName();


    public LoadingSheetAdapter(Context context, List<String> booking_ids, OnMyItemClickListener onMyItemClickListener) {

        Log.i(TAG,"adapter onCtor");
        this.context=context;
        this.booking_ids=booking_ids;
        this.onMyItemClickListener=onMyItemClickListener;
        }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG,"adapter onCreateViewHolder");
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_list_grid,parent,false);
        final Holder holder= new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        Log.i(TAG,"adapter onBindViewHolder");
        holder.booking_id.setText(booking_ids.get(position));
        holder.remove_loaded_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMyItemClickListener.onClick(booking_ids.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return booking_ids.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView booking_id;
        ImageButton remove_loaded_item;

        public Holder(View itemView) {
            super(itemView);
            booking_id=(TextView)itemView.findViewById(R.id.booking_id_tag);
            remove_loaded_item=(ImageButton)itemView.findViewById(R.id.remove_loaded_item);
        }
    }

    public interface OnMyItemClickListener{
         void onClick(String order_id);
    }

}

