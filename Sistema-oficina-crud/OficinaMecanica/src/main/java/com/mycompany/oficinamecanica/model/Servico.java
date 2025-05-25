package com.mycompany.oficinamecanica.model;

import java.time.LocalDate;

public class Servico {
    private int id;
    private String descricao;
    private double preco;
    private LocalDate data;
    private int veiculoId;
    
    //Construtor padrão
    public Servico() {}
    
    //Construtor com parâmetros
    public Servico(int id, String descricao, double preco, LocalDate data, int veiculoId) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.data = data;
        this.veiculoId = veiculoId;
    }
    
    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(int veiculoId) {
        this.veiculoId = veiculoId;
    }
    
    
}
