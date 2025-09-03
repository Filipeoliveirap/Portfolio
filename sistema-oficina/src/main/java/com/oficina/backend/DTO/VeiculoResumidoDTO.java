package com.oficina.backend.DTO;

import lombok.Data;
import com.oficina.backend.model.Veiculo;
@Data
public class VeiculoResumidoDTO {
    private Long id;
    private String modelo;
    private String placa;

    public VeiculoResumidoDTO(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.modelo = veiculo.getModelo();
        this.placa = veiculo.getPlaca();
    }
}

