package com.oficina.backend.DTO;
import lombok.Data;
import com.oficina.backend.model.Cliente;

@Data
public class ClienteResumidoDTO {
    private Long id;
    private String nome;
    private String cpf;

    public ClienteResumidoDTO() {
    }

    public ClienteResumidoDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }
}
