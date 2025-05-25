/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.oficinamecanica.view.cliente.servico.veiculo.nota;

import com.mycompany.oficinamecanica.controller.VeiculoController;
import com.mycompany.oficinamecanica.model.Veiculo;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class TelaCadastroVeiculo extends javax.swing.JFrame {
    
    private VeiculoController veiculoController;
    private Veiculo veiculo;
   
    public TelaCadastroVeiculo() {
        initComponents();
        veiculoController = new VeiculoController();
        this.veiculo = null;
    }
    
    public TelaCadastroVeiculo(int id) {
        initComponents();
        veiculoController = new VeiculoController();
        try {
            this.veiculo = veiculoController.buscarVeiculoPorId(id);
            if (veiculo != null) {
                preencherCampos(veiculo);
            } else {
                JOptionPane.showMessageDialog(this, "Veículo não encontrado.");
                dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao carregar veículo: " + e.getMessage());
            dispose();
        }
    }
    
    private void preencherCampos(Veiculo veiculo) {
        txtModelo.setText(veiculo.getModelo());
        txtPlaca.setText(veiculo.getPlaca());
        txtAno.setText(String.valueOf(veiculo.getAno()));
        txtClienteId.setText(String.valueOf(veiculo.getClienteId()));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblModelo = new java.awt.Label();
        txtModelo = new javax.swing.JTextField();
        lblPlaca = new java.awt.Label();
        txtPlaca = new javax.swing.JTextField();
        lblAno = new java.awt.Label();
        txtAno = new javax.swing.JTextField();
        lblClienteId = new javax.swing.JLabel();
        txtClienteId = new javax.swing.JTextField();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblModelo.setText("Modelo:");

        txtModelo.setPreferredSize(new java.awt.Dimension(100, 26));

        lblPlaca.setText("Placa:");

        txtPlaca.setPreferredSize(new java.awt.Dimension(100, 26));

        lblAno.setText("Ano:");

        txtAno.setPreferredSize(new java.awt.Dimension(100, 26));

        lblClienteId.setText("Cliente ID:");

        txtClienteId.setPreferredSize(new java.awt.Dimension(100, 26));

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

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSalvar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblClienteId)
                        .addGap(12, 12, 12)
                        .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVoltar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lblClienteId))
                    .addComponent(txtClienteId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(231, 231, 231)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar)
                    .addComponent(btnVoltar)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            String modelo = txtModelo.getText();
            String placa = txtPlaca.getText();
            int ano = Integer.parseInt(txtAno.getText());
            int clienteId = Integer.parseInt(txtClienteId.getText());

            // Validação dos campos
            if (modelo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo 'Modelo' é obrigatório.");
                return;
            }
            if (placa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "O campo 'Placa' é obrigatório.");
                return;
            }
            if (ano <= 0) {
                JOptionPane.showMessageDialog(this, "O campo 'Ano' deve ser maior que zero.");
                return;
            }
            if (clienteId <= 0) {
                JOptionPane.showMessageDialog(this, "O campo 'Cliente ID' deve ser válido.");
                return;
            }

            // Cria ou atualiza o veículo
            Veiculo novoVeiculo = new Veiculo(modelo, placa, ano, clienteId);
            if (this.veiculo != null) {
                novoVeiculo.setId(this.veiculo.getId()); // Mantém o ID existente
                veiculoController.atualizarVeiculo(novoVeiculo); // Edição de veículo
            } else {
                veiculoController.adicionarVeiculo(novoVeiculo); // Novo veículo
            }

            JOptionPane.showMessageDialog(this, "Veículo salvo com sucesso!");
            TelaListagemVeiculo telaListagemVeiculo = new TelaListagemVeiculo();
            telaListagemVeiculo.setVisible(true);
            dispose();
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Os campos 'Ano' e 'Cliente ID' devem ser números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar veículo: " + e.getMessage());
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
            TelaListagemVeiculo telaListagemVeiculo = new TelaListagemVeiculo();
            telaListagemVeiculo.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao voltar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCadastroVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroVeiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private java.awt.Label lblAno;
    private javax.swing.JLabel lblClienteId;
    private java.awt.Label lblModelo;
    private java.awt.Label lblPlaca;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtClienteId;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
