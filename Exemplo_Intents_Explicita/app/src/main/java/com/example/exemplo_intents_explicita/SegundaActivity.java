package com.example.exemplo_intents_explicita;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exemplo_intents_explicita.model.Usuario;


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

        Usuario usuario = (Usuario) dados.getSerializable("objetousuario");
        tvLogin.setText(usuario.getLoginUsuario());
        tvSenha.setText(usuario.getSenha());

    }
}