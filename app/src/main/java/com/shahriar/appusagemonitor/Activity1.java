package com.shahriar.appusagemonitor;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.android.billingclient.api.BillingClient;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.shahriar.appusagemonitor.service.Overlay;
import com.shahriar.appusagemonitor.service.duration_used_with_time;
import com.shahriar.appusagemonitor.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;



import static com.shahriar.appusagemonitor.permission.start_intent;

public class Activity1 extends AppCompatActivity {

    private AdView mAdView;
    Boolean purchased = false;


    private BillingClient billingClient;
    private List skulist = new ArrayList();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_1);

     /*   Intent duration_used_with_time = new Intent(this, com.shahriar.appusagemonitor.service.duration_used_with_time.class);
        startService(duration_used_with_time);*/


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d("addss", "ads initialized");
            }
        });




        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        if (!isAccessGranted()) {
            start_intent(this);
        }

              SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(10);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);




    }

    private boolean isAccessGranted() {
        try {
            PackageManager packageManager = getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
            AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
            int mode = 0;
            if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
                mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                        applicationInfo.uid, applicationInfo.packageName);
            }
            return (mode == AppOpsManager.MODE_ALLOWED);

        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void onResume(){
        super.onResume();
    }




    void prem() {
        purchased = true;

        ConstraintLayout clayout2 = findViewById(R.id.clayout2);
        ConstraintSet cs = new ConstraintSet();
        cs.clone(clayout2);

        cs.connect(R.id.adView, ConstraintSet.BOTTOM, R.id.clayout2, ConstraintSet.BOTTOM);
        cs.applyTo(clayout2);
        clayout2.removeView(mAdView);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.purchase1:
                break;

        }
        return true;
    }


}