package com.example.cfp.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "financeiro.db";
    private static final int DATABASE_VERSION = 2; // ⚠️ INCREMENTEI A VERSÃO

    public static final String TB_DESPESAS = "despesas";
    public static final String TB_RECEITAS = "receitas";

    // ✅ SQL COMPLETAMENTE CORRIGIDO - Testado e funcional
    private static final String SQL_CREATE_DESPESAS =
            "CREATE TABLE " + TB_DESPESAS + " (" +
                    "idDespesa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricaoDespesa TEXT NOT NULL, " +
                    "valorDespesa REAL NOT NULL, " +
                    "dataDespesa TEXT NOT NULL" +
                    ")";

    private static final String SQL_CREATE_RECEITAS =
            "CREATE TABLE " + TB_RECEITAS + " (" +
                    "idReceita INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricaoReceita TEXT NOT NULL, " +
                    "valorReceita REAL NOT NULL, " +
                    "dataReceita TEXT NOT NULL" +
                    ")";

    private static final String SQL_DELETE_DESPESAS = "DROP TABLE IF EXISTS " + TB_DESPESAS;
    private static final String SQL_DELETE_RECEITAS = "DROP TABLE IF EXISTS " + TB_RECEITAS;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB_DEBUG", "=== INICIANDO CRIAÇÃO DO BANCO ===");

        // Criar tabela despesas
        try {
            Log.d("DB_DEBUG", "SQL Despesas: " + SQL_CREATE_DESPESAS);
            db.execSQL(SQL_CREATE_DESPESAS);
            Log.i("DB_SUCCESS", "✅ TABELA DESPESAS CRIADA COM SUCESSO");
        } catch (Exception e) {
            Log.e("DB_ERROR", "❌ ERRO AO CRIAR TABELA DESPESAS: " + e.getMessage());
            throw e; // Re-throw para não continuar com erro
        }

        // Criar tabela receitas
        try {
            Log.d("DB_DEBUG", "SQL Receitas: " + SQL_CREATE_RECEITAS);
            db.execSQL(SQL_CREATE_RECEITAS);
            Log.i("DB_SUCCESS", "✅ TABELA RECEITAS CRIADA COM SUCESSO");
        } catch (Exception e) {
            Log.e("DB_ERROR", "❌ ERRO AO CRIAR TABELA RECEITAS: " + e.getMessage());
            throw e; // Re-throw para não continuar com erro
        }

        // Verificar se as tabelas foram realmente criadas
        verificarTabelasCriadas(db);

        Log.d("DB_DEBUG", "=== CRIAÇÃO DO BANCO FINALIZADA ===");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB_DEBUG", "=== ATUALIZANDO BANCO DE " + oldVersion + " PARA " + newVersion + " ===");

        // Dropar tabelas existentes
        try {
            db.execSQL(SQL_DELETE_DESPESAS);
            Log.i("DB_SUCCESS", "✅ TABELA DESPESAS REMOVIDA");
        } catch (Exception e) {
            Log.w("DB_WARNING", "⚠️ ERRO AO REMOVER TABELA DESPESAS: " + e.getMessage());
        }

        try {
            db.execSQL(SQL_DELETE_RECEITAS);
            Log.i("DB_SUCCESS", "✅ TABELA RECEITAS REMOVIDA");
        } catch (Exception e) {
            Log.w("DB_WARNING", "⚠️ ERRO AO REMOVER TABELA RECEITAS: " + e.getMessage());
        }

        // Recriar tabelas
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d("DB_DEBUG", "=== BANCO ABERTO ===");
        verificarTabelasCriadas(db);
    }

    // ✅ MÉTODO PARA VERIFICAR SE AS TABELAS EXISTEM
    private void verificarTabelasCriadas(SQLiteDatabase db) {
        // Verificar tabela despesas
        try {
            db.rawQuery("SELECT COUNT(*) FROM " + TB_DESPESAS, null).close();
            Log.i("DB_VERIFY", "✅ TABELA DESPESAS EXISTE E É ACESSÍVEL");
        } catch (Exception e) {
            Log.e("DB_VERIFY", "❌ TABELA DESPESAS NÃO EXISTE OU NÃO É ACESSÍVEL: " + e.getMessage());
        }

        // Verificar tabela receitas
        try {
            db.rawQuery("SELECT COUNT(*) FROM " + TB_RECEITAS, null).close();
            Log.i("DB_VERIFY", "✅ TABELA RECEITAS EXISTE E É ACESSÍVEL");
        } catch (Exception e) {
            Log.e("DB_VERIFY", "❌ TABELA RECEITAS NÃO EXISTE OU NÃO É ACESSÍVEL: " + e.getMessage());
        }
    }

    // ✅ MÉTODO PÚBLICO PARA DEBUG - LISTAR TODAS AS TABELAS
    public void listarTodasTabelas() {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%'";
            android.database.Cursor cursor = db.rawQuery(sql, null);

            Log.d("DB_TABLES", "=== TABELAS NO BANCO ===");
            while (cursor.moveToNext()) {
                String tableName = cursor.getString(0);
                Log.d("DB_TABLES", "📋 Tabela encontrada: " + tableName);
            }
            cursor.close();

            if (cursor.getCount() == 0) {
                Log.w("DB_TABLES", "⚠️ NENHUMA TABELA ENCONTRADA NO BANCO!");
            }
        } catch (Exception e) {
            Log.e("DB_TABLES", "❌ ERRO AO LISTAR TABELAS: " + e.getMessage());
        }
    }

    // ✅ MÉTODO PARA FORÇAR RECRIAÇÃO DO BANCO (DEBUG)
    public void recriarBanco(Context context) {
        Log.w("DB_DEBUG", "🔄 FORÇANDO RECRIAÇÃO DO BANCO");
        context.deleteDatabase(DATABASE_NAME);

        // Reabrir conexão para forçar onCreate
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
    }
}