package com.arthur.aplicativodereceitas.model;

public class Receita {
    private int id;
    private int usuarioId; // se houver login
    private String nome;
    private String categoria;
    private String modoPreparo;
    private int tempoPreparo; // minutos
    private int porcoes;
    private String dificuldade;
    private String caminhoImagem;

    public Receita() {
    }

    public Receita(int id, int usuarioId, String nome, String fotoPath, String categoria, String modoPreparo, int tempoPreparo, int porcoes, String dificuldade, String caminhoImagem) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.categoria = categoria;
        this.modoPreparo = modoPreparo;
        this.tempoPreparo = tempoPreparo;
        this.porcoes = porcoes;
        this.dificuldade = dificuldade;
        this.caminhoImagem = caminhoImagem;
    }


    // Getters e Setters
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public int getPorcoes() {
        return porcoes;
    }

    public void setPorcoes(int porcoes) {
        this.porcoes = porcoes;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
}

