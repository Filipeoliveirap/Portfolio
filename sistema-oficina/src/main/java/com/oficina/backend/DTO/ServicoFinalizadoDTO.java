package com.oficina.backend.DTO;

import com.oficina.backend.model.ServicoFinalizado;
import com.oficina.backend.model.UnidadeProduto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoFinalizadoDTO {
    private Long id;
    private String descricao;
    private BigDecimal preco;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFinalizacao;
    private String detalhesFinalizacao;
    private LocalDate dataGarantia;
    private String clausulaGarantia;
    private ClienteResumidoDTO cliente;
    private VeiculoResumidoDTO veiculo;
    private List<Long> unidadesUsadasIds;

    // Construtor a partir do model
    public ServicoFinalizadoDTO(ServicoFinalizado sf) {
        this.id = sf.getId();
        this.descricao = sf.getDescricao();
        this.preco = sf.getPreco();
        this.dataInicio = sf.getDataInicio();
        this.dataFinalizacao = sf.getDataFinalizacao();
        this.detalhesFinalizacao = sf.getDetalhesFinalizacao();
        this.dataGarantia = sf.getDataGarantia();
        this.clausulaGarantia = sf.getClausulaGarantia();
        this.cliente = new ClienteResumidoDTO(sf.getCliente());
        if (sf.getVeiculo() != null) {
            this.veiculo = new VeiculoResumidoDTO(sf.getVeiculo());
        }
        if (sf.getUnidadesUsadas() != null) {
            this.unidadesUsadasIds = sf.getUnidadesUsadas()
                    .stream()
                    .map(UnidadeProduto::getId)
                    .toList();
        }
    }
}

