package com.example.israel.demointent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.israel.demointent.utils.Constantes;

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
        validaLogin.putExtra(Constantes.KEY_LOGIN, login.getText().toString());
        validaLogin.putExtra(Constantes.KEY_SENHA, senha.getText().toString());
        startActivityForResult(validaLogin, Constantes.REQUEST_CODE_VALIDA_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case Constantes.REQUEST_CODE_VALIDA_LOGIN:
                switch (resultCode) {
                    case RESULT_OK:
                        boolean ehLoginValido = data.getBooleanExtra(Constantes.KEY_RESULT_LOGIN, false);
                        if(ehLoginValido){
                            Toast.makeText(this,"Login valido!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(this,"Login invalido!", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case RESULT_CANCELED:
                        break;
            }
        }
    }
}
