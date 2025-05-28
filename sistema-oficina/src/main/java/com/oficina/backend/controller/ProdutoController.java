package com.oficina.backend.controller;

import com.oficina.backend.DTO.ProdutoDTO;
import com.oficina.backend.service.ProdutoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Criar produto
    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody @Valid ProdutoDTO dto) {
        ProdutoDTO salvo = produtoService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // Listar produtos com paginação e filtro por nome ou categoria
    @GetMapping
    public ResponseEntity<Page<ProdutoDTO>> listarProdutos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(required = false) String filtro,
            @RequestParam(defaultValue = "nome") String tipoFiltro) {

        Page<ProdutoDTO> produtos = produtoService.listarTodos(page, size, sortBy, filtro, tipoFiltro);
        return ResponseEntity.ok(produtos);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Long id) {
        ProdutoDTO dto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    // Deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar produtos com estoque baixo
    @GetMapping("/estoque-baixo")
    public ResponseEntity<?> buscarPorEstoqueBaixo(@RequestParam int quantidadeLimite) {
        List<ProdutoDTO> produtos = produtoService.produtosComEstoqueBaixo(quantidadeLimite);
        if (produtos.isEmpty()) {
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("mensagem", "Nenhum produto com estoque abaixo de " + quantidadeLimite);
            resposta.put("status", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
        }
        return ResponseEntity.ok(produtos);
    }

    // Tratamento local para EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(EntityNotFoundException ex) {
        Map<String, Object> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        erro.put("status", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoDTO dto) {
        ProdutoDTO atualizado = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}
