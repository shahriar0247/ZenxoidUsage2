package com.shahriar.appusagemonitor;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.FragmentActivity;

public class create_element {




    static ConstraintLayout adding_items(String appname, Drawable appicon, String time_used, FragmentActivity activity, View view){

        if (time_used == "none"){
            return null;
        }
        else{
            LinearLayout llayout1 = view.findViewById(R.id.llayout1);
            
            
            ConstraintLayout itemlayout = new ConstraintLayout(activity);
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(60,30,60,20);
            itemlayout.setLayoutParams(lp);
            itemlayout.setId(View.generateViewId());
           itemlayout.setElevation(10f);
           /*itemlayout.setBackgroundColor(Color.rgb(244,244,244));*/
           itemlayout.setPadding(20,20,20,20);
            itemlayout.setBackgroundResource(R.drawable.itemlayout_bg);
            llayout1.addView(itemlayout);




            TextView name_field = new TextView(activity);
            name_field.setText(appname);
            name_field.setLayoutParams(new ConstraintLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
            name_field.setId(View.generateViewId());
            //designing name_filed
            name_field.setTextColor(Color.parseColor("#222222"));

            ImageView icon_filed = new ImageView(activity);
            icon_filed.setLayoutParams(new ConstraintLayout.LayoutParams(150, 150));
            icon_filed.setId(View.generateViewId());

            icon_filed.setImageDrawable(appicon);


            TextView time_used_field = new TextView(activity);
            time_used_field.setText(time_used);
            time_used_field.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
            time_used_field.setId(View.generateViewId());
            time_used_field.setTextColor(Color.parseColor("#222222"));
            time_used_field.setTextSize(13);

            itemlayout.addView(name_field);
            itemlayout.addView(icon_filed);
            itemlayout.addView(time_used_field);


            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(itemlayout);
            constraintSet.connect(name_field.getId(),ConstraintSet.LEFT,icon_filed.getId(),ConstraintSet.RIGHT,32);
           /* constraintSet.connect(name_field.getId(),ConstraintSet.RIGHT,time_used_field.getId(),ConstraintSet.LEFT,32);*/
            constraintSet.connect(name_field.getId(),ConstraintSet.TOP,itemlayout.getId(),ConstraintSet.TOP,0);
            constraintSet.connect(name_field.getId(),ConstraintSet.BOTTOM,itemlayout.getId(),ConstraintSet.BOTTOM,0);

            constraintSet.connect(icon_filed.getId(),ConstraintSet.RIGHT,name_field.getId(),ConstraintSet.LEFT,0);
            constraintSet.connect(icon_filed.getId(),ConstraintSet.LEFT,itemlayout.getId(),ConstraintSet.LEFT,0);
            constraintSet.connect(icon_filed.getId(),ConstraintSet.TOP,itemlayout.getId(),ConstraintSet.TOP,0);
            constraintSet.connect(icon_filed.getId(),ConstraintSet.BOTTOM,itemlayout.getId(),ConstraintSet.BOTTOM,0);

            constraintSet.connect(time_used_field.getId(),ConstraintSet.TOP,itemlayout.getId(),ConstraintSet.TOP,0);
            constraintSet.connect(time_used_field.getId(),ConstraintSet.BOTTOM,itemlayout.getId(),ConstraintSet.BOTTOM,0);
/*            constraintSet.connect(time_used_field.getId(),ConstraintSet.LEFT,name_field.getId(),ConstraintSet.RIGHT,0);*/
            constraintSet.connect(time_used_field.getId(),ConstraintSet.RIGHT,itemlayout.getId(),ConstraintSet.RIGHT,30);

            constraintSet.applyTo(itemlayout);

            return itemlayout;
        }}


    static void delete_items(View view){
        LinearLayout llayout1 = view.findViewById(R.id.llayout1);
        llayout1.removeAllViews();
    }

}
