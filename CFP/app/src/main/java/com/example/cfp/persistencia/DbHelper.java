package com.example.cfp.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "financeiro.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TB_DESPESAS = "despesas.db";
    public static final String TB_RECEITAS = "receitas.db";

    private static final String SQL_CREATE_DESPESAS = "CREATE TABLE IF NOT EXISTS "
            + TB_DESPESAS + " ("
            + "idDespesa INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "descricaoDespesa TEXT NOT NULL, "
            + "valorDespesa REAL NOT NULL, "
            + "dataDespesa TEXT NOT NULL"
            + ");";

    // SQL para criar a tabela de receitas (sintaxe corrigida)
    private static final String SQL_CREATE_RECEITAS = "CREATE TABLE IF NOT EXISTS "
            + TB_RECEITAS + " ("
            + "idReceita INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "descricaoReceita TEXT NOT NULL, "
            + "valorReceita REAL NOT NULL, "
            + "dataReceita TEXT NOT NULL"
            + ");";

    private static final String SQL_DELETE_DESPESAS= ("DROP TABLE IF EXISTS " + TB_DESPESAS);
    private static final String SQL_DELETE_RECEITAS = ("DROP TABLE IF EXISTS " + TB_RECEITAS);
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void executeSQL(SQLiteDatabase db, String sql, String tableName) {

        try {
            db.execSQL(sql);
            Log.i("INFO DB", "SUCESSO AO CRIAR SQL NA TABELA: " + tableName);
        } catch (Exception e) {
            Log.e("INFO DB", "ERRO AO CRIAR A TABELA" + tableName + " " + e.getMessage());
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_DESPESAS);
        db.execSQL(SQL_CREATE_RECEITAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        executeSQL(db, SQL_DELETE_DESPESAS, TB_DESPESAS);
        executeSQL(db, SQL_DELETE_RECEITAS, TB_RECEITAS);
        onCreate(db);
    }
}
