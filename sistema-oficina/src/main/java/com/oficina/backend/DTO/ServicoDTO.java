package com.oficina.backend.DTO;

import lombok.Data;
import com.oficina.backend.model.Servico;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ServicoDTO {
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private LocalDate data;
    private ClienteResumidoDTO cliente;

    public ServicoDTO() {
    }

    public ServicoDTO(Servico servico) {
        this.id = servico.getId();
        this.descricao = servico.getDescricao();
        this.preco = servico.getPreco();
        this.data = servico.getData();
        this.cliente = new ClienteResumidoDTO(servico.getCliente());
    }
}
