package com.oficina.backend.service;

import com.oficina.backend.DTO.ServicoRequestDTO;
import com.oficina.backend.model.Cliente;
import com.oficina.backend.model.Produto;
import com.oficina.backend.model.Servico;
import com.oficina.backend.model.Veiculo;
import com.oficina.backend.repository.ClienteRepository;
import com.oficina.backend.repository.ProdutoRepository;
import com.oficina.backend.repository.ServicoRepository;
import com.oficina.backend.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    // Salvar serviço com produtos e cliente
    public Servico salvar(Servico servico) {
        // Garantir cliente salvo
        Cliente cliente = servico.getCliente();
        if (cliente.getId() == null) {
            cliente = clienteRepository.save(cliente);
            servico.setCliente(cliente);
        }

        // Validar estoque
        servico.getProdutos().forEach(produtoUsado -> {
            Produto produto = produtoRepository.findById(produtoUsado.getId())
                    .orElseThrow(() -> new RuntimeException("Produto com id: " + produtoUsado.getId() + " não encontrado"));
            if (produto.getQuantidade() < produtoUsado.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para produto: " + produto.getNome());
            }
        });

        // Atualizar estoque
        servico.getProdutos().forEach(produtoUsado -> {
            Produto produto = produtoRepository.findById(produtoUsado.getId()).get();
            produto.setQuantidade(produto.getQuantidade() - produtoUsado.getQuantidade());
            produtoRepository.save(produto);
        });

        return servicoRepository.save(servico);
    }

    // Listar todos os serviços
    public List<Servico> listarServicos() {
        return servicoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    // Deletar serviço
    public void deletar(Long id) {
        servicoRepository.deleteById(id);
    }

    // Buscar por nome do cliente
    public List<Servico> buscarPorNomeDoCliente(String nome) {
        return servicoRepository.findByCliente_NomeContainingIgnoreCase(nome);
    }

    // Buscar por período
    public List<Servico> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return servicoRepository.findByDataBetween(inicio, fim);
    }

    // Atualizar serviço
    public Servico atualizar(Long id, Servico servicoAtualizado) {
        return servicoRepository.findById(id).map(servicoExistente -> {

            // Repor estoque dos produtos antigos
            servicoExistente.getProdutos().forEach(produtoUsado -> {
                Produto produto = produtoRepository.findById(produtoUsado.getId()).orElseThrow();
                produto.setQuantidade(produto.getQuantidade() + produtoUsado.getQuantidade());
                produtoRepository.save(produto);
            });

            // Validar novos produtos
            servicoAtualizado.getProdutos().forEach(produtoNovo -> {
                Produto produto = produtoRepository.findById(produtoNovo.getId())
                        .orElseThrow(() -> new RuntimeException("Produto com id " + produtoNovo.getId() + " não encontrado"));
                if (produto.getQuantidade() < produtoNovo.getQuantidade()) {
                    throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
                }
            });

            // Atualizar estoque com novos produtos
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

            // ✅ Atualizar veículo
            if (servicoAtualizado.getVeiculo() != null && servicoAtualizado.getVeiculo().getId() != null) {
                Veiculo veiculo = veiculoRepository.findById(servicoAtualizado.getVeiculo().getId())
                        .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));
                servicoExistente.setVeiculo(veiculo);
            } else {
                servicoExistente.setVeiculo(null);
            }

            return servicoRepository.save(servicoExistente);

        }).orElseThrow(() -> new RuntimeException("Serviço com id " + id + " não encontrado"));
    }

    // Salvar via DTO
    public Servico salvarcomDTO(ServicoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + dto.getClienteId()));

        Servico servico = new Servico();
        servico.setDescricao(dto.getDescricao());
        servico.setPreco(dto.getPreco());
        servico.setData(dto.getData().atStartOfDay());
        servico.setCliente(cliente);

        // Associar veículo
        if (dto.getVeiculoId() != null) {
            Veiculo veiculo = veiculoRepository.findById(dto.getVeiculoId())
                    .orElseThrow(() -> new RuntimeException("Veículo não encontrado com id: " + dto.getVeiculoId()));
            servico.setVeiculo(veiculo);
        }

        return servicoRepository.save(servico);
    }

    // Buscar por descrição ou CPF do cliente
    public List<Servico> buscarPorDescricaoOuCpf(String termo) {
        return servicoRepository.findByDescricaoContainingIgnoreCaseOrClienteCpfContaining(termo, termo);
    }

    // Buscar últimos serviços
    public List<Servico> getUltimosServicos(int limite) {
        return servicoRepository.findUltimosServicos().stream()
                .limit(limite)
                .collect(Collectors.toList());
    }
}
