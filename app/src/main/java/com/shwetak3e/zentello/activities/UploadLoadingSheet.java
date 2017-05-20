package com.shwetak3e.zentello.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shwetak3e.zentello.R;
import com.shwetak3e.zentello.adapter.LoadingSheetAdapter;
import com.shwetak3e.zentello.models.ShipmentItem;
import com.shwetak3e.zentello.utilities.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class UploadLoadingSheet extends AppCompatActivity {

    private final String TAG=UploadLoadingSheet.class.getSimpleName();

    EditText booking_id;
    Button fetch_shipment_details;


    LinearLayout list_layout;
    RecyclerView loading_list;
    RecyclerView.LayoutManager layoutManager;
    List<String > booking_ids=new ArrayList<>();
    LoadingSheetAdapter loadingSheetAdapter;


    CardView shipment_item_layout;
    ShipmentItem shipmentItem;
    TextView commodity_name_tag;
    TextView item_nos_shipped;
    EditText no_of_items_loaded;
    RadioGroup deficiencies;
    RadioButton leaked,missing,damaged;
    Button confirm_loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_loading_sheet);


        booking_id=(EditText)findViewById(R.id.booking_id_edit);
        fetch_shipment_details=(Button)findViewById(R.id.loading);
        fetch_shipment_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UploadingLoadingListService(booking_id.getText().toString().trim()).execute();
            }
        });

        list_layout=(LinearLayout)findViewById(R.id.list_layout);
        list_layout.setVisibility(View.INVISIBLE);
        loading_list=(RecyclerView) findViewById(R.id.loading_list);
        layoutManager=new GridLayoutManager(this,1);
        loading_list.setLayoutManager(layoutManager);
        loadingSheetAdapter=new LoadingSheetAdapter(this, booking_ids, new LoadingSheetAdapter.OnMyItemClickListener() {
            @Override
            public void onClick(String booking_id) {
               booking_ids.remove(booking_id);
               loadingSheetAdapter.notifyDataSetChanged();
            }
        });
        loading_list.setAdapter(loadingSheetAdapter);


        shipment_item_layout=(CardView)findViewById(R.id.shipment_item_view);
        shipment_item_layout.setVisibility(GONE);
        commodity_name_tag=(TextView)findViewById(R.id.commodity_name_tag);
        item_nos_shipped=(TextView)findViewById(R.id.item_nos);
        no_of_items_loaded=(EditText)findViewById(R.id.no_of_items_loaded);
        no_of_items_loaded.addTextChangedListener(new UploadingSheetTextWatcher(no_of_items_loaded));
        deficiencies=(RadioGroup)findViewById(R.id.deficiency_layout);
        missing=(RadioButton)findViewById(R.id.missing_check);
        leaked=(RadioButton)findViewById(R.id.leakage_check);
        damaged=(RadioButton)findViewById(R.id.damaged_check);
        confirm_loading=(Button)findViewById(R.id.confirm_loading);
        confirm_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deficiencies.getVisibility()==VISIBLE){
                    if(deficiencies.getCheckedRadioButtonId()==-1){
                        Toast.makeText(UploadLoadingSheet.this, "Select A Deficiency option", Toast.LENGTH_SHORT).show();
                    }else{
                        list_layout.setVisibility(View.VISIBLE);
                        Log.i(TAG, "Size A" + "----" + booking_ids.size() + " ");
                        String id=booking_id.getText().toString().trim().toUpperCase();
                        booking_ids.add(id);
                        loadingSheetAdapter.notifyDataSetChanged();
                        shipmentItem.setLoadedCount(Integer.parseInt(no_of_items_loaded.getText().toString().trim()));
                    }
                }else {
                    list_layout.setVisibility(View.VISIBLE);
                    Log.i(TAG, "Size B" + "----" + booking_ids.size() + " ");
                    booking_ids.add(booking_id.getText().toString().trim().toUpperCase());
                    loadingSheetAdapter.notifyDataSetChanged();
                    shipmentItem.setLoadedCount(Integer.parseInt(no_of_items_loaded.getText().toString().trim()));
                }
            }
        });

    }



    class UploadingSheetTextWatcher implements TextWatcher {

        private View view;

        private UploadingSheetTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.no_of_items_loaded:
                    if(editable.toString().equals("")){
                        deficiencies.setVisibility(View.VISIBLE);
                    } else if(shipmentItem.getShippedItemCount()>Integer.parseInt(editable.toString())){
                        deficiencies.setVisibility(View.VISIBLE);
                    }else if(shipmentItem.getShippedItemCount()<Integer.parseInt(editable.toString())){
                        no_of_items_loaded.setText(String.valueOf(0));
                        deficiencies.setVisibility(View.VISIBLE);
                    }else if(shipmentItem.getShippedItemCount()==Integer.parseInt(editable.toString())){
                        deficiencies.setVisibility(GONE);
                    }
                   break;

            }
        }
    }




     class UploadingLoadingListService extends AsyncTask<Void,Void,String> {

        OkHttpClient client;
        private int responseCode;
        ProgressDialog pd;
        private final String TAG = UploadingLoadingListService.class.getSimpleName();
        private String ID;


         UploadingLoadingListService(String id) {
            Log.i(TAG, "Uploading Loading List service Initiated");
            this.ID=id;
            this.pd = new ProgressDialog(UploadLoadingSheet.this);
        }

        String doRequest(String url) throws IOException {
            Log.i(TAG, "Uploading Loading List service do Request");
            Request request = new Request.Builder().url(url+ID).build();
            Response response = client.newCall(request).execute();
            responseCode = response.code();
            Log.i(TAG, "doRequest"+ request.url().toString()+ "----"+responseCode+"-----");
            return response.body().string();
        }

        @Override
        protected String doInBackground(Void... params) {

            Log.i(TAG, "Uploading Loading List service background");
            client = new OkHttpClient();
            String url = "http://192.168.0.109:8080/shipment_item/";
            String JsonResponse = null;
            try {
                JsonResponse = doRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "Uploading Loading List service background"  +JsonResponse);
            return JsonResponse;
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "Uploading Loading List service preExecute");
            super.onPreExecute();
            pd.setCancelable(false);
            pd.show();
        }


        @Override
        protected void onPostExecute(String result) {
            Log.i(TAG, "Uploading Loading List service postExecute");
            super.onPostExecute(result);
            pd.dismiss();
            if(responseCode==0){
                LinearLayout layout=null;
                layout = (LinearLayout) findViewById(R.id.upload_loading_list_layout);
                Snackbar snackbar = Snackbar.make(layout, Constants.NO_NETWORK, Snackbar.LENGTH_LONG);
                snackbar.show();
                Log.i(TAG, "Uploading Loading List service 90 , responseCode:" + responseCode);
            } else if (result != null) {
                Gson gson=new Gson();
                shipmentItem=new ShipmentItem();
                shipmentItem=gson.fromJson(result,ShipmentItem.class);
                if(shipmentItem.getId()!=null){
                    shipment_item_layout.setVisibility(View.VISIBLE);
                    commodity_name_tag.setText(shipmentItem.getBookedItem().getCommodityName().toUpperCase());
                    item_nos_shipped.setText(String.valueOf(shipmentItem.getShippedItemCount()));
                }
                Toast.makeText(UploadLoadingSheet.this, "Booking ID List Obtained", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "Uploading Loading List service , responseCode:" + responseCode);
            }else {
                Log.i(TAG, "Uploading Loading List service , null result");
            }
        }
    }
}
