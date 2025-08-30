package com.example.exemplo_intents_explicita;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SegundaActivity extends AppCompatActivity {

    TextView tvLogin, tvSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);
        tvLogin = (TextView) findViewById(R.id.lblLogin);
        tvSenha = (TextView) findViewById(R.id.lblSenha);

        Bundle dados = getIntent().getExtras();

        tvLogin.setText(dados.getString("login"));
        tvSenha.setText(dados.getString("senha"));
    }
}