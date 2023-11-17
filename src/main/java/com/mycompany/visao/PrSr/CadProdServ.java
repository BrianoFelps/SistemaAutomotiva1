/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.visao.PrSr;

import com.mycompany.dao.DaoGpServico;
import com.mycompany.dao.DaoPrSr;
import com.mycompany.ferramentas.Constantes;
import com.mycompany.ferramentas.DadosTemporarios;
import com.mycompany.ferramentas.Formularios;
import com.mycompany.modelo.ModPrSr;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author brian.7908
 */
public class CadProdServ extends javax.swing.JFrame {

    /**
     * Creates new form CadProdServ
     */
    public CadProdServ() {
        initComponents();
        
        carregarGrupos();
        
        if(!existeDadosTemporarios()){
            DaoPrSr daoPs = new DaoPrSr();
            
            int id = daoPs.buscarProximoId();
            if(id>0)
            tfId.setText(String.valueOf(id));
            
            btnAcao.setText(Constantes.BTN_SALVAR_TEXT);
            btnExcluir.setVisible(false);
    }else{
            btnAcao.setText(Constantes.BTN_ALTERAR_TEXT);
            btnExcluir.setVisible(true);
        }
        recuperaIdGrupo();
         
        setLocationRelativeTo(null);
        
        tfId.setEnabled(false);
        tfIdGs.setEnabled(false);
    }
    
    private Boolean existeDadosTemporarios (){
    if(DadosTemporarios.tempObject instanceof ModPrSr){
            int id = ((ModPrSr) DadosTemporarios.tempObject).getId();
            int idGS = ((ModPrSr) DadosTemporarios.tempObject).getIdGrupo();
            String nome = ((ModPrSr) DadosTemporarios.tempObject).getNome();
            String desc = ((ModPrSr) DadosTemporarios.tempObject).getDescricao();
            Double pre = ((ModPrSr) DadosTemporarios.tempObject).getPreco();

            tfId.setText(String.valueOf(id));
            tfIdGs.setText(String.valueOf(idGS));
            tfNome.setText(nome);
            taDesc.setText(desc);
            tfPreço.setText(String.valueOf(pre));

            //
            try{
                DaoGpServico daoG = new DaoGpServico();
                ResultSet resultSet = daoG.listarPorId(idGS);
                resultSet.next();
                String GS = resultSet.getString("NOME");
                int index = 0;
                for(int i = 0; i < JcbGs.getItemCount(); i++){
                    if(JcbGs.getItemAt(i).equals(GS)){
                        index = i;
                        break;
                    }
                }
                JcbGs.setSelectedIndex(index);
            }catch(Exception e){}
            //
            
            DadosTemporarios.tempObject = null;
            
                return true;
            }else 
                return false;
    }
    
     private void inserir(){
            DaoPrSr daoP = new DaoPrSr();
            
            if (daoP.inserir(Integer.parseInt(tfId.getText()), Integer.parseInt(tfIdGs.getText()), tfNome.getText(), taDesc.getText(),  Double.parseDouble(tfPreço.getText()))){
                JOptionPane.showMessageDialog(null, "Produto/serviço salvo com sucesso!");
                
                tfId.setText("" + daoP.buscarProximoId());
                tfNome.setText(" ");
                taDesc.setText(" ");
                tfPreço.setText(" ");
                
            }else{
                JOptionPane.showMessageDialog(null, "Não foi possível salvar o produto/serviço!");
            }
            }
        
        private void alterar(){
            DaoPrSr daoP = new DaoPrSr();
            
            if (daoP.alterar(Integer.parseInt(tfId.getText()), Integer.parseInt(tfIdGs.getText()), tfNome.getText(), taDesc.getText(),  Double.parseDouble(tfPreço.getText()))){
                JOptionPane.showMessageDialog(null, "Produto/serviço alterado com sucesso!");
            
                tfId.setText (" ");
                tfNome.setText(" ");
                taDesc.setText(" ");
                tfPreço.setText(" ");
                
            }else{
                JOptionPane.showMessageDialog(null, "Não foi possível alterar o produto/serviço!");
            }
            
            ((ListProdServ) Formularios.ListProdServ).listarTodos();
            
            dispose();
        }
        
