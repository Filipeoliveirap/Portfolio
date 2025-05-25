package com.mycompany.oficinamecanica.controller;

import com.mycompany.oficinamecanica.dao.ClienteDAO;
import com.mycompany.oficinamecanica.dao.EnderecoDAO;
import com.mycompany.oficinamecanica.model.Cliente;
import com.mycompany.oficinamecanica.model.Endereco;

import java.sql.SQLException;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;
    private EnderecoDAO enderecoDAO;
    public EnderecoController enderecoController = new EnderecoController();

    // Construtor
    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
        this.enderecoDAO = new EnderecoDAO();
    }

    // Método para adicionar um cliente e seus endereços
    public void adicionarCliente(Cliente cliente, List<Endereco> enderecos) {
        try {
            validarCliente(cliente);
            validarEnderecos(enderecos);
           
            if (enderecos == null || enderecos.isEmpty()) {
                throw new IllegalArgumentException("O cliente deve ter pelo menos um endereço.");
            }

            // Adiciona o cliente e recupera o ID gerado
            int clienteId = clienteDAO.adicionarCliente(cliente);

            // Adiciona os endereços associados ao cliente
            for (Endereco endereco : enderecos) {
                endereco.setClienteId(clienteId);
                enderecoDAO.adicionarEndereco(endereco);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente no banco de dados", e);
        }
    }

    // Método para listar todos os clientes
    public List<Cliente> listarClientes() {
        try {
            return clienteDAO.listarClientes();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes", e);
        }
    }

    // Método para atualizar um cliente
    public void atualizarCliente(Cliente cliente) {
        try {
            validarCliente(cliente);
            if (cliente.getId() <= 0) {
                throw new IllegalArgumentException("O ID do cliente deve ser válido.");
            }
            clienteDAO.atualizarCliente(cliente);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente", e);
        }
    }

    // Método para excluir um cliente e seus endereços
    public void excluirCliente(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("O ID do cliente está inválido.");
            }

            // Exclui todos os endereços associados ao cliente
            enderecoDAO.excluirEnderecosPorCliente(id); // Correção: exclui endereços pelo cliente_id

            // Exclui o cliente
            clienteDAO.excluirCliente(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente", e);
        }
    }

    // Método para buscar cliente pelo ID
    public Cliente buscarClientePeloId(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("O ID do cliente deve ser válido.");
            }
            return clienteDAO.buscarClientePeloId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente pelo ID", e);
        }
    }

    // Método para validar cliente
    private void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente não pode ser vazio.");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("O telefone do cliente não pode ser vazio.");
        }
    }

    // Método para listar endereços de um cliente
    public List<Endereco> listarEnderecosDoCliente(int clienteId) {
        try {
            return enderecoDAO.listarEnderecosPorCliente(clienteId);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar endereços do cliente", e);
        }
    }
    
    private void validarEnderecos(List<Endereco> enderecos) {
        if (enderecos == null || enderecos.isEmpty()) {
        throw new IllegalArgumentException("O cliente deve ter pelo menos um endereço.");
        }
        for (Endereco endereco : enderecos) {
            if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
            throw new IllegalArgumentException("A rua do endereço não pode ser vazia.");
            }
            if (endereco.getNumero() == null || endereco.getNumero().isEmpty()) {
            throw new IllegalArgumentException("O número do endereço não pode ser vazio.");
            }
            if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
            throw new IllegalArgumentException("A cidade do endereço não pode ser vazia.");
            }
        }
    }
    
    public void atualizarEndereco(Endereco endereco) {
        try {
            enderecoDAO.atualizarEndereco(endereco);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar endereço", e);
        }
    }
}