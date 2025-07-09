package com.arthur.aplicativodereceitas.model;

public class Ingrediente {
    private int id;
    private int receitaId;
    private String nome;
    private String quantidade; // Ex: "2 xícaras"
    private String unidade;    // Ex: "g", "ml"

    public Ingrediente() {
    }

    public Ingrediente(int id, int receitaId, String nome, String quantidade, String unidade) {
        this.id = id;
        this.receitaId = receitaId;
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidade = unidade;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(int receitaId) {
        this.receitaId = receitaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
