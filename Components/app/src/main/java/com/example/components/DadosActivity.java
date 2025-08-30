package com.example.components;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.components.model.Pessoa;

public class DadosActivity extends AppCompatActivity {

    TextView tvNome, tvlAreaAtuacao, tvSexo, tvNotificacao, tvDataAgendamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dados);
        tvNome = (TextView) findViewById(R.id.lblNome);
        tvlAreaAtuacao = (TextView) findViewById(R.id.lblAreaAtuacao);
        tvSexo = (TextView) findViewById(R.id.lblSexo);
        tvNotificacao = (TextView) findViewById(R.id.lblNotificacao);
        tvDataAgendamento = (TextView) findViewById(R.id.lblDataAgendamento);

        Bundle dados = getIntent().getExtras();

        Pessoa pessoa = (Pessoa) dados.getSerializable("objetopessoa");

        tvNome.setText(pessoa.getNome());
        tvlAreaAtuacao.setText(pessoa.getAreaAtuacao());
        tvSexo.setText(pessoa.getSexo());
        tvNotificacao.setText(pessoa.getNotificacao());
        tvDataAgendamento.setText(pessoa.getDataAgendamento());



    }
}