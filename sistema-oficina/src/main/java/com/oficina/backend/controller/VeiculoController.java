package com.oficina.backend.controller;

import com.oficina.backend.model.Veiculo;
import com.oficina.backend.model.Cliente;
import com.oficina.backend.repository.ClienteRepository;
import com.oficina.backend.repository.VeiculoRepository;
import com.oficina.backend.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody @Valid Veiculo veiculo) {
        Cliente cliente = clienteRepository.findById(veiculo.getCliente().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));

        veiculo.setCliente(cliente);
        veiculo.setCpfCliente(cliente.getCpf());

        Veiculo salvo = veiculoRepository.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> listarVeiculos() {
        List<Veiculo> veiculos = veiculoService.listarTodos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> buscarPorId(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.buscarPorId(id);
        return veiculo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody @Valid Veiculo veiculoAtualizado) {
        try {
            Veiculo atualizado = veiculoService.atualizar(id, veiculoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            veiculoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/buscar/cpf")
    public ResponseEntity<List<Veiculo>> buscarPorCpf(@RequestParam String cpf) {
        List<Veiculo> veiculos = veiculoService.buscarPorCpfCliente(cpf);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/buscar/modelo")
    public ResponseEntity<List<Veiculo>> buscarPorModelo(@RequestParam String modelo) {
        List<Veiculo> veiculos = veiculoService.buscarPorModelo(modelo);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/buscar/placa")
    public ResponseEntity<List<Veiculo>> buscarPorPlaca(@RequestParam String placa) {
        List<Veiculo> veiculos = veiculoService.buscarPorPlaca(placa);
        return ResponseEntity.ok(veiculos);
    }


    @GetMapping("/buscar/cliente")
    public ResponseEntity<List<Veiculo>> buscarPorNomeCliente(@RequestParam String nome) {
        List<Veiculo> veiculos = veiculoService.buscarPorNomeCliente(nome);
        return ResponseEntity.ok(veiculos);
    }
}
