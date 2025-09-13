package com.example.cfp.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.cfp.model.Receita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReceitaDAO implements ICrudDAO<Receita> {

    private SQLiteDatabase dbEscreve;
    private SQLiteDatabase dbLe;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public ReceitaDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        this.dbEscreve = dbHelper.getWritableDatabase();
        this.dbLe = dbHelper.getReadableDatabase();
    }



    @Override
    public boolean salvar(Receita receita) {
        ContentValues cv = new ContentValues();
        cv.put("descricaoReceita", receita.getDescricao());
        cv.put("valorReceita", receita.getValor());
        cv.put("dataReceita", dateFormat.format(receita.getData()));

        try {
            long id = dbEscreve.insert(DbHelper.TB_RECEITAS, null, cv);
            if (id != -1) {
                Log.i("Info DB", "Sucesso ao salvar receita");
                return true;
            } else {
                Log.i("Info DB", "Erro ao salvar receita");
                return false;
            }
        } catch (Exception e) {
            Log.e("ReceitaDAO", "Erro ao salvar receita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alterar(Receita receita) {
        ContentValues cv = new ContentValues();
        cv.put("descricaoReceita", receita.getDescricao());
        cv.put("valorReceita", receita.getValor());
        cv.put("dataReceita", dateFormat.format(receita.getData()));

        try {
            String[] args = {String.valueOf(receita.getId())};
            int linhasAfetadas = dbEscreve.update(DbHelper.TB_RECEITAS, cv, "idReceita = ?", args);
            if (linhasAfetadas > 0) {
                Log.i("Info DB", "Sucesso ao atualizar receita");
                return true;
            } else {
                Log.i("Info DB", "Erro ao atualizar receita");
                return false;
            }
        } catch (Exception e) {
            Log.e("ReceitaDAO", "Erro ao atualizar receita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Receita receita) {
        try {
            String[] args = {String.valueOf(receita.getId())};
            int linhasAfetadas = dbEscreve.delete(DbHelper.TB_RECEITAS, "idReceita = ?", args);
            if (linhasAfetadas > 0) {
                Log.i("Info DB", "Sucesso ao deletar receita");
                return true;
            } else {
                Log.i("Info DB", "Erro ao deletar receita");
                return false;
            }
        } catch (Exception e) {
            Log.e("ReceitaDAO", "Erro ao deletar receita: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Receita> listarTodos() {
        List<Receita> listaReceitas = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TB_RECEITAS + ";";

        try (Cursor cursor = dbLe.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("idReceita"));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricaoReceita"));
                double valor = cursor.getDouble(cursor.getColumnIndexOrThrow("valorReceita"));
                String dataStr = cursor.getString(cursor.getColumnIndexOrThrow("dataReceita"));

                Date data = null;
                try {
                    data = dateFormat.parse(dataStr);
                } catch (ParseException e) {
                    Log.e("ReceitaDAO", "Erro ao converter data: " + dataStr + " - " + e.getMessage());
                }

                Receita receita = new Receita(id, descricao, valor, data);
                listaReceitas.add(receita);

                Log.i("Info DB", "Receita listada: " + descricao);
            }
        } catch (Exception e) {
            Log.e("ReceitaDAO", "Erro ao listar receitas: " + e.getMessage());
        }

        return listaReceitas;
    }
    @Override
    public Receita ListarPorId(int id) {
        String sql = "SELECT * FROM " + DbHelper.TB_RECEITAS + " WHERE idReceita = ?";

        try (Cursor cursor = dbLe.rawQuery(sql, new String[]{String.valueOf(id)})) {
            if (cursor.moveToFirst()) {
                int idReceita = cursor.getInt(cursor.getColumnIndexOrThrow("idReceita"));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricaoReceita"));
                double valor = cursor.getDouble(cursor.getColumnIndexOrThrow("valorReceita"));
                String dataStr = cursor.getString(cursor.getColumnIndexOrThrow("dataReceita"));

                Date data = null;
                try {
                    data = dateFormat.parse(dataStr);
                } catch (ParseException e) {
                    Log.e("ReceitaDAO", "Erro ao converter data: " + dataStr + " - " + e.getMessage());
                }

                return new Receita(idReceita, descricao, valor, data);
            }
        } catch (Exception e) {
            Log.e("ReceitaDAO", "Erro ao buscar receita por id: " + e.getMessage());
        }

        return null;
    }
}
