package com.oficina.backend.repository;

import com.oficina.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Buscar produto pelo nome exato (case sensitive)
    Optional<Produto> findByNome(String nome);

    // Buscar produtos com quantidade menor que o valor fornecido
    List<Produto> findByQuantidadeLessThan(int quantidade);

    // Buscar produtos cujo nome contenha a string (case insensitive), paginado
    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    // Verificar se produto com determinado nome existe
    boolean existsByNome(String nome);

    // Buscar produtos cuja categoria contenha a string (case insensitive), paginado
    Page<Produto> findByCategoriaContainingIgnoreCase(String categoria, Pageable pageable);

    List<Produto> findByQuantidadeLessThanEqual(int quantidade);
}
