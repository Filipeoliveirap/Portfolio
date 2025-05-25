package com.oficina.backend.service;


import com.oficina.backend.model.Produto;
import com.oficina.backend.model.Servico;
import com.oficina.backend.repository.ClienteRepository;
import com.oficina.backend.repository.ProdutoRepository;
import com.oficina.backend.repository.ServicoRepository;
import com.oficina.backend.DTO.ServicoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oficina.backend.model.Cliente;


import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ServicoService {
    @Autowired
    private ServicoRepository servicoRepository;


    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    //salvar servico com controle de estoque
    public Servico salvar(Servico servico) {
        // Garante que o cliente está salvo antes de salvar o serviço
        Cliente cliente = servico.getCliente();
        if (cliente.getId() == null) {
            cliente = clienteRepository.save(cliente); // salve e atualize a referência
            servico.setCliente(cliente); // atualiza o cliente dentro do serviço
        }

        // Verifica estoque de todos os produtos
        servico.getProdutos().forEach(produtoUsado -> {
            Produto produto = produtoRepository.findById(produtoUsado.getId())
                    .orElseThrow(() -> new RuntimeException("Produto com id: " + produtoUsado.getId() + " não encontrado"));
            if (produto.getQuantidade() < produtoUsado.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para produto: " + produto.getNome());
            }
        });

        // Diminui o estoque
        servico.getProdutos().forEach(produtoUsado -> {
            Produto produto = produtoRepository.findById(produtoUsado.getId()).get();
            produto.setQuantidade(produto.getQuantidade() - produtoUsado.getQuantidade());
            produtoRepository.save(produto);
        });

        return servicoRepository.save(servico);
    }


    //buscar todos os servicos
    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    //buscar por id
    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    //deletar servico
    public void deletar(Long id) {
        servicoRepository.deleteById(id);
    }

    //buscar por nome do cliente
    public List<Servico> buscarPorNomeDoCliente(String nome) {
        return servicoRepository.findByCliente_NomeContainingIgnoreCase(nome);
    }

    //buscar por intervalo de datas
    public List<Servico> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return servicoRepository.findByDataBetween(inicio, fim);
    }

    //atualizar servico
    public Servico atualizar(Long id, Servico servicoAtualizado) {
        return servicoRepository.findById(id).map(servicoExistente -> {

            // Repor o estoque dos produtos antigos
            servicoExistente.getProdutos().forEach(produtoUsado -> {
                Produto produto = produtoRepository.findById(produtoUsado.getId()).orElseThrow();
                produto.setQuantidade(produto.getQuantidade() + produtoUsado.getQuantidade());
                produtoRepository.save(produto);
            });

            // Verificar se os novos produtos têm estoque suficiente
            servicoAtualizado.getProdutos().forEach(produtoNovo -> {
                Produto produto = produtoRepository.findById(produtoNovo.getId()).orElseThrow(() ->
                        new RuntimeException("Produto com id " + produtoNovo.getId() + " não encontrado"));

                if (produto.getQuantidade() < produtoNovo.getQuantidade()) {
                    throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
                }
            });

            // Atualizar o estoque com os novos produtos
            servicoAtualizado.getProdutos().forEach(produtoNovo -> {
                Produto produto = produtoRepository.findById(produtoNovo.getId()).get();
                produto.setQuantidade(produto.getQuantidade() - produtoNovo.getQuantidade());
                produtoRepository.save(produto);
            });

            // Atualizar dados do serviço
            servicoExistente.setData(servicoAtualizado.getData());
            servicoExistente.setDescricao(servicoAtualizado.getDescricao());
            servicoExistente.setPreco(servicoAtualizado.getPreco());
            servicoExistente.setCliente(servicoAtualizado.getCliente());
            servicoExistente.setProdutos(servicoAtualizado.getProdutos());

            return servicoRepository.save(servicoExistente);

        }).orElseThrow(() -> new RuntimeException("Serviço com id " + id + " não encontrado"));
    }

    //buscar pela descricao do servico
    public List<Servico> buscarPorDescricao(String descricao) {
        return servicoRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    //buscar por cpf
    public List<Servico> buscarPorCpfCliente(String cpf) {
        return servicoRepository.findByClienteCpfContaining(cpf);
    }

    public List<Servico> buscarPorPeriodoECliente(LocalDate inicio, LocalDate fim, String nomeCliente) {
        return servicoRepository.findByDataBetween(inicio, fim);


    }

    public List<Servico> getUltimosServicos(int limite) {
        return servicoRepository.findUltimosServicos().stream()
                .limit(limite)
                .collect(Collectors.toList());
    }
    public Servico salvarcomDTO(ServicoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + dto.getClienteId()));

        Servico servico = new Servico();
        servico.setDescricao(dto.getDescricao());
        servico.setPreco(dto.getPreco());
        servico.setData(dto.getData());
        servico.setCliente(cliente);

        return servicoRepository.save(servico);
    }

    public List<Servico> buscarPorDescricaoOuCpf(String termo) {
        return servicoRepository.findByDescricaoContainingIgnoreCaseOrClienteCpfContaining(termo, termo);
    }







}
