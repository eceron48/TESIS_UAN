package com.example.monitor_ua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.BatteryManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.BatteryManager.BATTERY_PROPERTY_CAPACITY;
import static android.os.BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER;
import static android.os.BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE;
import static android.os.BatteryManager.BATTERY_PROPERTY_CURRENT_NOW;
import static android.os.BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER;

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        TextView sttus=((MainActivity)context).findViewById(R.id.txt_gps);
        TextView porcentage=((MainActivity)context).findViewById(R.id.txt_bateria);
        ImageView biamgen=((MainActivity)context).findViewById(R.id.bateria_img);


        int level =intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1); //carga actual
       int scale=intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
       float  vlotege=Math.abs(intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1)) ;



        BatteryManager mBatteryManager =
                ( BatteryManager ) context . getSystemService (context . BATTERY_SERVICE );
    long energía=Long.MIN_VALUE;
      energía = mBatteryManager.getLongProperty(  BATTERY_PROPERTY_ENERGY_COUNTER);
int promedio =mBatteryManager.getIntProperty(BATTERY_PROPERTY_CURRENT_NOW);
float milis =Float.valueOf(promedio);



        float total=level*100/scale;
       porcentage.setText(promedio+" %");
       sttus.setText(energía+"mch");

        Resources res = context.getResources();

if (total>=90){
    biamgen.setImageDrawable(res.getDrawable(R.drawable.b100,null));

}else if (90 >=total &&total>=65){
    biamgen.setImageDrawable(res.getDrawable(R.drawable.b75,null));

}else if (65>total && total>=40){
    biamgen.setImageDrawable(res.getDrawable(R.drawable.b50,null));
}else if (40>=total && total>=15){
    biamgen.setImageDrawable(res.getDrawable(R.drawable.b25,null));
}else{
    biamgen.setImageDrawable(res.getDrawable(R.drawable.b0,null));
}

    }



    protected  void OnResume(){

    }
}
