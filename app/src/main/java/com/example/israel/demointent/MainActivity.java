package com.example.israel.demointent;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.israel.demointent.broadcastreceiver.AlarmReceiver;
import com.example.israel.demointent.utils.Constantes;

public class MainActivity extends AppCompatActivity {

    private TextView txtNome;
    private TextView placarHome;
    private TextView placarVisitante;

    private int contHome = 0;
    private int contVisitante = 0;

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
    }

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
