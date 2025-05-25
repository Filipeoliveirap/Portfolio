package com.oficina.backend.repository;

import com.oficina.backend.model.Servico;
import com.oficina.backend.model.ServicoFinalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ServicoFinalizadoRepository extends JpaRepository<ServicoFinalizado, Long> {

    @Query("SELECT s FROM ServicoFinalizado s " +
            "WHERE (:termo IS NULL OR LOWER(s.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) OR s.cpfCliente LIKE CONCAT('%', :termo, '%')) " +
            "AND (:dataInicio IS NULL OR s.dataInicio >= :dataInicio) " +
            "AND (:dataFim IS NULL OR s.dataFinalizacao <= :dataFim)")
    List<ServicoFinalizado> buscarComFiltros(@Param("termo") String termo,
                                             @Param("dataInicio") LocalDate dataInicio,
                                             @Param("dataFim") LocalDate dataFim);
}
