package com.example.appimc;

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
    EditText txtPeso;
    EditText txtAltura;
    Button cmdCalculoIMC;
    TextView lblIMC;
    TextView lblClassificaoIMC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtPeso = findViewById(R.id.txtPeso);
        txtAltura = findViewById(R.id.txtAltura);
        cmdCalculoIMC = findViewById(R.id.cmdCalculoIMC);
        lblIMC = findViewById(R.id.lblIMC);
        lblClassificaoIMC = findViewById(R.id.lblClassificaoIMC);

        cmdCalculoIMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoPeso = txtPeso.getText().toString();
                String textoAltura = txtAltura.getText().toString();

                try {
                    double peso = Double.parseDouble(textoPeso);
                    double altura = Double.parseDouble(textoAltura);

                    if (altura == 0) {
                        lblIMC.setText("A altura não pode ser zero.");
                        lblClassificaoIMC.setText("");
                        return;
                    }

                    double imc = peso / (altura * altura);

                    String classificacao;
                    if (imc < 18.5) {
                        classificacao = "Abaixo do peso";
                    } else if (imc < 24.9) {
                        classificacao = "Peso normal";
                    } else if (imc < 29.9) {
                        classificacao = "Sobrepeso";
                    } else if (imc < 34.9) {
                        classificacao = "Obesidade Grau I";
                    } else if (imc < 39.9) {
                        classificacao = "Obesidade Grau II";
                    } else {
                        classificacao = "Obesidade Grau III";
                    }

                    lblIMC.setText(String.format("Seu IMC é: %.1f", imc));
                    lblClassificaoIMC.setText(String.format("Classificação: %s", classificacao));

                } catch (NumberFormatException e) {
                    lblIMC.setText("Digite apenas números válidos.");
                    lblClassificaoIMC.setText("");
                }
            }
        });


    }
}