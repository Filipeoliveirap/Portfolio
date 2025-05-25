package com.mycompany.oficinamecanica.dao;

import com.mycompany.oficinamecanica.model.Cliente;
import com.mycompany.oficinamecanica.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    //Método para adicionar um cliente
    public int adicionarCliente(Cliente cliente) throws SQLException{
       String sql = "INSERT INTO clientes (nome, telefone) VALUES (?, ?)";
        try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getTelefone());
        stmt.executeUpdate();

        // Recupera o ID gerado
        ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1); // Retorna o ID do cliente
        } else {
            throw new SQLException("Falha ao obter o ID do cliente após a inserção.");
        }
    }
    }
    
    //Método para listar todos os clientes
    public List<Cliente> listarClientes() throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try(Connection conn = Conexao.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    
    //Método para atualizar um cliete
    public void atualizarCliente(Cliente cliente) throws SQLException{
        String sql = "UPDATE clientes SET nome = ?, telefone = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setInt(3, cliente.getId());
            stmt.executeUpdate();
        }
    }
    
    //Método para exluir cliente
    public void excluirCliente(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    //Método para buscar um cliente pelo id
    public Cliente buscarClientePeloId(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try(Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                return cliente;
            } else {
                throw new SQLException("Cliente com ID " + id + " não encontrado.");
            }
        }
    }
    
}
