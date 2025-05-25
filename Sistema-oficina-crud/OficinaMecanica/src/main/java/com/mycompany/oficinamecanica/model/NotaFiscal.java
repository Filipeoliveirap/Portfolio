
package com.mycompany.oficinamecanica.model;

import java.time.LocalDate;

public class NotaFiscal {
    private int id;
    private String numero;
    private LocalDate dataEmissao;
    private double valorTotal;
    private int servicoId;
    
    //Construtor padrão
    public NotaFiscal(){}
    
    //Construtor com parâmetros
    public NotaFiscal(String numero, LocalDate dataEmissao, double valorTotal, int servicoId) {
        this.numero = numero;
        this.dataEmissao = dataEmissao;
        this.valorTotal = valorTotal;
        this.servicoId = servicoId;
    }
   
    //Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getServicoId() {
        return servicoId;
    }

    public void setServicoId(int servicoId) {
        this.servicoId = servicoId;
    }
}
