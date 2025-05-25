package com.mycompany.oficinamecanica.dao;

import com.mycompany.oficinamecanica.model.NotaFiscal;
import com.mycompany.oficinamecanica.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscalDAO {

    // Método para adicionar nota fiscal
    public void adicionarNotaFiscal(NotaFiscal notaFiscal) throws SQLException {
        if (notaFiscal == null) {
            throw new IllegalArgumentException("Nota fiscal não pode ser nula.");
        }
        if (notaFiscal.getNumero() == null || notaFiscal.getNumero().isEmpty()) {
            throw new IllegalArgumentException("Número da nota fiscal não pode ser vazio.");
        }
        if (notaFiscal.getValorTotal() <= 0) {
            throw new IllegalArgumentException("Valor total deve ser maior que zero.");
        }
        if (notaFiscal.getServicoId() <= 0) {
            throw new IllegalArgumentException("ID do serviço inválido.");
        }

        String sql = "INSERT INTO notas_fiscais (numero, data_emissao, valor_total, servico_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, notaFiscal.getNumero());
            stmt.setDate(2, Date.valueOf(notaFiscal.getDataEmissao()));
            stmt.setDouble(3, notaFiscal.getValorTotal());
            stmt.setInt(4, notaFiscal.getServicoId());
            stmt.executeUpdate();
        }
    }

    // Método para listar todas as notas fiscais
    public List<NotaFiscal> listarNotasFiscais() throws SQLException {
        List<NotaFiscal> notasFiscais = new ArrayList<>();
        String sql = "SELECT * FROM notas_fiscais";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql); // Usar PreparedStatement mesmo para consultas fixas
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                NotaFiscal notaFiscal = new NotaFiscal();
                notaFiscal.setId(rs.getInt("id"));
                notaFiscal.setNumero(rs.getString("numero"));
                notaFiscal.setDataEmissao(rs.getDate("data_emissao").toLocalDate());
                notaFiscal.setValorTotal(rs.getDouble("valor_total"));
                notaFiscal.setServicoId(rs.getInt("servico_id"));
                notasFiscais.add(notaFiscal);
            }
        }
        return notasFiscais;
    }

    // Método para atualizar uma nota fiscal
    public void atualizarNotaFiscal(NotaFiscal notaFiscal) throws SQLException {
        if (notaFiscal == null) {
            throw new IllegalArgumentException("Nota fiscal não pode ser nula.");
        }
        if (notaFiscal.getId() <= 0) {
            throw new IllegalArgumentException("ID da nota fiscal inválido.");
        }
        if (notaFiscal.getNumero() == null || notaFiscal.getNumero().isEmpty()) {
            throw new IllegalArgumentException("Número da nota fiscal não pode ser vazio.");
        }
        if (notaFiscal.getValorTotal() <= 0) {
            throw new IllegalArgumentException("Valor total deve ser maior que zero.");
        }
        if (notaFiscal.getServicoId() <= 0) {
            throw new IllegalArgumentException("ID do serviço inválido.");
        }

        String sql = "UPDATE notas_fiscais SET numero = ?, data_emissao = ?, valor_total = ?, servico_id = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, notaFiscal.getNumero());
            stmt.setDate(2, Date.valueOf(notaFiscal.getDataEmissao()));
            stmt.setDouble(3, notaFiscal.getValorTotal());
            stmt.setInt(4, notaFiscal.getServicoId());
            stmt.setInt(5, notaFiscal.getId());
            stmt.executeUpdate();
        }
    }

    // Método para excluir uma nota fiscal
    public void excluirNotaFiscal(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID da nota fiscal inválido.");
        }

        String sql = "DELETE FROM notas_fiscais WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para buscar nota fiscal por ID (nome ajustado)
    public NotaFiscal buscarNotaFiscalPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID da nota fiscal inválido.");
        }

        String sql = "SELECT * FROM notas_fiscais WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // ResultSet no try-with-resources
            if (rs.next()) {
                NotaFiscal notaFiscal = new NotaFiscal();
                notaFiscal.setId(rs.getInt("id"));
                notaFiscal.setNumero(rs.getString("numero"));
                notaFiscal.setDataEmissao(rs.getDate("data_emissao").toLocalDate());
                notaFiscal.setValorTotal(rs.getDouble("valor_total"));
                notaFiscal.setServicoId(rs.getInt("servico_id"));
                return notaFiscal;
            }
        }
        return null;
    }
}