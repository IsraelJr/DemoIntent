package com.example.israel.demointent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (EditText) findViewById(R.id.etLogin);
        senha = (EditText) findViewById(R.id.etSenha);

    }

    public void fazerConexao(View v){

        Intent validaLogin = new Intent(this, ValidaLoginActivity.class);
        validaLogin.putExtra("login", "login.get");

    }

}
