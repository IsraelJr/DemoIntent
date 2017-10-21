package com.example.israel.demointent.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.israel.demointent.R;

public class AlarmReceiver extends BroadcastReceiver{

    private MediaPlayer mp = null;

    public AlarmReceiver(){

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        mp = MediaPlayer.create(context, R.raw.despertar);
        mp.start();
        Toast.makeText(context, "Alarm...", Toast.LENGTH_SHORT).show();
    }
}
