package com.oficina.backend.controller;

import com.oficina.backend.model.ServicoFinalizado;
import com.oficina.backend.repository.ServicoFinalizadoRepository;
import com.oficina.backend.service.ServicoFinalizadoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/servicos-finalizados")
@CrossOrigin(origins = "*")
public class ServicoFinalizadoController {

    @Autowired
    private ServicoFinalizadoRepository servicoFinalizadoRepository;

    @Autowired
    private ServicoFinalizadoService servicoFinalizadoService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!servicoFinalizadoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        servicoFinalizadoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    // Endpoint para finalizar serviço a partir de um ID (mover da tabela Servico)
    @PutMapping("/{id}/finalizar")
    public ResponseEntity<?> finalizarServico(@PathVariable Long id) {
        try {
            boolean finalizado = servicoFinalizadoService.finalizarServico(id);
            if (finalizado) {
                return ResponseEntity.ok().body("Serviço finalizado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Serviço não encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping
    public Page<ServicoFinalizado> listarServicosFinalizados(
            @RequestParam(required = false) String termo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
            @RequestParam(required = false) String periodo,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {

        Pageable pageable = PageRequest.of(pagina, tamanho);

        return servicoFinalizadoService.buscarServicosFinalizados(termo, inicio, fim, periodo, pageable);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ServicoFinalizado> atualizarObservacao(@PathVariable Long id, @RequestBody ServicoFinalizado dadosAtualizados) {
        return servicoFinalizadoRepository.findById(id).map(servico -> {
            servico.setObservacoes(dadosAtualizados.getObservacoes());
            servicoFinalizadoRepository.save(servico);
            return ResponseEntity.ok(servico);
        }).orElse(ResponseEntity.notFound().build());
    }

}