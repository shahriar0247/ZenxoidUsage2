package com.shahriar.appusagemonitor;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

public class permission {

    static void start_intent(Context context){
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        context.startActivity(intent);

    }

}
