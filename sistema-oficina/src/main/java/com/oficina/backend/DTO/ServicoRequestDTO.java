package com.oficina.backend.DTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ServicoRequestDTO {
    @NotNull
    private String descricao;
    @NotNull
    private BigDecimal preco;
    @NotNull
    private LocalDate data;
    @NotNull
    private Long clienteId;
}
