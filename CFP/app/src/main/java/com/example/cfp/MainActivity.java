package com.example.cfp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cfp.adapters.AdapterDespesa;
import com.example.cfp.adapters.AdapterReceita;
import com.example.cfp.model.Despesa;
import com.example.cfp.model.Receita;
import com.example.cfp.persistencia.DespesaDAO;
import com.example.cfp.persistencia.ReceitaDAO;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvDespesa, tvReceita, tvValorLiquido;
    Button btnNovaDespesa, btnNovaReceita;

    RecyclerView recycleDespesa, recycleReceita;

    TabLayout tabLayout;

    private AdapterDespesa despesaAdapter;

    private AdapterReceita receitaAdapter;

    private Despesa despesaSelecionado;
    private Receita receitaSelecionado;

    List<Despesa> listaDespesas = new ArrayList<>();
    List<Receita> listaReceitas = new ArrayList<>();

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
        btnNovaDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, DespesaActivity.class);
                startActivity(intencao);
            }
        });
        btnNovaReceita = (Button) findViewById(R.id.cmdNovaReceita);
        btnNovaReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(MainActivity.this, ReceitaActivity.class);
                startActivity(intencao);
            }
        });

        // Configurar RecyclerViews
        //RecyclerView Despesas
        recycleDespesa.setLayoutManager(new LinearLayoutManager(this));
        recycleDespesa.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recycleDespesa, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                despesaSelecionado = listaDespesas.get(position);
                Intent intencao = new Intent(MainActivity.this, DespesaActivity.class);
                intencao.putExtra("contatoSelecionado",despesaSelecionado);
                startActivity(intencao);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        recycleReceita.setLayoutManager(new LinearLayoutManager(this));
        recycleReceita.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recycleReceita, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                receitaSelecionado= listaReceitas.get(position);
                Intent intencao = new Intent(MainActivity.this, DespesaActivity.class);
                intencao.putExtra("contatoSelecionado",receitaSelecionado);
                startActivity(intencao);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

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
                    CarregarDespesas();
                } else {
                    recycleDespesa.setVisibility(View.GONE);
                    recycleReceita.setVisibility(View.VISIBLE);
                    btnNovaDespesa.setVisibility(View.GONE);
                    btnNovaReceita.setVisibility(View.VISIBLE);
                    CarregarReceitas();
                }
                updateSummary(); // Atualizar o resumo financeiro
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
    private void CarregarDespesas() {
        DespesaDAO despesaDAO = new DespesaDAO(this);
        listaDespesas = despesaDAO.listarTodos();
        Log.d("Carregar dados", "Quantidade de registro " + listaDespesas.size());
        //Configurar o meu adapter
        despesaAdapter= new AdapterDespesa(listaDespesas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleDespesa.setLayoutManager(layoutManager);
        recycleDespesa.setHasFixedSize(true);
        recycleDespesa.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recycleDespesa.setAdapter(despesaAdapter);
    }

    private void CarregarReceitas() {
        ReceitaDAO receitaDAO = new ReceitaDAO(this);
        listaReceitas = receitaDAO.listarTodos();
        Log.d("Carregar dados receitas", "Quantidade de registro " + listaReceitas.size());
        //Configurar o meu adapter
        receitaAdapter= new AdapterReceita(listaReceitas);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleReceita.setLayoutManager(layoutManager);
        recycleReceita.setHasFixedSize(true);
        recycleReceita.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recycleReceita.setAdapter(receitaAdapter);
    }

    private void updateSummary() {
        DespesaDAO despesaDAO = new DespesaDAO(this);
        ReceitaDAO receitaDAO = new ReceitaDAO(this);

        double totalExpensesValue = 0.0;
        double totalRevenuesValue = 0.0;

        for (Despesa despesa : despesaDAO.listarTodos()) {
            totalExpensesValue += despesa.getValor();
        }

        for (Receita receita : receitaDAO.listarTodos()) {
            totalRevenuesValue += receita.getValor();
        }

        double balance = totalRevenuesValue - totalExpensesValue;

        // Atualiza os TextViews
        tvDespesa.setText(String.format("Total Despesas: R$ %.2f", totalExpensesValue));
        tvReceita.setText(String.format("Total Receitas: R$ %.2f", totalRevenuesValue));
        tvValorLiquido.setText(String.format("Valor Líquido: R$ %.2f", balance));
    }


}