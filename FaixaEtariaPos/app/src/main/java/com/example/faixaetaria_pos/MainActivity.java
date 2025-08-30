package com.example.faixaetaria_pos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tvFaixaEtaria;
    EditText edtIdade;
    Button btnClassificarFaixaEtaria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        tvFaixaEtaria = (TextView) findViewById(R.id.lblFaixaEtaria);
        edtIdade = (EditText) findViewById(R.id.txtIdade);
        btnClassificarFaixaEtaria = (Button) findViewById(R.id.cmdFaixaEtaria);
        btnClassificarFaixaEtaria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idadeDigitada = edtIdade.getText().toString();

                try {

                    int idade = Integer.parseInt(idadeDigitada);
                    String classificacao;

                    if (idade>= 0 && idade<= 12) {
                        classificacao = "Criança";
                    } else if (idade >= 13 && idade<= 17) {
                        classificacao = "Adolescente";
                    } else if (idade>= 18 && idade<= 29) {
                        classificacao = "Jovem Adulto";
                    } else if (idade>= 30 && idade <= 59) {
                        classificacao = "Adulto";
                    } else {
                        classificacao = "Idoso";
                    }

                    String mensagemFinal = String.format("Com %d anos, a sua faixa etária é: %s", idade, classificacao);
                    tvFaixaEtaria.setText(mensagemFinal);

                } catch (NumberFormatException e) {

                    tvFaixaEtaria.setText("Ops! Por favor, digite um número válido.");
                }
            }
        });

    }
}