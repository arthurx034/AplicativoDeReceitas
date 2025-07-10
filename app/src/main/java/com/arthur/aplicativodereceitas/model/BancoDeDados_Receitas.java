package com.arthur.aplicativodereceitas.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados_Receitas extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "banco.db";
    public static final int VERSAO_BANCO = 1;

    public static final String TABELA_RECEITAS = "receitas";
    public static final String RECEITAS_ID = "id";
    public static final String RECEITAS_NOME = "nome";
    public static final String RECEITAS_CATEGORIA = "categoria";
    public static final String RECEITAS_MODO_PREPARO = "modoPreparo";
    public static final String RECEITAS_TEMPO_PREPARO = "tempoPreparo";
    public static final String RECEITAS_PORCOES = "porcoes";
    public static final String RECEITAS_FOTOPATH = "fotoPath";
    public static final String RECEITAS_DIFICULDADE = "dificuldade";

    public static final String TABELA_INGREDIENTES = "ingredientes";
    public static final String INGREDIENTES_ID = "id";
    public static final String INGREDIENTES_RECEITA_ID = "receitaId";
    public static final String INGREDIENTES_NOME = "nome";
    public static final String INGREDIENTES_QUANTIDADE = "quantidade";
    public static final String INGREDIENTES_UNIDADE = "unidade";

    public BancoDeDados_Receitas(final Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        final String sqlReceitas = "CREATE TABLE " + TABELA_RECEITAS + " (" + RECEITAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RECEITAS_NOME + " TEXT NOT NULL, " + RECEITAS_CATEGORIA + " TEXT NOT NULL, " + RECEITAS_MODO_PREPARO + " TEXT NOT NULL, " + RECEITAS_TEMPO_PREPARO + " INTEGER NOT NULL, " + RECEITAS_PORCOES + " INTEGER NOT NULL, " + RECEITAS_FOTOPATH + " TEXT NOT NULL, " + RECEITAS_DIFICULDADE + " TEXT NOT NULL" + ")";

        final String sqlIngredientes = "CREATE TABLE " + TABELA_INGREDIENTES + " (" + INGREDIENTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + INGREDIENTES_RECEITA_ID + " INTEGER NOT NULL, " + INGREDIENTES_NOME + " TEXT NOT NULL, " + INGREDIENTES_QUANTIDADE + " TEXT NOT NULL, " + INGREDIENTES_UNIDADE + " TEXT NOT NULL" + ")";

        db.execSQL(sqlReceitas);
        db.execSQL(sqlIngredientes);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_RECEITAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_INGREDIENTES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_RECEITAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_INGREDIENTES);
        onCreate(db);
    }
}
