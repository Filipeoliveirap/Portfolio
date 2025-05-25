package com.mycompany.oficinamecanica.controller;

import com.mycompany.oficinamecanica.dao.NotaFiscalDAO;
import com.mycompany.oficinamecanica.dao.ServicoDAO; 
import com.mycompany.oficinamecanica.model.NotaFiscal;
import java.sql.SQLException;
import java.util.List;

public class NotaFiscalController {

    private NotaFiscalDAO notaFiscalDAO;
    private ServicoDAO servicoDAO; 

    public NotaFiscalController() {
        this.notaFiscalDAO = new NotaFiscalDAO();
        this.servicoDAO = new ServicoDAO();
    }

    // Método para emitir uma nota fiscal
    public void emitirNotaFiscal(NotaFiscal notaFiscal) {
        try {
            validarNotaFiscal(notaFiscal);
            if (notaFiscal.getServicoId() <= 0) {
                throw new IllegalArgumentException("ID do serviço inválido.");
            }
            if (servicoDAO.buscarServicoPorId(notaFiscal.getServicoId()) == null) {
                throw new IllegalArgumentException("Serviço não encontrado.");
            }
            notaFiscalDAO.adicionarNotaFiscal(notaFiscal);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao emitir nota fiscal", e);
        }
    }

    // Método para listar todas as notas fiscais
    public List<NotaFiscal> listarNotasFiscais() {
        try {
            return notaFiscalDAO.listarNotasFiscais();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar notas fiscais", e);
        }
    }

    // Método para atualizar uma nota fiscal
    public void atualizarNotaFiscal(NotaFiscal notaFiscal) {
        try {
            validarNotaFiscal(notaFiscal);
            if (notaFiscal.getId() <= 0) {
                throw new IllegalArgumentException("ID da nota fiscal inválido.");
            }
            if (servicoDAO.buscarServicoPorId(notaFiscal.getServicoId()) == null) {
                throw new IllegalArgumentException("Serviço não encontrado.");
            }
            notaFiscalDAO.atualizarNotaFiscal(notaFiscal);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar nota fiscal", e);
        }
    }

    // Método para excluir uma nota fiscal
    public void excluirNotaFiscal(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID da nota fiscal inválido.");
            }
            notaFiscalDAO.excluirNotaFiscal(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir nota fiscal", e);
        }
    }

    // Método para buscar nota fiscal por ID
    public NotaFiscal buscarNotaFiscalPorId(int id) {
        try {
            if (id <= 0) {
                throw new IllegalArgumentException("ID da nota fiscal inválido.");
            }
            return notaFiscalDAO.buscarNotaFiscalPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar nota fiscal", e);
        }
    }

    // Validações da nota fiscal
    private void validarNotaFiscal(NotaFiscal notaFiscal) {
        if (notaFiscal.getNumero() == null || notaFiscal.getNumero().isEmpty()) {
            throw new IllegalArgumentException("Número da nota fiscal não pode ser vazio.");
        }
        if (notaFiscal.getDataEmissao() == null) {
            throw new IllegalArgumentException("Data de emissão não pode ser nula.");
        }
        if (notaFiscal.getValorTotal() <= 0) {
            throw new IllegalArgumentException("Valor total deve ser maior que zero.");
        }
    }
}