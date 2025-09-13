package com.example.cfp.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.cfp.model.Despesa;

import java.text.SimpleDateFormat;
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
    public boolean salvar(Despesa item) {
        return false;

//        SQLiteDatabase db = null;
//        try {
//            // Pega uma instância do banco de dados que permite escrita
//            db = dbHelper.getWritableDatabase();
//
//            // ContentValues é usado para mapear os valores do objeto para as colunas da tabela
//            ContentValues values = new ContentValues();
//            values.put(COL_DESCRICAO, item.getDescricaoDespesa());
//            values.put(COL_VALOR, item.getValorDespesa());
//            values.put(COL_DATA, item.getDataDespesa());
//
//            // O método insert retorna o ID da linha inserida, ou -1 se houver um erro.
//            long resultado = db.insert(TABLE_NAME, null, values);
//
//            return resultado != -1;
//
//        } catch (Exception e) {
//            Log.e("DespesaDAO", "Erro ao salvar despesa: " + e.getMessage());
//            return false;
//        } finally {
//            if (db != null) {
//                db.close(); // Sempre feche a conexão com o banco
//            }
//        }
    }

    @Override
    public boolean alterar(Despesa item) {
        return false;
    }

    @Override
    public boolean deletar(Despesa item) {
        return false;
    }

    @Override
    public List<Despesa> listarTodos() {
        return Collections.emptyList();
    }

    @Override
    public Despesa ListarPorId(int id) {
        return null;
    }
}
