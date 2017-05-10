package com.shwetak3e.zentello.utilities;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Pervacio on 4/26/2017.
 */

public abstract class DetectNetworkConnectivity extends BroadcastReceiver {

        public static final String TAG = DetectNetworkConnectivity.class.getSimpleName();
        Dialog myDialog = null;
        public boolean dilaogStatus=false;
        Context mContext;

        @Override
        public void onReceive(Context context, Intent intent) {
           mContext=context;
            if(!isOnline()){
                onNetworkChange();
            }
        }

    protected  abstract  void onNetworkChange();

    public boolean isOnline() {
            ConnectivityManager cm = (ConnectivityManager)mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        }

        // Alert Dialogue
       /* public void networkMessageDialog(String title, String message) {
            Snackbar snackbar=Snackbar.make()
        }*/

    }



