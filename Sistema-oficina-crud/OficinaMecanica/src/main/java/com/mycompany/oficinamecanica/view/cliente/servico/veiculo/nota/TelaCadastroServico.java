/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.oficinamecanica.view.cliente.servico.veiculo.nota;

import com.mycompany.oficinamecanica.controller.ServicoController;
import com.mycompany.oficinamecanica.controller.VeiculoController;
import com.mycompany.oficinamecanica.model.Servico;
import com.mycompany.oficinamecanica.model.Veiculo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.List;

public class TelaCadastroServico extends javax.swing.JFrame {

    private ServicoController servicoController;
    private VeiculoController veiculoController;
    private Servico servico;
    
    public TelaCadastroServico() {
        initComponents();
        servicoController = new ServicoController();
        veiculoController = new VeiculoController();
        this.servico = null;
        cmbVeiculos.setModel(new javax.swing.DefaultComboBoxModel<>());
        carregarVeiculos();
    }
    
    public TelaCadastroServico(int id) {
        initComponents();
        servicoController = new ServicoController();
        veiculoController = new VeiculoController(); // Instancia o VeiculoController
        try {
            this.servico = servicoController.buscarServicoPorId(id);
            if (servico != null) {
                preencherCampos(servico);
            } else {
                JOptionPane.showMessageDialog(this, "Serviço não encontrado.");
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar serviço: " + e.getMessage());
            dispose();
        }
        carregarVeiculos(); 
    }
    
    private void carregarVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoController.listarVeiculos(); // Usa o método listarVeiculos
            for (Veiculo veiculo : veiculos) {
                cmbVeiculos.addItem(veiculo); // Adiciona cada veículo ao ComboBox
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar veículos: " + e.getMessage());
        }
    }
    
     private void preencherCampos(Servico servico) {
        txtDescricao.setText(servico.getDescricao());
        txtPreco.setText(String.valueOf(servico.getPreco()));
        txtData.setText(servico.getData().toString());
        for (int i = 0; i < cmbVeiculos.getItemCount(); i++) {
            Veiculo veiculo = cmbVeiculos.getItemAt(i);
            if (veiculo.getId() == servico.getVeiculoId()) {
                cmbVeiculos.setSelectedItem(veiculo); // Seleciona o veículo correspondente
                break;
            }
        }
    }
     
    private void limparCampos() {
        txtDescricao.setText("");
        txtPreco.setText("");
        txtData.setText("");
        cmbVeiculos.setSelectedIndex(-1); // Limpa a seleção do JComboBox
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblDescricao = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescricao = new javax.swing.JTextArea();
        txtPreco = new javax.swing.JTextField();
        lblData = new java.awt.Label();
        txtData = new javax.swing.JTextField();
        lblVeiculoId = new java.awt.Label();
        cmbVeiculos = new javax.swing.JComboBox<>();
        btnVoltar = new javax.swing.JButton();
        lblPreco = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblDescricao.setText("Descrição:");

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        jScrollPane1.setViewportView(txtDescricao);

        txtPreco.setPreferredSize(new java.awt.Dimension(100, 26));
        txtPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecoActionPerformed(evt);
            }
        });

        lblData.setText("Data:");

        txtData.setPreferredSize(new java.awt.Dimension(100, 26));

        lblVeiculoId.setText("Veículo ID:");

        cmbVeiculos.setPreferredSize(new java.awt.Dimension(150, 26));

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        lblPreco.setText("Preço:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(lblPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(lblVeiculoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoltar))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbVeiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVeiculoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(173, 173, 173)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnVoltar)
                    .addComponent(btnSalvar))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            // Recupera os valores dos campos
            String descricao = txtDescricao.getText();
            double preco = Double.parseDouble(txtPreco.getText());
            LocalDate data = LocalDate.parse(txtData.getText());

            // Validação dos campos
            if (descricao.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo 'Descrição' é obrigatório.");
                return;
            }
            if (preco <= 0) {
                JOptionPane.showMessageDialog(this, "O campo 'Preço' deve ser maior que zero.");
                return;
            }
            if (data == null) {
                JOptionPane.showMessageDialog(this, "A data deve ser válida.");
                return;
            }

            // Recupera o veículo selecionado no JComboBox
            Veiculo veiculoSelecionado = (Veiculo) cmbVeiculos.getSelectedItem();
            if (veiculoSelecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um veículo válido.");
                return;
            }
            int veiculoId = veiculoSelecionado.getId(); // Obtém o ID do veículo selecionado

            // Cria ou atualiza o serviço
            Servico novoServico = new Servico(0, descricao, preco, data, veiculoId);
            if (this.servico != null) {
                novoServico.setId(this.servico.getId()); // Mantém o ID existente
            }

            if (this.servico == null) {
                servicoController.adicionarServico(novoServico); // Novo serviço
            } else {
            servicoController.atualizarServico(novoServico); // Edição de serviço
            }

            JOptionPane.showMessageDialog(this, "Serviço salvo com sucesso!");
            
            TelaListagemServico telaListagemServico = new TelaListagemServico();
            telaListagemServico.setVisible(true);
            dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Os campos 'Preço' e 'Data' devem ser números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar serviço: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        try {
            // Fecha a tela atual
            this.dispose();
            // Abre a tela anterior (ex.: TelaPrincipal)
            TelaListagemServico telaListagemServico = new TelaListagemServico();
            telaListagemServico.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao voltar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void txtPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroServico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<Veiculo> cmbVeiculos;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label lblData;
    private java.awt.Label lblDescricao;
    private java.awt.Label lblPreco;
    private java.awt.Label lblVeiculoId;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextArea txtDescricao;
    private javax.swing.JTextField txtPreco;
    // End of variables declaration//GEN-END:variables
}