        private void excluir(){
              DaoPrSr daoP = new DaoPrSr();
            
            if (daoP.excluir(Integer.parseInt(tfId.getText()))){
            JOptionPane.showMessageDialog(null, "Produto/serviço "+ tfNome.getText() +" excluído com sucesso!");
            
                tfId.setText ("");
                tfIdGs.setText("");
                tfNome.setText(" ");
                taDesc.setText(" ");
                tfPreço.setText(" ");
            }else{
                JOptionPane.showMessageDialog(null, "Não foi possível excluir o produto/serviço!");
            }
            
            ((ListProdServ) Formularios.ListProdServ).listarTodos();
            
            dispose();
        }
        
        private void carregarGrupos(){
        try{
            DaoGpServico daoG = new DaoGpServico();

            ResultSet resultSet = daoG.listarTodos();

            while(resultSet.next()){
                JcbGs.addItem(resultSet.getString("NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdGrupo(){
        try{
            DaoGpServico daoG = new DaoGpServico();
            ResultSet resultSet = daoG.listarPorNome(JcbGs.getSelectedItem().toString());
            resultSet.next();
            tfIdGs.setText(resultSet.getString("ID"));
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
        Nome = new javax.swing.JLabel();
        tfIdGs = new javax.swing.JTextField();
        JcbGs = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfPreço = new javax.swing.JTextField();
        btnAcao = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        tfId = new javax.swing.JTextField();
        LNome2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDesc = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de produto/serviço");

        jPanel3.setBackground(new java.awt.Color(153, 0, 51));

        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });

        LNome1.setText("Grupo de serviço");

        Nome.setText("Nome");

        tfIdGs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIdGsActionPerformed(evt);
            }
        });

        JcbGs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcbGsItemStateChanged(evt);
            }
        });
        JcbGs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcbGsActionPerformed(evt);
            }
        });

        jLabel10.setText("Descricao");

        jLabel11.setText("Preço ($$.$$)");

        tfPreço.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPreçoActionPerformed(evt);
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

        taDesc.setColumns(20);
        taDesc.setRows(5);
        jScrollPane1.setViewportView(taDesc);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btnExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAcao)))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(LNome2)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LNome1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(JcbGs, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfIdGs, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Nome))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(tfPreço, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 31, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LNome2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LNome1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JcbGs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdGs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nome)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPreço, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExcluir)
                    .addComponent(btnAcao))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeActionPerformed

    private void tfIdGsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIdGsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIdGsActionPerformed

    private void JcbGsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcbGsItemStateChanged
        // TODO add your handling code here:
        recuperaIdGrupo();
    }//GEN-LAST:event_JcbGsItemStateChanged

    private void JcbGsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcbGsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JcbGsActionPerformed

    private void tfPreçoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPreçoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPreçoActionPerformed

    private void btnAcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcaoActionPerformed
        // TODO add your handling code here:
        if (btnAcao.getText().equals(Constantes.BTN_SALVAR_TEXT))
        inserir();
        else if (btnAcao.getText().equals(Constantes.BTN_ALTERAR_TEXT))
        alterar();
    }//GEN-LAST:event_btnAcaoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        int escolha =
        JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o produto/serviço " + tfNome.getText() + "?");

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
            java.util.logging.Logger.getLogger(CadProdServ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadProdServ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadProdServ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadProdServ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadProdServ().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JcbGs;
    private javax.swing.JLabel LNome1;
    private javax.swing.JLabel LNome2;
    private javax.swing.JLabel Nome;
    private javax.swing.JButton btnAcao;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taDesc;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfIdGs;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPreço;
    // End of variables declaration//GEN-END:variables
}
