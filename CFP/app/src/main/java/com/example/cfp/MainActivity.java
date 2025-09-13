package com.example.cfp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TextView tvDespesa, tvReceita, tvValorLiquido;
    Button btnNovaDespesa, btnNovaReceita;

    RecyclerView recycleDespesa, recycleReceita;

    TabLayout tabLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvDespesa = (TextView) findViewById(R.id.lblDespesa);
        tvReceita = (TextView) findViewById(R.id.lblReceita);
        tvValorLiquido = (TextView) findViewById(R.id.lblValorTotal);
        recycleReceita = (RecyclerView) findViewById(R.id.rvListaReceitas);
        recycleDespesa = (RecyclerView) findViewById(R.id.rvListaDespesas);
        btnNovaDespesa = (Button) findViewById(R.id.cmdNovaDespesa);
        btnNovaReceita = (Button) findViewById(R.id.cmdNovaReceita);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        // clique dos botoes
        //

        // Configurar TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    recycleDespesa.setVisibility(View.VISIBLE);
                    recycleReceita.setVisibility(View.GONE);
                    btnNovaDespesa.setVisibility(View.VISIBLE);
                    btnNovaReceita.setVisibility(View.GONE);
                   // CarregarDespesas();
                } else {
                    recycleDespesa.setVisibility(View.GONE);
                    recycleReceita.setVisibility(View.VISIBLE);
                    btnNovaDespesa.setVisibility(View.GONE);
                    btnNovaReceita.setVisibility(View.VISIBLE);
                    // CarregarReceitas();
                }
               // updateSummary(); // Atualizar o resumo financeiro
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Não é necessário fazer nada
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Não é necessário fazer nada
            }
        });

        // Adicionar abas
        tabLayout.addTab(tabLayout.newTab().setText("Despesas"));
        tabLayout.addTab(tabLayout.newTab().setText("Receitas"));

    }
}