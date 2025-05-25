package com.mycompany.oficinamecanica.dao;

import com.mycompany.oficinamecanica.model.Servico;
import com.mycompany.oficinamecanica.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    // Método para adicionar serviço
    public void adicionarServico(Servico servico) throws SQLException {
        if (servico.getVeiculoId() <= 0) {
            throw new IllegalArgumentException("ID do veículo inválido.");
        }

        String sql = "INSERT INTO servicos (descricao, preco, data, veiculo_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getPreco());
            stmt.setDate(3, Date.valueOf(servico.getData()));
            stmt.setInt(4, servico.getVeiculoId());
            stmt.executeUpdate();
        }
    }

    // Método para listar serviços (nome ajustado)
    public List<Servico> listarServicos() throws SQLException {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servicos";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getInt("id"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getDouble("preco"));
                servico.setData(rs.getDate("data").toLocalDate());
                servico.setVeiculoId(rs.getInt("veiculo_id")); // ✅ Corrigido para "veiculo_id"
                servicos.add(servico);
            }
        }
        return servicos;
    }

    // Método para atualizar um serviço
    public void atualizarServico(Servico servico) throws SQLException {
        if (servico.getId() <= 0) {
            throw new IllegalArgumentException("ID do serviço inválido.");
        }
        if (servico.getVeiculoId() <= 0) {
            throw new IllegalArgumentException("ID do veículo inválido.");
        }

        String sql = "UPDATE servicos SET descricao = ?, preco = ?, data = ?, veiculo_id = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, servico.getDescricao());
            stmt.setDouble(2, servico.getPreco());
            stmt.setDate(3, Date.valueOf(servico.getData()));
            stmt.setInt(4, servico.getVeiculoId());
            stmt.setInt(5, servico.getId());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um serviço
    public void excluirServico(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do serviço inválido.");
        }

        String sql = "DELETE FROM servicos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para buscar serviço por ID (nome ajustado)
    public Servico buscarServicoPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do serviço inválido.");
        }

        String sql = "SELECT * FROM servicos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getInt("id"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getDouble("preco"));
                servico.setData(rs.getDate("data").toLocalDate());
                servico.setVeiculoId(rs.getInt("veiculo_id")); // ✅ Corrigido para "veiculo_id"
                return servico;
            }
        }
        return null;
    }
    
    public List<Servico> listarServicosPorVeiculo(int veiculoId) throws SQLException {
    List<Servico> servicos = new ArrayList<>();
    String sql = "SELECT * FROM servicos WHERE veiculo_id = ?";
    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, veiculoId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Servico servico = new Servico();
            servico.setId(rs.getInt("id"));
            servico.setDescricao(rs.getString("descricao"));
            servico.setPreco(rs.getDouble("preco"));
            servico.setData(rs.getDate("data").toLocalDate());
            servico.setVeiculoId(rs.getInt("veiculo_id"));
            servicos.add(servico);
        }
    }
    return servicos;
    }
}