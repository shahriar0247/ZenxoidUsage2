package com.shahriar.appusagemonitor;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.shahriar.appusagemonitor.create_element.adding_items;
import static com.shahriar.appusagemonitor.create_element.delete_items;

public class GatherInfo {
    private static UsageStatsManager mUsageStatsManager;
    private static LayoutInflater mInflater;
    private static  PackageManager mPm;

    public static ConstraintLayout itemlayout;
    static String converttime(String time) {
        int second = Integer.parseInt(time) / 1000;
        int minute = 0;
        int hour = 0;
        if (Integer.parseInt(time) == 0) {
            String time2 = "none";
            return time2;
        }

        while (second > 60) {
            minute = minute + 1;
            second = second - 60;
        }

        while (minute > 60) {
            hour = hour + 1;
            minute = minute - 60;

        }
        String time2 = hour + " h " + minute + " m " + second + " s ";
        return time2;
    }

    public static List<UsageStats> getting_all_apps_usage(String time, UsageStatsManager mUsageStatsManager) {


        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        int type;

        if (time == "day") {

            type = UsageStatsManager.INTERVAL_DAILY;
            cal.add(Calendar.DAY_OF_YEAR, -1);
            cal2.add(Calendar.DAY_OF_YEAR, 0);

        } else if (time == "week") {
            type = UsageStatsManager.INTERVAL_WEEKLY;
            cal.add(Calendar.WEEK_OF_YEAR, -1);
            cal2.add(Calendar.WEEK_OF_YEAR, 0);
        } else if (time == "month") {
            type = UsageStatsManager.INTERVAL_MONTHLY;
            cal.add(Calendar.MONTH, -1);
            cal2.add(Calendar.MONTH, 0);
        } else if (time == "year") {
            type = UsageStatsManager.INTERVAL_YEARLY;
            cal.add(Calendar.YEAR, -1);
            cal2.add(Calendar.YEAR, 0);
        } else {
            type = UsageStatsManager.INTERVAL_BEST;
            cal.add(Calendar.YEAR, -10);
            cal2.add(Calendar.YEAR, 0);
        }

        List<UsageStats> stats =
                mUsageStatsManager.queryUsageStats(type,
                        cal.getTimeInMillis(), System.currentTimeMillis());

        return stats;
    }


    public static List gettingservice(UsageStatsManager mUsageStatsManager, LayoutInflater mInflater, PackageManager mPm, FragmentActivity activity) {
        mUsageStatsManager = (UsageStatsManager) activity.getSystemService(Context.USAGE_STATS_SERVICE);
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPm = activity.getPackageManager();

        List all = Arrays.asList(mUsageStatsManager, mInflater, mPm);
        return all;

    }

    static void main(String time, View view, FragmentActivity activity){
        delete_items(view);
        List services = gettingservice(mUsageStatsManager, mInflater, mPm, activity);

        mUsageStatsManager = (UsageStatsManager) services.get(0);
        mInflater = (LayoutInflater) services.get(1);
        mPm = (PackageManager) services.get(2);

        List<UsageStats> stats = getting_all_apps_usage(time, mUsageStatsManager);
        for (int i = 0; i < stats.size(); i++){

            try {
                ApplicationInfo appinfo = mPm.getApplicationInfo(stats.get(i).getPackageName(), 0);
                itemlayout =  adding_items(appinfo.loadLabel(mPm).toString(), appinfo.loadIcon(mPm), converttime(Long.toString(stats.get(i).getTotalTimeInForeground())), activity,view);

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

}


