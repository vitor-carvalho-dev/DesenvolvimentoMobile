package com.example.components;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.components.model.Pessoa;

public class MainActivity extends AppCompatActivity {

   // RadioButton rbFeminino, rbMasculino;
    EditText edtNome, edtData;
    RadioGroup radioGrupoSexo;
    CheckBox checkFrontEnd, checkBacktEnd;
    Switch switchNotificacao;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtNome = (EditText) findViewById(R.id.txtNome);
        edtData = (EditText) findViewById(R.id.txtData);
        radioGrupoSexo = (RadioGroup) findViewById(R.id.rdGroupSexo);
        checkFrontEnd = (CheckBox) findViewById(R.id.ckbFrontEnd);
        checkBacktEnd = (CheckBox) findViewById(R.id.ckbBackEnd);
        switchNotificacao = (Switch) findViewById(R.id.swNotificacao);
        btnEnviar = (Button) findViewById(R.id.cmdEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoa pessoa = new Pessoa();
                Intent intencao = new Intent(MainActivity.this, DadosActivity.class);

                pessoa.setNome(edtNome.getText().toString());

                if(radioGrupoSexo.getCheckedRadioButtonId() == R.id.rbMasculino) {
                    pessoa.setSexo("Masculino");
                } else {
                    pessoa.setSexo("Feminino");
                }

                StringBuilder resultado = new StringBuilder();
                if(checkBacktEnd.isChecked()){
                    resultado.append("\n Back-End");
                }

                if(checkFrontEnd.isChecked()){
                    resultado.append("\n Front-End");
                }

                pessoa.setAreaAtuacao(resultado.toString());

                if(switchNotificacao.isChecked()){
                    pessoa.setNotificacao("Notificacao autorizada");
                } else {
                    pessoa.setNotificacao("Notificacao nao autorizada");
                }

                intencao.putExtra("objetopessoa", pessoa);
                startActivity(intencao);

            }
        });
    }
}