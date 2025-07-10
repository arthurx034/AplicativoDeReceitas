package com.arthur.aplicativodereceitas.model;

import com.arthur.aplicativodereceitas.model.Receita;

import java.util.ArrayList;
import java.util.List;

public class Lista {
    private static Lista instancia;
    private final ArrayList<Receita> receitas;

    private Lista() {
        receitas = new ArrayList<>();
    }

    public static Lista getInstance() {
        if (instancia == null) {
            instancia = new Lista();
        }
        return instancia;
    }

    public List<Receita> getReceitas() {
        return receitas;
    }

    public void limpar() {
        receitas.clear();
    }
}
