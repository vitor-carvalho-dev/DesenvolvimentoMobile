package com.example.saudacao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    TextView tvSaudacao;
    EditText edtNome;
    Button btnRealizarSaudacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvSaudacao = (TextView) findViewById(R.id.lblSaudacao);
        edtNome = (EditText) findViewById(R.id.txtNome);
        btnRealizarSaudacao = (Button) findViewById(R.id.cmdRealizarSaudacao);
        btnRealizarSaudacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome, saudacao;
                nome = edtNome.getText().toString();
                saudacao = "Seja bem vindo" + nome + " ao curso de PDM";
                tvSaudacao.setText(saudacao);
            }
        });
    }
}