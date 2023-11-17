/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.visao.Veiculo;

import com.mycompany.dao.DaoMarca;
import com.mycompany.dao.DaoVeiculo;
import com.mycompany.ferramentas.Constantes;
import com.mycompany.ferramentas.DadosTemporarios;
import com.mycompany.ferramentas.Formularios;
import com.mycompany.modelo.ModVeiculo;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author brian.7908
 */
public class CadVeiculo extends javax.swing.JFrame {

    /**
     * Creates new form CadVeiculo
     */
    public CadVeiculo() {
        initComponents();
        
        carregarMarcas();
        
        if(!existeDadosTemporarios()){
            DaoVeiculo daoV = new DaoVeiculo();
            
            int id  = daoV.buscarProximoId();
            if(id>0)
                tfId.setText(String.valueOf(id));
                
                 btnAcao.setText(Constantes.BTN_SALVAR_TEXT);
            btnExcluir.setVisible(false);
        }else{
                btnAcao.setText(Constantes.BTN_ALTERAR_TEXT);
            btnExcluir.setVisible(true);
            }
            recuperaIdMarca();
            
            setLocationRelativeTo(null);
            
            tfId.setEnabled(false);
            tfidMar.setEnabled(false);
    }

    private Boolean existeDadosTemporarios(){
         if(DadosTemporarios.tempObject instanceof ModVeiculo){
            int id = ((ModVeiculo) DadosTemporarios.tempObject).getId();
            int idM = ((ModVeiculo) DadosTemporarios.tempObject).getIdMar();
            String nome = ((ModVeiculo) DadosTemporarios.tempObject).getNome();
            String placa = ((ModVeiculo) DadosTemporarios.tempObject).getPlaca();
            int Ano = ((ModVeiculo) DadosTemporarios.tempObject).getAno();

            tfId.setText(String.valueOf(id));
            tfidMar.setText(String.valueOf(idM));
            tfNome.setText(nome);
            tfPlaca.setText(placa);
            tfAno.setText(String.valueOf(Ano));

            //
            try{
                DaoMarca daoM = new DaoMarca();
                ResultSet resultSet = daoM.listarPorId(idM);
                resultSet.next();
                String marca = resultSet.getString("NOME");
                int index = 0;
                for(int i = 0; i < JcbMar.getItemCount(); i++){
                    if(JcbMar.getItemAt(i).equals(marca)){
                        index = i;
                        break;
                    }
                } 
                JcbMar.setSelectedIndex(index);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
           
                return true;
            }else 
                return false;
    }
    
    private void inserir(){
     DaoVeiculo daoV = new DaoVeiculo();
     
     if(daoV.inserir(Integer.parseInt(tfId.getText()), Integer.parseInt(tfidMar.getText()), tfNome.getText(), tfPlaca.getText(), Integer.parseInt(tfAno.getText()))){
         JOptionPane.showMessageDialog(null, "Veículo salvo com sucesso!");
         
         tfId.setText("" + daoV.buscarProximoId());
         tfNome.setText("");
         tfPlaca.setText("");
         tfAno.setText("");
         
     }else{
        JOptionPane.showMessageDialog(null, "Não foi possível salvar o veículo!");
     }
    }
    
    private void alterar(){
        DaoVeiculo daoV = new DaoVeiculo();
        
        if(daoV.alterar(Integer.parseInt(tfId.getText()), Integer.parseInt(tfidMar.getText()), tfNome.getText(), tfPlaca.getText(), Integer.parseInt(tfAno.getText()))){
         JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso!");
         
            tfId.setText("");
         tfNome.setText("");
         tfPlaca.setText("");
         tfAno.setText("");
        }else{
                JOptionPane.showMessageDialog(null, "Não foi possível alterar o veículo!");
        }
        
        ((ListVeiculo) Formularios.ListVeiculo).listarTodos();
        
        dispose();
    }
    
    private void excluir(){
        DaoVeiculo daoV = new DaoVeiculo();
        
        if(daoV.excluir(Integer.parseInt(tfId.getText()))){
        JOptionPane.showMessageDialog(null, "Veículo excluído com sucesso!");
        
        }else{
           JOptionPane.showMessageDialog(null, "Não foi possível excluir o veículo!");
        }
        ((ListVeiculo) Formularios.ListVeiculo).listarTodos();
            
            dispose();
    }
    
     private void carregarMarcas(){
        try{
            DaoMarca daoM = new DaoMarca();

            ResultSet resultSet = daoM.listarTodos();

            while(resultSet.next()){
                JcbMar.addItem(resultSet.getString("NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdMarca(){
        try{
            DaoMarca daoM = new DaoMarca();
            ResultSet resultSet = daoM.listarPorNome(JcbMar.getSelectedItem().toString());
            resultSet.next();
            tfidMar.setText(resultSet.getString("ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        tfNome = new javax.swing.JTextField();
        LNome1 = new javax.swing.JLabel();
        LDesc2 = new javax.swing.JLabel();
        tfidMar = new javax.swing.JTextField();
        JcbMar = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfPlaca = new javax.swing.JTextField();
        tfAno = new javax.swing.JTextField();
        btnAcao = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        tfId = new javax.swing.JTextField();
        LNome2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de veículo");

        jPanel3.setBackground(new java.awt.Color(153, 0, 51));

        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });

        LNome1.setText("Marca");

        LDesc2.setText("Veículo");

        tfidMar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfidMarActionPerformed(evt);
            }
        });

        JcbMar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcbMarItemStateChanged(evt);
            }
        });
        JcbMar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcbMarActionPerformed(evt);
            }
        });

        jLabel10.setText("Placa");

        jLabel11.setText("Ano");

        tfAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAnoActionPerformed(evt);
            }
        });

        btnAcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcaoActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        tfId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdActionPerformed(evt);
            }
        });

        LNome2.setText("Id");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LDesc2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(tfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAno, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAcao)
                        .addGap(14, 14, 14))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LNome2)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LNome1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(JcbMar, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfidMar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(LNome2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LNome1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JcbMar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfidMar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LDesc2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAcao)
                    .addComponent(btnExcluir))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void tfidMarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfidMarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfidMarActionPerformed

    private void JcbMarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcbMarItemStateChanged
        // TODO add your handling code here:
        recuperaIdMarca();
    }//GEN-LAST:event_JcbMarItemStateChanged

    private void JcbMarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcbMarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JcbMarActionPerformed

    private void tfAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAnoActionPerformed

    private void btnAcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcaoActionPerformed
        // TODO add your handling code here:
        
            if (btnAcao.getText() == Constantes.BTN_SALVAR_TEXT)
                inserir();
            else if (btnAcao.getText().equals(Constantes.BTN_ALTERAR_TEXT))
                alterar();
    }//GEN-LAST:event_btnAcaoActionPerformed

    
    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        int escolha =
        JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o veículo " + tfNome.getText() + "?");

        if (escolha == JOptionPane.YES_OPTION)
        excluir();

    }//GEN-LAST:event_btnExcluirActionPerformed

    private void tfIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdActionPerformed

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
            java.util.logging.Logger.getLogger(CadVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadVeiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadVeiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JcbMar;
    private javax.swing.JLabel LDesc2;
    private javax.swing.JLabel LNome1;
    private javax.swing.JLabel LNome2;
    private javax.swing.JButton btnAcao;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField tfAno;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPlaca;
    private javax.swing.JTextField tfidMar;
    // End of variables declaration//GEN-END:variables
}
