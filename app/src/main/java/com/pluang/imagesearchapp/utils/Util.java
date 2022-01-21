package com.pluang.imagesearchapp.utils;

import static android.content.Context.CONNECTIVITY_SERVICE;

import android.content.Context;
import android.net.ConnectivityManager;

public  class Util {
    public static boolean isNetworkConnected(Context activity) {
        boolean data;
        ConnectivityManager manager = (ConnectivityManager)activity. getSystemService(CONNECTIVITY_SERVICE);
        //For 4G check
        boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .isConnectedOrConnecting();
        //For WiFi Check
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnectedOrConnecting();
        data = is3g || isWifi;
        return data;
    }

}
