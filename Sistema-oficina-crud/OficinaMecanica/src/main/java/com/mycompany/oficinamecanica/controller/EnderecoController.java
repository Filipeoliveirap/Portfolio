package com.mycompany.oficinamecanica.controller;

import com.mycompany.oficinamecanica.dao.EnderecoDAO;
import com.mycompany.oficinamecanica.model.Endereco;

import java.sql.SQLException;
import java.util.List;

public class EnderecoController {

    private EnderecoDAO enderecoDAO;

    public EnderecoController() {
        this.enderecoDAO = new EnderecoDAO();
    }

    // Método para adicionar um endereço
    public boolean adicionarEndereco(Endereco endereco) {
        try {
            validarEndereco(endereco); // Reutiliza a validação centralizada
            if (endereco.getClienteId() <= 0) {
                throw new IllegalArgumentException("ID do cliente inválido.");
            }

            enderecoDAO.adicionarEndereco(endereco);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
            return false;
        }
    }

    // Método para listar endereços de um cliente
    public List<Endereco> listarEnderecosPorCliente(int clienteId) {
        try {
            if (clienteId <= 0) {
                throw new IllegalArgumentException("ID do cliente inválido.");
            }
            return enderecoDAO.listarEnderecosPorCliente(clienteId);
        } catch (SQLException e) {
            System.out.println("Erro ao listar endereços: " + e.getMessage());
            return List.of(); // Retorna lista vazia em vez de null
        }
    }

    // Método para atualizar um endereço
    public boolean atualizarEndereco(Endereco endereco) {
        try {
            if (endereco.getId() <= 0) {
                throw new IllegalArgumentException("ID do endereço inválido.");
            }
            validarEndereco(endereco); // Valida campos obrigatórios
            if (endereco.getClienteId() <= 0) {
                throw new IllegalArgumentException("ID do cliente inválido.");
            }
            enderecoDAO.atualizarEndereco(endereco);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
            return false;
        } catch (SQLException e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
            return false;
        }
    }

    // Método para excluir um endereço
    public boolean excluirEndereco(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID do endereço inválido.");
            }
            enderecoDAO.excluirEndereco(id);
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir endereço: " + e.getMessage());
            return false;
        }
    }

    // Método para validar endereço
    private void validarEndereco(Endereco endereco) {
        if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
            throw new IllegalArgumentException("A rua do endereço não pode ser vazia.");
        }
        if (endereco.getNumero() == null || endereco.getNumero().isEmpty()) {
            throw new IllegalArgumentException("O número do endereço não pode ser vazio.");
        }
        if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
            throw new IllegalArgumentException("O bairro do endereço não pode ser vazio.");
        }
        if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
            throw new IllegalArgumentException("A cidade do endereço não pode ser vazia.");
        }
        if (endereco.getEstado() == null || endereco.getEstado().isEmpty()) {
            throw new IllegalArgumentException("O estado do endereço não pode ser vazio.");
        }
        if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
            throw new IllegalArgumentException("O CEP do endereço não pode ser vazio.");
        }
    }
}