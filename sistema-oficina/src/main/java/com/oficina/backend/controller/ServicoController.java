package com.oficina.backend.controller;

import com.oficina.backend.model.Servico;
import com.oficina.backend.service.ServicoService;
import com.oficina.backend.model.Cliente;
import com.oficina.backend.DTO.ClienteResumidoDTO;
import com.oficina.backend.repository.ClienteRepository;
import com.oficina.backend.repository.ServicoRepository;
import com.oficina.backend.DTO.ServicoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<ServicoDTO> criarServico(@RequestBody ServicoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getCliente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));

        Servico servico = new Servico();
        servico.setDescricao(dto.getDescricao());
        servico.setPreco(dto.getPreco());
        servico.setData(dto.getData().atStartOfDay());
        servico.setCliente(cliente); // associando cliente completo

        servico = servicoRepository.save(servico);

        // montar DTO de resposta com cliente resumido
        ClienteResumidoDTO clienteResumido = new ClienteResumidoDTO(cliente);
        ServicoDTO resposta = new ServicoDTO(servico);

        return ResponseEntity.ok(resposta);
    }



    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> buscarPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.buscarPorId(id);
        return servico
                .map(s -> ResponseEntity.ok(new ServicoDTO(s)))
                .orElse(ResponseEntity.notFound().build());
    }


    //Listar todos os servicos
    @GetMapping
    public ResponseEntity<List<ServicoDTO>> listarTodos() {
        List<Servico> servicos = servicoService.listarServicos();
        List<ServicoDTO> dtos = servicos.stream()
                .map(ServicoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/buscar-tudo")
    public ResponseEntity<List<Servico>> buscarPorDescricaoOuCpf(@RequestParam String termo) {
        return ResponseEntity.ok(servicoService.buscarPorDescricaoOuCpf(termo));
    }


    //buscar por intervalo de dadas
    @GetMapping("/data")
    public ResponseEntity<List<Servico>> buscarPorData(@RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        return ResponseEntity.ok(servicoService.buscarPorPeriodo(inicio, fim));
    }

    //atualizar servico
    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody @Valid Servico servicoAtualizado) {
        try {
            Servico atualizado = servicoService.atualizar(id, servicoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //deletar servico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    //buscar por cpf
    @GetMapping("/cpf")
    public ResponseEntity<List<Servico>> buscarPorCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(servicoService.buscarPorCpfCliente(cpf));
    }

    @GetMapping("/historico")
    public ResponseEntity<List<ServicoDTO>> getHistoricoUltimosServicos() {
        List<Servico> ultimosServicos = servicoService.getUltimosServicos(10); // Limita a 10 serviços
        List<ServicoDTO> servicoDTOs = ultimosServicos.stream()
                .map(ServicoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(servicoDTOs);
    }
}