package com.oficina.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "O cliente é obrigatório")
    private Cliente cliente;

    @Column(nullable = false)
    private String cpfCliente;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "A placa é obrigatória")
    private String placa;

    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @NotBlank(message = "A cor é obrigatória")
    private String cor;

}
