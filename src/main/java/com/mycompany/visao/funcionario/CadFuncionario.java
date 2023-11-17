/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.visao.funcionario;

import com.mycompany.dao.DaoFuncao;
import com.mycompany.dao.DaoFuncionario;
import com.mycompany.dao.DaoPessoa;
import com.mycompany.ferramentas.Constantes;
import com.mycompany.ferramentas.DadosTemporarios;
import com.mycompany.ferramentas.Formularios;
import com.mycompany.modelo.ModFuncionario;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author brian.7908
 */
public class CadFuncionario extends javax.swing.JFrame {

    /**
     * Creates new form CadFuncionario
     */
    public CadFuncionario() {
        initComponents();
        
        carregarFuncoes();
        carregarPessoas();
        
        if(!existeDadosTemporarios()){
            DaoFuncionario daoFun = new DaoFuncionario();
            
              int id = daoFun.buscarProximoId();
            if (id>0)
                tfId.setText(String.valueOf(id));
            
              btnAcao.setText(Constantes.BTN_SALVAR_TEXT);
            btnExcluir.setVisible(false);
            }else{
            btnAcao.setText(Constantes.BTN_ALTERAR_TEXT);
            btnExcluir.setVisible(true);
        }
        recuperaIdFuncao();
        recuperaIdPessoa();
        
        setLocationRelativeTo(null);
        
        tfId.setEnabled(false);
        tfIdFunc.setEnabled(false);
        tfIdPess.setEnabled(false);
    }

    private Boolean existeDadosTemporarios(){
        if(DadosTemporarios.tempObject instanceof ModFuncionario){
            int id = ((ModFuncionario) DadosTemporarios.tempObject).getId();
            int idF = ((ModFuncionario) DadosTemporarios.tempObject).getIdFuncao();
            int idPess = ((ModFuncionario) DadosTemporarios.tempObject).getIdPess();

            tfId.setText(String.valueOf(id));
            tfIdFunc.setText(String.valueOf(idF));
            tfIdPess.setText(String.valueOf(idPess));
        
            //
            try{
                DaoFuncao daoF = new DaoFuncao();
                ResultSet rS = daoF.listarPorId(idF);
                rS.next();
                String func = rS.getString("NOME");
                int index = 0;
                for (int i = 0; i < jcbFunc.getItemCount(); i++) {
                    if (jcbFunc.getItemAt(i).equals(func)) {
                        index = i;
                        break;
                    }
                }
                jcbFunc.setSelectedIndex(index);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            //
            
            //
            try{
                DaoPessoa daoP = new DaoPessoa();
                ResultSet rS = daoP.listarPorId(idPess);
                rS.next();
                String pess = rS.getString("P.NOME");
                int index = 0;
                for (int i = 0; i < jcbPessoa.getItemCount(); i++) {
                    if(jcbPessoa.getItemAt(i).equals(pess)){
                    index = i;
                    break;
                    }
                }
                jcbPessoa.setSelectedIndex(index);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            
         DadosTemporarios.tempObject = null;
            
                return true;
            }else 
                return false;
   }
    
    private void inserir(){
        DaoFuncionario daoF = new DaoFuncionario();
        
        if (daoF.inserir(Integer.parseInt(tfId.getText()), Integer.parseInt(tfIdFunc.getText()), Integer.parseInt(tfIdPess.getText()))) {
            JOptionPane.showMessageDialog(null, "Funcionário adicionado com sucesso!");
            
            tfId.setText(" " + daoF.buscarProximoId());
        
        }else{
        JOptionPane.showMessageDialog(null, "Não foi possível salvar o funcionário!");
        }
    }
    
    private void alterar(){
    DaoFuncionario daoF = new DaoFuncionario();
        
        if (daoF.alterar(Integer.parseInt(tfId.getText()), Integer.parseInt(tfIdFunc.getText()), Integer.parseInt(tfIdPess.getText()))) {
            JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!");
            
            tfId.setText("" + daoF.buscarProximoId());
        
        }else{
        JOptionPane.showMessageDialog(null, "Não foi possível atualizar o funcionário!");
        }
        
        ((ListFuncionario) Formularios.listFuncionario).listarTodos();
        
        dispose();
    }
    
    private void excluir(){
    DaoFuncionario daoF = new DaoFuncionario();
    
    if (daoF.excluir(Integer.parseInt(tfId.getText()))){
        JOptionPane.showMessageDialog(null, "Funcionário " + (String)jcbPessoa.getSelectedItem() + " excluído com sucesso!");
        
        tfId.setText("");
    }else{
        JOptionPane.showMessageDialog(null, "Não foi possível excluir o funcionário!");
    }
    ((ListFuncionario) Formularios.listFuncionario).listarTodos();
        
        dispose();
    }
    
    private void carregarFuncoes(){
        try{
            DaoFuncao daoF = new DaoFuncao();

            ResultSet resultSet = daoF.listarTodos();

            while(resultSet.next()){
                jcbFunc.addItem(resultSet.getString("NOME"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
        
        private void recuperaIdFuncao(){
        try{
            DaoFuncao daoF = new DaoFuncao();
            ResultSet resultSet = daoF.listarPorNome(jcbFunc.getSelectedItem().toString());
            
            resultSet.next();
            tfIdFunc.setText(resultSet.getString("ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
    private void carregarPessoas(){
        try{
            DaoPessoa daoP = new DaoPessoa();

            ResultSet resultSet = daoP.listarTodos();

            while(resultSet.next()){
                jcbPessoa.addItem(resultSet.getString("P.NOME"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
        
        private void recuperaIdPessoa(){
        try{
            DaoPessoa daoP = new DaoPessoa();
            ResultSet resultSet = daoP.listarPorNome(jcbPessoa.getSelectedItem().toString());
            
            resultSet.next();
            tfIdPess.setText(resultSet.getString("P.ID"));
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

        jPanel1 = new javax.swing.JPanel();
        LId = new javax.swing.JLabel();
        LNome = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        tfIdFunc = new javax.swing.JTextField();
        jcbFunc = new javax.swing.JComboBox<>();
        btnAcao = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jcbPessoa = new javax.swing.JComboBox<>();
        tfIdPess = new javax.swing.JTextField();
        LNome1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de funcionário");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));

        LId.setText("ID");

        LNome.setText("Função");

        tfIdFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdFuncActionPerformed(evt);
            }
        });

        jcbFunc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbFuncItemStateChanged(evt);
            }
        });
        jcbFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbFuncActionPerformed(evt);
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

        jcbPessoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbPessoaItemStateChanged(evt);
            }
        });
        jcbPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPessoaActionPerformed(evt);
            }
        });

        tfIdPess.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdPessActionPerformed(evt);
            }
        });

        LNome1.setText("Pessoa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 360, Short.MAX_VALUE)
                        .addComponent(btnAcao))
                    .addComponent(LNome1)
                    .addComponent(LNome)
                    .addComponent(LId)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcbFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfIdFunc, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcbPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfIdPess, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LNome)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LNome1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdPess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcluir)
                    .addComponent(btnAcao))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfIdFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdFuncActionPerformed

