package com.oficina.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicos_finalizados")
public class ServicoFinalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private String detalhesFinalizacao;

    private BigDecimal preco;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinalizacao;

    private String nomeCliente;

    private String cpfCliente;

    private String observacoes;
    private LocalDate dataGarantia;
    private String clausulaGarantia;


    @ManyToOne
    private Cliente cliente;
    @ManyToMany
    @JoinTable(
            name = "servico_finalizado_unidade",
            joinColumns = @JoinColumn(name = "servico_finalizado_id"),
            inverseJoinColumns = @JoinColumn(name = "unidade_id")
    )
    private List<UnidadeProduto> unidadesUsadas;

    // Caso queira manter referência ao serviço original
    @ManyToOne
    private Servico servicoOriginal;
    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;





}
