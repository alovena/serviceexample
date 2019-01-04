package com.example.com314.serviceexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;

public class RealService extends Service {
    MyThread mThread;

    @Override
    public void onCreate() {
        unregisterAlram();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //스레드 동작..!
        Log.d("seo-service","service-start");
        if(mThread !=null){
            try{
                mThread.interrupt();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        mThread=null;
        if(mThread==null){
            mThread=new MyThread();
            mThread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        Log.d("seo-service","service-die");
        registerAlarm();
        super.onDestroy();
    }

    private void registerAlarm() {
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 1);

        Intent intent=new Intent(RealService.this,AlarmRecevice.class);
        intent.setAction(AlarmRecevice.ACTION_RESTART);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    }
    private void unregisterAlram() {
     Intent intent=new Intent(RealService.this,AlarmRecevice.class);
     intent.setAction(AlarmRecevice.ACTION_RESTART);
     PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        am.cancel(pendingIntent);

    }
    public class MyThread extends Thread{
        private int s = 0;
        public MyThread() {
            Log.d( "set-onStart", "worker is created");
        }
        @Override
        public void run() {
            Log.d( "set-onStart", " Service Running ");
            while(true && !this.currentThread().isInterrupted()) {
                Log.d( "set-onStart", " LL : " + (s++) );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            Log.d( "set-onStart", " Terminated thread :: " + this);
        }
    }
}
