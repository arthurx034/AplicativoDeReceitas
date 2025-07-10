package com.arthur.aplicativodereceitas.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.arthur.aplicativodereceitas.model.BancoDeDados_Receitas;
import com.arthur.aplicativodereceitas.model.Receita;

import java.util.ArrayList;
import java.util.List;

public class DBController_Receita {
    private final SQLiteDatabase db;

    public DBController_Receita(final Context context) {
        BancoDeDados_Receitas banco = new BancoDeDados_Receitas(context);
        this.db = banco.getWritableDatabase();
    }

    // --- CONTATOS ---

    public void adicionarReceita(final Receita receita) {
        final ContentValues valores = new ContentValues();
        valores.put(BancoDeDados_Receitas.RECEITAS_NOME, receita.getNome());
        valores.put(BancoDeDados_Receitas.RECEITAS_CATEGORIA, receita.getCategoria());
        valores.put(BancoDeDados_Receitas.RECEITAS_MODO_PREPARO, receita.getModoPreparo());
        valores.put(BancoDeDados_Receitas.RECEITAS_TEMPO_PREPARO, receita.getTempoPreparo());
        valores.put(BancoDeDados_Receitas.RECEITAS_PORCOES, receita.getPorcoes());
        valores.put(BancoDeDados_Receitas.RECEITAS_FOTOPATH, receita.getFotoPath());
        valores.put(BancoDeDados_Receitas.RECEITAS_DIFICULDADE, receita.getDificuldade());

        db.insert(BancoDeDados_Receitas.TABELA_RECEITAS, null, valores);
    }

    public Receita buscarReceitaPorId(final int id) {
        Receita receita = null;

        try (Cursor cursor = db.query(
                BancoDeDados_Receitas.TABELA_RECEITAS,
                null,
                BancoDeDados_Receitas.RECEITAS_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null)) {
            if (cursor.moveToFirst()) {
                receita = mapearContato(cursor);
            }
        }

        return receita;
    }

    public void editarReceita(final Receita receita) {
        final ContentValues valores = new ContentValues();
        valores.put(BancoDeDados_Receitas.RECEITAS_NOME, receita.getNome());
        valores.put(BancoDeDados_Receitas.RECEITAS_CATEGORIA, receita.getCategoria());
        valores.put(BancoDeDados_Receitas.RECEITAS_MODO_PREPARO, receita.getModoPreparo());
        valores.put(BancoDeDados_Receitas.RECEITAS_TEMPO_PREPARO, receita.getTempoPreparo());
        valores.put(BancoDeDados_Receitas.RECEITAS_PORCOES, receita.getPorcoes());
        valores.put(BancoDeDados_Receitas.RECEITAS_FOTOPATH, receita.getFotoPath());
        valores.put(BancoDeDados_Receitas.RECEITAS_DIFICULDADE, receita.getDificuldade());

        db.update(
                BancoDeDados_Receitas.TABELA_RECEITAS,
                valores,
                BancoDeDados_Receitas.RECEITAS_ID + "=?",
                new String[]{String.valueOf(receita.getId())});
    }

    public void excluirReceita(final int id) {
        db.delete(
                BancoDeDados_Receitas.TABELA_RECEITAS,
                BancoDeDados_Receitas.RECEITAS_ID + "=?",
                new String[]{String.valueOf(id)});
    }

    private Receita mapearContato(final Cursor cursor) {
        final Receita receita = new Receita();
        receita.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BancoDeDados_Receitas.RECEITAS_ID)));
        receita.setNome(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados_Receitas.RECEITAS_NOME)));
        receita.setCategoria(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados_Receitas.RECEITAS_CATEGORIA)));
        receita.setModoPreparo(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados_Receitas.RECEITAS_MODO_PREPARO)));
        receita.setTempoPreparo(cursor.getInt(cursor.getColumnIndexOrThrow(BancoDeDados_Receitas.RECEITAS_TEMPO_PREPARO)));
        receita.setPorcoes(cursor.getInt(cursor.getColumnIndexOrThrow(BancoDeDados_Receitas.RECEITAS_PORCOES)));
        receita.setFotoPath(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados_Receitas.RECEITAS_FOTOPATH)));
        receita.setDificuldade(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados_Receitas.RECEITAS_DIFICULDADE)));

        return receita;
    }
}
