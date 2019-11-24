package com.blogspot.sslaia.achidanews.helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

/**
 * Created by Saurabh(aqua) in 2017.
 * https://gist.github.com/aqua30/e16509f70176b6770a3373aa08cf29a3
 * Thank you so much Saurabh
 */

public class ConnectionLiveData extends LiveData<ConnectionModel> {

    private static final int MobileData = 2;
    private static final int WifiData = 1;
    private Context context;

    public ConnectionLiveData(Context context) {
        this.context = context;
    }



    @Override
    protected void onActive() {
        super.onActive();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(networkReceiver, filter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        context.unregisterReceiver(networkReceiver);
    }

    private BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @SuppressWarnings("deprecation")
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras()!=null) {
                NetworkInfo activeNetwork = (NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if(isConnected) {
                    switch (activeNetwork.getType()){
                        case ConnectivityManager.TYPE_WIFI:
                            postValue(new ConnectionModel(WifiData,true));
                            break;
                        case ConnectivityManager.TYPE_MOBILE:
                            postValue(new ConnectionModel(MobileData,true));
                            break;
                    }
                } else {
                    postValue(new ConnectionModel(0,false));
                }
            }
        }
    };
}