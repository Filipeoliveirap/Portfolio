package com.oficina.backend.repository;

import com.oficina.backend.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByCpfClienteContaining(String cpf);

    List<Veiculo> findByModeloContainingIgnoreCase(String modelo);

    List<Veiculo> findByPlacaContainingIgnoreCase(String placa);

    List<Veiculo> findByClienteNomeContainingIgnoreCase(String nome);
}
