package com.oficina.backend.repository;
import com.oficina.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    //buscar produto por nome
    Optional<Produto> findByNome(String nome);

    //buscar produtos com quantidades menor que um valor
    List<Produto> findByQuantidadeLessThan(int quantidade);
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}
