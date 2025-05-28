package com.oficina.backend.service;

import com.oficina.backend.DTO.ProdutoDTO;
import com.oficina.backend.model.Produto;
import com.oficina.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Converte entity para DTO
    private ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setQuantidade(produto.getQuantidade());
        dto.setPrecoUnitario(produto.getPrecoUnitario());
        dto.setCategoria(produto.getCategoria());
        dto.setObservacao(produto.getObservacao());
        return dto;
    }

    // Converte DTO para entity
    private Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setQuantidade(dto.getQuantidade());
        produto.setPrecoUnitario(dto.getPrecoUnitario());
        produto.setCategoria(dto.getCategoria());
        produto.setObservacao(dto.getObservacao());
        return produto;
    }

    // Salva um novo produto
    public ProdutoDTO salvar(ProdutoDTO dto) {
        Produto produto = toEntity(dto);
        produto = produtoRepository.save(produto);
        return toDTO(produto);
    }

    // Atualiza produto existente pelo id
    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));
        produtoExistente.setNome(dto.getNome());
        produtoExistente.setQuantidade(dto.getQuantidade());
        produtoExistente.setPrecoUnitario(dto.getPrecoUnitario());
        produtoExistente.setCategoria(dto.getCategoria());
        produtoExistente.setObservacao(dto.getObservacao());
        produtoExistente = produtoRepository.save(produtoExistente);
        return toDTO(produtoExistente);
    }

    // Exclui produto pelo id
    public void excluir(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    // Busca produto por id
    public ProdutoDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com id: " + id));
        return toDTO(produto);
    }

    // Lista todos produtos com paginação e ordenação
    public Page<ProdutoDTO> listarTodos(int page, int size, String sortBy, String filtro, String tipoFiltro) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        if (filtro == null || filtro.isEmpty()) {
            // Sem filtro, retorna tudo paginado
            Page<Produto> produtos = produtoRepository.findAll(pageable);
            return produtos.map(this::toDTO);
        }

        // Filtra por categoria
        if ("categoria".equalsIgnoreCase(tipoFiltro)) {
            Page<Produto> produtos = produtoRepository.findByCategoriaContainingIgnoreCase(filtro, pageable);
            return produtos.map(this::toDTO);
        }

        // Filtra por nome
        if ("nome".equalsIgnoreCase(tipoFiltro)) {
            Page<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(filtro, pageable);
            return produtos.map(this::toDTO);
        }

        // Caso tipoFiltro não seja reconhecido, retorna tudo paginado
        Page<Produto> produtos = produtoRepository.findAll(pageable);
        return produtos.map(this::toDTO);
    }

    // Busca produtos pelo nome (contendo, case insensitive) com paginação
    public Page<ProdutoDTO> buscarPorNome(String nome, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Produto> produtos = produtoRepository.findByNomeContainingIgnoreCase(nome, pageable);
        return produtos.map(this::toDTO);
    }

    // Busca produtos pela categoria (contendo, case insensitive) com paginação
    public Page<ProdutoDTO> buscarPorCategoria(String categoria, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Produto> produtos = produtoRepository.findByCategoriaContainingIgnoreCase(categoria, pageable);
        return produtos.map(this::toDTO);
    }

    // Produtos com estoque abaixo do limite definido (sem paginação)
    public List<ProdutoDTO> produtosComEstoqueBaixo(int quantidadeLimite) {
        List<Produto> produtos = produtoRepository.findByQuantidadeLessThan(quantidadeLimite);
        return produtos.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Produto> buscarPorEstoqueBaixo(int quantidadeLimite) {
        return produtoRepository.findByQuantidadeLessThanEqual(quantidadeLimite);
    }

}
