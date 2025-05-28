package com.oficina.backend.repository;

import com.oficina.backend.model.ServicoFinalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServicoFinalizadoRepository extends JpaRepository<ServicoFinalizado, Long> {

    @Query("SELECT s FROM ServicoFinalizado s JOIN FETCH s.cliente ORDER BY s.dataFinalizacao DESC")
    List<ServicoFinalizado> findTop5WithCliente();

    @Query("SELECT s FROM ServicoFinalizado s " +
            "WHERE (:termo IS NULL OR LOWER(s.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) OR s.cpfCliente LIKE CONCAT('%', :termo, '%')) " +
            "AND (:dataInicio IS NULL OR s.dataInicio >= :dataInicio) " +
            "AND (:dataFim IS NULL OR s.dataFinalizacao <= :dataFim)")
    List<ServicoFinalizado> buscarComFiltros(@Param("termo") String termo,
                                             @Param("dataInicio") LocalDateTime dataInicio,
                                             @Param("dataFim") LocalDateTime dataFim);

    List<ServicoFinalizado> findTop5ByOrderByDataFinalizacaoDesc();

    @Query("SELECT strftime('%Y-%m', s.dataFinalizacao) as mes, COUNT(s) " +
            "FROM ServicoFinalizado s " +
            "GROUP BY mes " +
            "ORDER BY mes")
    List<Object[]> contarServicosPorMes();

    @Query("SELECT strftime('%Y-%m', s.dataFinalizacao), COUNT(s) " +
            "FROM ServicoFinalizado s " +
            "WHERE s.dataFinalizacao >= :limite " +
            "GROUP BY strftime('%Y-%m', s.dataFinalizacao) " +
            "ORDER BY strftime('%Y-%m', s.dataFinalizacao)")
    List<Object[]> contarServicosPorMesUltimos4Meses(@Param("limite") LocalDateTime limite);

}
