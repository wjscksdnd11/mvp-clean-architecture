package com.example.tranbesample.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.example.tranbesample.R;
import com.google.android.material.snackbar.Snackbar;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class InternetUtil {
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( CONNECTIVITY_SERVICE );
        if (connectivityManager!=null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
    public static void offlineCheck(View view, Context context) {
        if (!InternetUtil.isNetworkAvailable(context)){
            Snackbar.make(view,context.getString(R.string.offline_message),Snackbar.LENGTH_SHORT).show();
        }
    }
}
