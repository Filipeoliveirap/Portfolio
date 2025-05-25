package com.mycompany.oficinamecanica.controller;

import com.mycompany.oficinamecanica.dao.ClienteDAO;
import com.mycompany.oficinamecanica.dao.VeiculoDAO;
import com.mycompany.oficinamecanica.model.Veiculo;
import java.sql.SQLException;
import java.util.List;

public class VeiculoController {

    private VeiculoDAO veiculoDAO;
    private ClienteDAO clienteDAO;

    public VeiculoController() {
        this.veiculoDAO = new VeiculoDAO();
        this.clienteDAO = new ClienteDAO();
    }

    // Método para adicionar um veículo
    public void adicionarVeiculo(Veiculo veiculo) {
        try {
            validarVeiculo(veiculo);
            if (veiculo.getClienteId() <= 0) {
                throw new IllegalArgumentException("ID do cliente inválido.");
            }
            if (clienteDAO.buscarClientePeloId(veiculo.getClienteId()) == null) {
                throw new IllegalArgumentException("Cliente não encontrado.");
            }
            veiculoDAO.adicionarVeiculo(veiculo);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar veículo no banco de dados", e);
        }
    }

    // Método para listar todos os veículos
    public List<Veiculo> listarVeiculos() {
        try {
            return veiculoDAO.listarVeiculos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos", e);
        }
    }

    // Método para atualizar um veículo
    public void atualizarVeiculo(Veiculo veiculo) {
        try {
            validarVeiculo(veiculo);
            if (veiculo.getId() <= 0) {
                throw new IllegalArgumentException("ID do veículo inválido.");
            }
            if (clienteDAO.buscarClientePeloId(veiculo.getClienteId()) == null) {
                throw new IllegalArgumentException("Cliente não encontrado.");
            }
            veiculoDAO.atualizarVeiculo(veiculo);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo", e);
        }
    }

    // Método para excluir um veículo
    public void excluirVeiculo(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID do veículo inválido.");
            }
            if (veiculoDAO.veiculoTemServicos(id)) { 
                throw new IllegalArgumentException("Não é possível excluir veículo com serviços cadastrados.");
            }
            veiculoDAO.excluirVeiculo(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir veículo", e);
        }
    }

    // Método para buscar veículo por ID
    public Veiculo buscarVeiculoPorId(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID do veículo inválido.");
            }
            return veiculoDAO.buscarVeiculoPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículo", e);
        }
    }

    // Método para listar veículos de um cliente específico
    public List<Veiculo> listarVeiculosPorCliente(int clienteId) {
        try {
            if (clienteId <= 0) {
                throw new IllegalArgumentException("ID do cliente inválido.");
            }
            return veiculoDAO.listarVeiculosPorCliente(clienteId);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos do cliente", e);
        }
    }

    // Validações do veículo
    private void validarVeiculo(Veiculo veiculo) {
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo não pode ser vazio.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa do veículo não pode ser vazia.");
        }
        if (veiculo.getAno() <= 0) {
            throw new IllegalArgumentException("Ano do veículo deve ser maior que zero.");
        }
    }
}