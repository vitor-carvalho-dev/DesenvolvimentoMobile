package com.example.cfp.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cfp.model.Despesa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DespesaDAO implements ICrudDAO<Despesa>{
    private SQLiteDatabase dbEscreve;
    private SQLiteDatabase dbLe;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public DespesaDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        this.dbEscreve = dbHelper.getWritableDatabase();
        this.dbLe = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean salvar(Despesa despesa) {
        ContentValues cv = new ContentValues();
        cv.put("descricaoDespesa", despesa.getDescricao());
        cv.put("valorDespesa", despesa.getValor());
        cv.put("dataDespesa", dateFormat.format(despesa.getData()));

       try {
            long id = dbEscreve.insert(DbHelper.TB_DESPESAS,null,cv);
            if(id != -1) {
                Log.i("Info DB", "Sucesso ao salvar e registrar na tabela Despesa");
                return true;
            } else {
                Log.i("Info DB", "Erro ao salvar e registrar na tabela Despesa");
                return false;
            }
         } catch (Exception e) {
             Log.e("DespesaDAO", "Erro ao salvar despesa: " + e.getMessage());
          return false;
       }
    }

    @Override
    public boolean alterar(Despesa despesa) {
        ContentValues cv = new ContentValues();
        cv.put("descricaoDespesa", despesa.getDescricao());
        cv.put("valorDespesa", despesa.getValor());
        cv.put("dataDespesa", dateFormat.format(despesa.getData()));

        try {
            String[] args = {String.valueOf(despesa.getId())};
            int linhaAfetada = dbEscreve.update(DbHelper.TB_DESPESAS, cv, "idDespesa", args );
            if(linhaAfetada > 0) {
                Log.i("Info DB", "Sucesso ao atualizar e registrar na tabela Despesa");
                return true;
            } else {
                Log.i("Info DB", "Erro ao atualizar e registrar na tabela Despesa");
                return false;
            }
        } catch (Exception e) {
            Log.e("DespesaDAO", "Erro ao atualizar despesa: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Despesa despesa) {
        try {
            String[] args = {String.valueOf(despesa.getId())};
            int linhaAfetada = dbEscreve.delete(DbHelper.TB_DESPESAS, "idDespesa", args );
            if(linhaAfetada > 0) {
                Log.i("Info DB", "Sucesso ao deletar e registrar na tabela Despesa");
                return true;
            } else {
                Log.i("Info DB", "Erro ao deletar e registrar na tabela Despesa");
                return false;
            }
        } catch (Exception e) {
            Log.e("DespesaDAO", "Erro ao deletar despesa: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Despesa> listarTodos() {
        List<Despesa> lista = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TB_DESPESAS + " ; " ;

        try(Cursor cursor = dbLe.rawQuery(sql, null)){
            while(cursor.moveToNext()) {
                
            }

        } catch (Exception e) {}

    }

    @Override
    public Despesa ListarPorId(int id) {
        return null;
    }
}
