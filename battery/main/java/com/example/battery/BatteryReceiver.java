package com.example.battery;

import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.widget.TextView;

public class BatteryReceiver extends BroadcastReceiver{
    TextView level;
    public BatteryReceiver (TextView levelIn){
        level=levelIn;
    }

    @Override
    public void onReceive(Context context, Intent intent){
        int p = intent.getIntExtra("level",0);
        if (p != 0){
            level.setText(p+"%");
        }

    }
}
