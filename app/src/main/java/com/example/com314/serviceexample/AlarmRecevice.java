package com.example.com314.serviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class AlarmRecevice extends BroadcastReceiver {
    static String ACTION_RESTART="getRecevice_Restart";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION_RESTART)){
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
                Log.d("seo-Recevice",ACTION_RESTART);
                Intent in=new Intent(context,RestartService.class);
                context.startForegroundService(in);
            }
            else{
                Log.d("seo-Recevice",ACTION_RESTART);
                Intent service_intent=new Intent(context,RealService.class);
                context.startService(service_intent);
            }
        }
    }
}
