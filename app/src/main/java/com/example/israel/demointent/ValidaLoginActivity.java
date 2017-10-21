package com.example.israel.demointent;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.israel.demointent.utils.Constantes;

public class ValidaLoginActivity extends AppCompatActivity {

    private final String LOGIN = "android";
    private final String SENHA = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valida_login);

        if(getIntent() != null) {
            String login = (getIntent().getStringExtra(Constantes.KEY_LOGIN));
            String senha = (getIntent().getStringExtra(Constantes.KEY_SENHA));
            validaLogin(login, senha);
        }
    }

    private void validaLogin(final String login, final String senha){
       Handler tempoEspera =  new Handler();
        tempoEspera.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent result = new Intent();
                if(login.equals(LOGIN) && senha.equals(SENHA)) {
                    result.putExtra(Constantes.KEY_RESULT_LOGIN, true);
                }else {
                    result.putExtra(Constantes.KEY_RESULT_LOGIN, false);
                }
                setResult(RESULT_OK, result);
                finish();
            }
        }, 2000);
    }
}
