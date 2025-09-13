package com.example.cfp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cfp.model.Despesa;
import com.example.cfp.persistencia.DespesaDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DespesaActivity extends AppCompatActivity {

    EditText edtDescricaoDespesa,edtValorDespesa, edtDataDespesa;
    Button btnSalvarDespesa;
    private DespesaDAO despesaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_despesa);

        edtDescricaoDespesa = findViewById(R.id.txtDespesa);
        edtValorDespesa = findViewById(R.id.txtValor);
        edtDataDespesa = findViewById(R.id.txtData);
        btnSalvarDespesa = findViewById(R.id.cmdSalvarDEspesa);
        despesaDAO = new DespesaDAO(this);
        btnSalvarDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarDespesa();
            }
        });

    }

    private void salvarDespesa() {
        String descricao = edtDescricaoDespesa.getText().toString();
        String valorStr = edtValorDespesa.getText().toString();
        String dataStr = edtDataDespesa.getText().toString();

        double valor = Double.parseDouble(valorStr);
        Date data;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
            data = dateFormat.parse(dataStr);
        } catch (Exception e) {
            Toast.makeText(this, "Formato de data inválido", Toast.LENGTH_SHORT).show();
            return;
        }

        Despesa despesa = new Despesa();
        despesa.setDescricao(descricao);
        despesa.setValor(valor);
        despesa.setData(data);


        boolean sucesso = despesaDAO.salvar(despesa);
        if (sucesso) {
            Toast.makeText(this, "Despesa salva com sucesso", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao salvar a despesa", Toast.LENGTH_SHORT).show();
        }
    }
}