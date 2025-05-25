package com.mycompany.oficinamecanica.controller;

import com.mycompany.oficinamecanica.dao.ServicoDAO;
import com.mycompany.oficinamecanica.dao.VeiculoDAO; // Para validar veículo
import com.mycompany.oficinamecanica.model.Servico;
import java.sql.SQLException;
import java.util.List;

public class ServicoController {

    private ServicoDAO servicoDAO;
    private VeiculoDAO veiculoDAO; // Para validar veículo

    public ServicoController() {
        this.servicoDAO = new ServicoDAO();
        this.veiculoDAO = new VeiculoDAO();
    }

    // Método para adicionar um serviço
    public void adicionarServico(Servico servico) {
        try {
            validarServico(servico);
            if (servico.getVeiculoId() <= 0) {
                throw new IllegalArgumentException("ID do veículo inválido.");
            }
            if (veiculoDAO.buscarVeiculoPorId(servico.getVeiculoId()) == null) {
                throw new IllegalArgumentException("Veículo não encontrado.");
            }
            servicoDAO.adicionarServico(servico);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar serviço", e);
        }
    }

    // Método para listar todos os serviços
    public List<Servico> listarServicos() {
        try {
            return servicoDAO.listarServicos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar serviços", e);
        }
    }

    // Método para atualizar um serviço
    public void atualizarServico(Servico servico) {
        try {
            validarServico(servico);
            if (servico.getId() <= 0) {
                throw new IllegalArgumentException("ID do serviço inválido.");
            }
            if (veiculoDAO.buscarVeiculoPorId(servico.getVeiculoId()) == null) {
                throw new IllegalArgumentException("Veículo não encontrado.");
            }
            servicoDAO.atualizarServico(servico);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar serviço", e);
        }
    }

    // Método para excluir um serviço
    public void excluirServico(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID do serviço inválido.");
            }
            servicoDAO.excluirServico(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir serviço", e);
        }
    }

    // Método para buscar serviço por ID
    public Servico buscarServicoPorId(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID do serviço inválido.");
            }
            return servicoDAO.buscarServicoPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar serviço", e);
        }
    }

    // Método para listar serviços de um veículo específico
    public List<Servico> listarServicosPorVeiculo(int veiculoId) {
        try {
            if (veiculoId <= 0) {
                throw new IllegalArgumentException("ID do veículo inválido.");
            }
            return servicoDAO.listarServicosPorVeiculo(veiculoId);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar serviços do veículo", e);
        }
    }

    // Validações do serviço
    private void validarServico(Servico servico) {
        if (servico.getDescricao() == null || servico.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("Descrição do serviço não pode ser vazia.");
        }
        if (servico.getPreco() <= 0) {
            throw new IllegalArgumentException("Preço do serviço deve ser maior que zero.");
        }
        if (servico.getData() == null) {
            throw new IllegalArgumentException("Data do serviço não pode ser nula.");
        }
    }
}
