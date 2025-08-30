package com.example.exemplo_intents_explicita;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtLogin, edtSenha;
    Button btnAutenticar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtLogin = (EditText) findViewById(R.id.txtLogin);
        edtSenha = (EditText) findViewById(R.id.txtSenha);
        btnAutenticar = (Button) findViewById(R.id.cmdAutenticar);
        btnAutenticar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginDigitado = edtLogin.getText().toString();
                String senhaDigitada = edtSenha.getText().toString();

                String login = "admin";
                String senha = "1234";

                if (loginDigitado.equals(login) && senhaDigitada.equals(senha)) {
                    Intent intencao = new Intent(MainActivity.this, SegundaActivity.class);
                    intencao.putExtra("login", loginDigitado);
                    intencao.putExtra("senha", senhaDigitada);
                    startActivity(intencao);
                    finish();
                }
            }
        });
    }
}