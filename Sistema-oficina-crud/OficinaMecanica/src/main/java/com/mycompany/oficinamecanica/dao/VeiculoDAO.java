package com.mycompany.oficinamecanica.dao;

import com.mycompany.oficinamecanica.model.Veiculo;
import com.mycompany.oficinamecanica.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    // Método para adicionar um veículo
    public void adicionarVeiculo(Veiculo veiculo) throws SQLException {
        if (veiculo.getClienteId() <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa não pode ser vazia.");
        }

        // Verifica se a placa já existe
        if (placaExiste(veiculo.getPlaca())) {
            throw new IllegalArgumentException("Placa já cadastrada.");
        }

        String sql = "INSERT INTO veiculos (modelo, placa, ano, cliente_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setInt(3, veiculo.getAno());
            stmt.setInt(4, veiculo.getClienteId());
            stmt.executeUpdate();
        }
    }

    // Método para listar todos os veículos
    public List<Veiculo> listarVeiculos() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql); // Usa PreparedStatement mesmo para consultas fixas
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setClienteId(rs.getInt("cliente_id"));
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    // Método para atualizar um veículo
    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        if (veiculo.getId() <= 0) {
            throw new IllegalArgumentException("ID do veículo inválido.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new IllegalArgumentException("Placa não pode ser vazia.");
        }
        if (veiculo.getClienteId() <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }

        // Verifica se a placa já existe (exceto para o veículo atual)
        if (placaExisteParaOutroVeiculo(veiculo.getPlaca(), veiculo.getId())) {
            throw new IllegalArgumentException("Placa já cadastrada para outro veículo.");
        }

        String sql = "UPDATE veiculos SET modelo = ?, placa = ?, ano = ?, cliente_id = ? WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setInt(3, veiculo.getAno());
            stmt.setInt(4, veiculo.getClienteId());
            stmt.setInt(5, veiculo.getId());
            stmt.executeUpdate();
        }
    }

    // Método para excluir um veículo
    public void excluirVeiculo(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do veículo inválido.");
        }

        // Verifica se o veículo tem serviços associados
        if (veiculoTemServicos(id)) {
            throw new IllegalArgumentException("Não é possível excluir veículo com serviços cadastrados.");
        }

        String sql = "DELETE FROM veiculos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método para buscar veículo por ID
    public Veiculo buscarVeiculoPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do veículo inválido.");
        }

        String sql = "SELECT * FROM veiculos WHERE id = ?";
        try (Connection conn = Conexao.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o valor do parâmetro
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setClienteId(rs.getInt("cliente_id"));
                return veiculo;
            }
        }
        return null; 
    }

    // Método para listar veículos de um cliente específico
    public List<Veiculo> listarVeiculosPorCliente(int clienteId) throws SQLException {
        if (clienteId <= 0) {
            throw new IllegalArgumentException("ID do cliente inválido.");
        }

        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos WHERE cliente_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setClienteId(rs.getInt("cliente_id"));
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    // Método auxiliar para verificar se a placa já existe
    private boolean placaExiste(String placa) throws SQLException {
        String sql = "SELECT COUNT(*) FROM veiculos WHERE placa = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Método auxiliar para verificar placa duplicada (exceto para o veículo atual)
    private boolean placaExisteParaOutroVeiculo(String placa, int veiculoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM veiculos WHERE placa = ? AND id != ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            stmt.setInt(2, veiculoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Método auxiliar para verificar se o veículo tem serviços associados
    public boolean veiculoTemServicos(int veiculoId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM servicos WHERE veiculo_id = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, veiculoId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}