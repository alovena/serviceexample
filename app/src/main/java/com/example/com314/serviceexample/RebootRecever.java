package com.example.com314.serviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class RebootRecever extends BroadcastReceiver {
    static String ACTION_REBOOT="getRecevice_Reboot";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
                Intent intent1=new Intent(context,RealService.class);
                context.startForegroundService(intent1);
            }
            else{
                Intent intent1=new Intent(context,RealService.class);
                context.startService(intent1);
            }

        }
    }
}
