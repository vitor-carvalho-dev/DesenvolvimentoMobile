package com.example.ciclodevidapos;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SegundaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda);
        setTitle("Segunda Activity");
        Log.i("Ciclo de vida Tela 2", "Metodo onCreate Ativado");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Ciclo de vida Tela 2", "Metodo onStart Ativado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ciclo de vida Tela 2", "Metodo onResume Ativado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ciclo de vida Tela 2", "Metodo onPause Ativado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ciclo de vida Tela 2", "Metodo onStop Ativado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ciclo de vida Tela 2", "Metodo onRestart Ativado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ciclo de vida Tela 2", "Metodo onDestroy Ativado");
    }
}