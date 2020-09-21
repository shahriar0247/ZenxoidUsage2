package com.shahriar.appusagemonitor.service;

import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.AbstractSequentialList;
import java.util.List;
import java.util.TreeMap;

import static com.shahriar.appusagemonitor.GatherInfo.*;


public class duration_used_with_time extends Service {
    public duration_used_with_time() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    private static UsageStatsManager mUsageStatsManager;
    @Override
    public void onCreate() {
        super.onCreate();

        mUsageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);


        List<UsageStats> stats = getting_all_apps_usage("day", mUsageStatsManager);
        for (int i = 0; i < stats.size(); i++){


               Log.d("name", stats.get(i).getPackageName() + "asd" + stats.get(i));


        }

    }

    String foregroundapp(){

        String currentApp = "NULL";
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager usm = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);

            List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  System.currentTimeMillis() - 1000*1000, System.currentTimeMillis());
            if (appList != null && appList.size() > 0) {
                TreeMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : appList) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    currentApp = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                    Log.d("sad", currentApp);
                }
            }
        } else {
            ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> tasks = am.getRunningAppProcesses();
            currentApp = tasks.get(0).processName;
        }
        return currentApp;
    }

   /* void foregroundappcheck(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String foreground_app;
                String current_app = "NULL";

                TreeMap<String, Long, String> apps = new TreeMap<String, Long, String>()
                synchronized (this) {
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    foreground_app = foregroundapp();
                    if (foreground_app == current_app) {


                    }
                    else{


                    }
                }

            }
        };*/

    /*    Thread thread = new Thread(runnable);
        thread.start();
    }*/
}