    private void jcbFuncItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbFuncItemStateChanged
        // TODO add your handling code here:
        recuperaIdFuncao();
    }//GEN-LAST:event_jcbFuncItemStateChanged

    private void jcbFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbFuncActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbFuncActionPerformed

    private void btnAcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcaoActionPerformed
        // TODO add your handling code here:
        if (btnAcao.getText() == Constantes.BTN_SALVAR_TEXT){
            inserir();

            if(Formularios.CadFuncao != null){
                ((CadFuncionario) Formularios.CadFuncionario).carregarFuncoes();
                dispose();
            }else if (Formularios.CadPessoa != null){
                ((CadFuncionario) Formularios.CadFuncionario).carregarPessoas();
                dispose();
            }
        }else if (btnAcao.getText().equals(Constantes.BTN_ALTERAR_TEXT))
        alterar();
    }//GEN-LAST:event_btnAcaoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        int escolha =
        JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o funcionário " + jcbPessoa.getSelectedItem() + "?");

        if (escolha == JOptionPane.YES_OPTION)
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jcbPessoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbPessoaItemStateChanged
        // TODO add your handling code here:
        recuperaIdPessoa();
    }//GEN-LAST:event_jcbPessoaItemStateChanged

    private void jcbPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPessoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbPessoaActionPerformed

    private void tfIdPessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdPessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdPessActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        Formularios.CadFuncionario = null;
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(CadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LId;
    private javax.swing.JLabel LNome;
    private javax.swing.JLabel LNome1;
    private javax.swing.JButton btnAcao;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> jcbFunc;
    private javax.swing.JComboBox<String> jcbPessoa;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfIdFunc;
    private javax.swing.JTextField tfIdPess;
    // End of variables declaration//GEN-END:variables
}
