package com.oficina.backend.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoResumoDTO {
    private String nomeCliente;
    private String descricao;
    private String data;
    private String dataInicio;
    private String telefone;
    private BigDecimal valor;
    private String observacoes;
}
