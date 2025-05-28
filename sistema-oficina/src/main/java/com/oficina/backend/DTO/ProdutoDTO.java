package com.oficina.backend.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {
    private Long id;

    @NotBlank(message = "O nome do produto é obrigatório")
    private String nome;

    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Integer quantidade;

    @NotNull(message = "O preço unitário é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser maior que zero")
    private BigDecimal precoUnitario;

    @NotBlank(message = "A categoria é obrigatória")
    private String categoria;

    private String observacao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacoes) {
        this.observacao = observacoes;
    }
}
