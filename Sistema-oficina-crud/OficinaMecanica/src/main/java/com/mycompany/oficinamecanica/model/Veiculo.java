package com.mycompany.oficinamecanica.model;

public class Veiculo {
    private int id;
    private String modelo;
    private String placa;
    private int ano;
    private int clienteId;
    
    //Construtor padrão
    public Veiculo(){}
    
    //Construtor com parâmetros
    public Veiculo(String modelo, String placa, int ano, int clienteId) {
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.clienteId = clienteId;
    }
    
    //Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
    
    @Override
    public String toString() {
        return modelo + " (" + placa + ")"; // Exibe o modelo e a placa do veículo
    }
    
}
