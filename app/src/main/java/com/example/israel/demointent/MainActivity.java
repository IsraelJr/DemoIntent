package com.example.israel.demointent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.israel.demointent.broadcastreceiver.AlarmReceiver;
import com.example.israel.demointent.services.BoundService;
import com.example.israel.demointent.utils.Constantes;

public class MainActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView placarHome;
    private TextView placarVisitante;

    private int contHome = 0;
    private int contVisitante = 0;

    BoundService mBoundService;
    boolean mServiceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNome = (TextView) findViewById(R.id.tvLogin);
        placarHome = (TextView) findViewById(R.id.tvPlacarHome);
        placarVisitante = (TextView) findViewById(R.id.tvPlacareVisitante);

        if(savedInstanceState != null){
            contHome = savedInstanceState.getInt(Constantes.KEY_PLACAR_CASA);
            contVisitante = savedInstanceState.getInt(Constantes.KEY_PLACAR_VISITANTE);
        }

        placarHome.setText(String.valueOf(contHome));
        placarVisitante.setText(String.valueOf(contVisitante));

        if(getIntent() != null){
            txtNome.setText(getIntent().getStringExtra(Constantes.KEY_LOGIN));
        }

        final TextView timestampText = (TextView) findViewById(R.id.timestamp_text);
        Button printTimestampButton = (Button) findViewById(R.id.print_timestamp);
        Button stopServiceButton = (Button) findViewById(R.id.stop_service);

        printTimestampButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mServiceBound){
                timestampText.setText(mBoundService.getTimestamp());
            }
        }
    });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mServiceBound){
                    unbindService(mServiceConnection);
                    mServiceBound = false;
                }
                Intent intent = new Intent(MainActivity.this, BoundService.class);
                stopService(intent);
            }
        });


    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
            //mBoundService = myBinder.getService();
            mServiceBound = true;
        }
    };

    public void golCasa(View v){
        contHome++;
        placarHome.setText(String.valueOf(contHome));
    }

    public void golVisitante(View v){
        contVisitante++;
        placarVisitante.setText(String.valueOf(contVisitante));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constantes.KEY_PLACAR_CASA, contHome);
        outState.putInt(Constantes.KEY_PLACAR_VISITANTE, contVisitante);
    }

    public void alarme(View v){

        EditText text = (EditText) findViewById(R.id.tempoJogo);
        int i = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP,
                System. currentTimeMillis() + (i * 1000),
                pendingIntent);
        Toast.makeText(this, "Alarm set in " +i+ " seconds",Toast.LENGTH_LONG).show();


    }
}
