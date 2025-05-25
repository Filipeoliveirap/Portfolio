package com.oficina.backend.controller;

import com.oficina.backend.model.Produto;
import com.oficina.backend.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    //criar produto
    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody @Valid Produto produto) {
        Produto salvo = produtoService.salvar(produto);
        return ResponseEntity.ok(salvo);
    }

    //listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.buscarPorId(id);
        return produto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //buscar por nome
    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(produtoService.buscarPorNome(nome));
    }

    //atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody @Valid Produto produtoAtualizado) {
        try {
            Produto atualizado = produtoService.atualizar(id, produtoAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    //buscar por estoque baixo
    @GetMapping("/estoque-baixo")
    public ResponseEntity<?> buscarPorEstoqueBaixo(@RequestParam int quantidadeMinima) {
        List<Produto> produtos = produtoService.buscarPorEstoqueBaixo(quantidadeMinima);

        if(produtos.isEmpty()) {
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("mensagem", "Nenhum produto com estoque baixo de " + quantidadeMinima);
            resposta.put("status", 404);
            return ResponseEntity.status(404).body(resposta);
        }
        return ResponseEntity.ok(produtoService.buscarPorEstoqueBaixo(quantidadeMinima));
    }
}
