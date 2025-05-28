package com.oficina.backend.controller;

import com.oficina.backend.model.Produto;
import com.oficina.backend.model.ServicoFinalizado;
import com.oficina.backend.relatorio.RelatorioProdutoPdfGenerator;
import com.oficina.backend.relatorio.RelatorioServicoPdfGenerator;
import com.oficina.backend.service.ProdutoService;
import com.oficina.backend.service.ServicoFinalizadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private ServicoFinalizadoService servicoFinalizadoService;

    @Autowired
    private ProdutoService produtoService;

    // Relatório geral de serviços finalizados com filtros
    @GetMapping("/servicos-finalizados")
    public ResponseEntity<byte[]> gerarRelatorioServicosFinalizadosFiltrado(
            @RequestParam(required = false) String termo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
            @RequestParam(required = false) String periodo
    ) {
        List<ServicoFinalizado> servicosFiltrados =
                servicoFinalizadoService.buscarServicosFinalizados(termo, inicio, fim, periodo);

        if (servicosFiltrados.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        ByteArrayInputStream pdf = RelatorioServicoPdfGenerator.gerarRelatorio(servicosFiltrados);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-servicos-filtrado.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }

    // Relatório individual do serviço finalizado por ID
    @GetMapping("/servico-finalizado/{id}")
    public ResponseEntity<byte[]> gerarRelatorioUnico(@PathVariable Long id) {
        Optional<ServicoFinalizado> servico = servicoFinalizadoService.buscarPorId(id);

        if (servico.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

        ByteArrayInputStream pdf = RelatorioServicoPdfGenerator.gerarRelatorioUnico(servico.get());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-servico-" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }

    // Relatório de produtos com estoque baixo
    @GetMapping("/produtos-estoque-baixo")
    public ResponseEntity<byte[]> gerarRelatorioProdutosEstoqueBaixo(
            @RequestParam(defaultValue = "5") int limite
    ) {
        List<Produto> produtos = produtoService.buscarPorEstoqueBaixo(limite);

        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        ByteArrayInputStream pdf = RelatorioProdutoPdfGenerator.gerarRelatorio(produtos);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-produtos-estoque-baixo.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }
}
