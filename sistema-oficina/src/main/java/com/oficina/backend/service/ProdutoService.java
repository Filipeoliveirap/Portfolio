package com.oficina.backend.service;

import com.oficina.backend.model.Produto;
import com.oficina.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    //salvar produto
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    //Listar produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    //buscar por id
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    //deletar
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    //buscar por nome
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    //atualizar produto
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            produto.setPrecoUnitario(produtoAtualizado.getPrecoUnitario());
            return produtoRepository.save(produto);
        }).orElseThrow(() -> new RuntimeException("Produto com id: " + id + " não encontrado"));
    }

    //buscar estoque baixo
    public List<Produto> buscarPorEstoqueBaixo(int quantidadeMinima) {
        return produtoRepository.findByQuantidadeLessThan(quantidadeMinima);
    }

}
