package com.example.ciclodevidapos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnAbrir2Tela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnAbrir2Tela = (Button) findViewById(R.id.cmdAbrir2Tela);
        btnAbrir2Tela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, SegundaActivity.class);
                startActivity(intencao);
            }
        });
        Log.i("Ciclo de vida Tela 1", "Metodo onCreate Ativado");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ciclo de vida Tela 1", "Metodo onStart Ativado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo de vida Tela 1", "Metodo onResume Ativado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo de vida Tela 1", "Metodo onPause Ativado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo de vida Tela 1", "Metodo onStop Ativado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ciclo de vida Tela 1", "Metodo onRestart Ativado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo de vida Tela 1", "Metodo onDestroy Ativado");
    }


}