package com.mycompany.oficinamecanica.dao;

import com.mycompany.oficinamecanica.model.Endereco;
import com.mycompany.oficinamecanica.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {
    //Método para adicionar um edereço
    public void adicionarEndereco(Endereco endereco) throws SQLException{
        String sql = "INSERT INTO enderecos (rua, numero, bairro, cidade, estado, cep, complemento, cliente_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getNumero());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getEstado());
            stmt.setString(6, endereco.getCep());
            stmt.setString(7, endereco.getComplemento());
            stmt.setInt(8, endereco.getClienteId());
            stmt.executeUpdate();
            
        }
    }
    
    //Método para listar endereços de um cliente
    public List<Endereco> listarEnderecosPorCliente(int clienteId) throws SQLException{
        List<Endereco> enderecos = new ArrayList<>();
        String sql = "SELECT * FROM enderecos WHERE cliente_id = ?";
        try(Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCep(rs.getString("cep"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setClienteId(rs.getInt("cliente_id"));
                enderecos.add(endereco);
                
            }
        }
        return enderecos;
    }
    
    //Método para atualizar um endereco
    public void atualizarEndereco(Endereco endereco) throws SQLException {
        String sql = "UPDATE enderecos SET rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, complemento = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getNumero());
            stmt.setString(3, endereco.getBairro());
            stmt.setString(4, endereco.getCidade());
            stmt.setString(5, endereco.getEstado());
            stmt.setString(6, endereco.getCep());
            stmt.setString(7, endereco.getComplemento());
            stmt.setInt(8, endereco.getId());
            stmt.executeUpdate();
        }
    }
    
    //Método para excluir um endereco
    public void excluirEndereco(int id) throws SQLException {
        String sql = "DELETE FROM enderecos WHERE id = ?";
        try (Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    //Método para buscar um endereço por id
    public Endereco buscarEnderecoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM enderecos WHERE id = ?";
        try(Connection conn = Conexao.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Endereco endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCep(rs.getString("cep"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setClienteId(rs.getInt("cliente_id"));
                return endereco;
            }
        }
        return null;
    }
    
    public void excluirEnderecosPorCliente(int clienteId) throws SQLException {
        String sql = "DELETE FROM enderecos WHERE cliente_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId); // Define o ID do cliente como parâmetro
            stmt.executeUpdate();
        }
    }
}
