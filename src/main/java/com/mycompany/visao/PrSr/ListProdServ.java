/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.visao.PrSr;

import com.mycompany.dao.DaoGpServico;
import com.mycompany.dao.DaoPrSr;
import com.mycompany.ferramentas.DadosTemporarios;
import com.mycompany.modelo.ModPrSr;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brian.7908
 */
public class ListProdServ extends javax.swing.JFrame {

    /**
     * Creates new form ListProdServ
     */
    public ListProdServ() {
        initComponents();
        
        setLocationRelativeTo(null);
        
        listarTodos();
    }

    public void listarTodos(){
        try{
             DefaultTableModel dtm = (DefaultTableModel) tableProdServ.getModel();
            
             tableProdServ.setModel(dtm);
            
             DaoPrSr daoP = new DaoPrSr();
            
             ResultSet resultset = daoP.listarTodos();
            
            dtm.setRowCount(0);
            
            while (resultset.next()){
                String id = resultset.getString(1);
                String gp = resultset.getString(2);
                String nm = resultset.getString(3);
                String desc = resultset.getString(4);
                String val = resultset.getString(5);
                
                dtm.addRow(new Object[] {id, gp, nm, desc, val});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void listarPorId(int pId){
        try{
            DefaultTableModel dtm = (DefaultTableModel) tableProdServ.getModel();
            
             tableProdServ.setModel(dtm);
            
             DaoPrSr daoP = new DaoPrSr();
            
             ResultSet resultset = daoP.listarPorId(pId);
            
            dtm.setRowCount(0);
            
            while (resultset.next()){
                String id = resultset.getString(1);
                String gp = resultset.getString(2);
                String nm = resultset.getString(3);
                String desc = resultset.getString(4);
                String val = resultset.getString(5);
                
                dtm.addRow(new Object[] {id, gp, nm, desc, val});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        }
    
    public void listarPorGrupo(String pGp){
        try{
             DefaultTableModel dtm = (DefaultTableModel) tableProdServ.getModel();
            
             tableProdServ.setModel(dtm);
            
             DaoPrSr daoP = new DaoPrSr();
            
             ResultSet resultset = daoP.listarPorGrupo(pGp);
            
            dtm.setRowCount(0);
            
            while (resultset.next()){
                String id = resultset.getString(1);
                String gp = resultset.getString(2);
                String nm = resultset.getString(3);
                String desc = resultset.getString(4);
                String val = resultset.getString(5);
                
                dtm.addRow(new Object[] {id, gp, nm, desc, val});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void listarPorNome(String pNome){
        try{
             DefaultTableModel dtm = (DefaultTableModel) tableProdServ.getModel();
            
             tableProdServ.setModel(dtm);
            
             DaoPrSr daoP = new DaoPrSr();
            
             ResultSet resultset = daoP.listarPorNome(pNome);
            
            dtm.setRowCount(0);
            
            while (resultset.next()){
                String id = resultset.getString(1);
                String gp = resultset.getString(2);
                String nm = resultset.getString(3);
                String desc = resultset.getString(4);
                String val = resultset.getString(5);
                
                dtm.addRow(new Object[] {id, gp, nm, desc, val});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void listarPorDesc(String pdesc){
    try{
             DefaultTableModel dtm = (DefaultTableModel) tableProdServ.getModel();
            
             tableProdServ.setModel(dtm);
            
             DaoPrSr daoP = new DaoPrSr();
            
             ResultSet resultset = daoP.listarPorDescricao(pdesc);
            
            dtm.setRowCount(0);
            
            while (resultset.next()){
                String id = resultset.getString(1);
                String gp = resultset.getString(2);
                String nm = resultset.getString(3);
                String desc = resultset.getString(4);
                String val = resultset.getString(5);
                
                dtm.addRow(new Object[] {id, gp, nm, desc, val});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void listarPorPreco(Double pPreco){
    try{
             DefaultTableModel dtm = (DefaultTableModel) tableProdServ.getModel();
            
             tableProdServ.setModel(dtm);
            
             DaoPrSr daoP = new DaoPrSr();
            
             ResultSet resultset = daoP.listarPorPreco(String.valueOf(pPreco));
            
            dtm.setRowCount(0);
            
            while (resultset.next()){
                String id = resultset.getString(1);
                String gp = resultset.getString(2);
                String nm = resultset.getString(3);
                String desc = resultset.getString(4);
                String val = resultset.getString(5);
                
                dtm.addRow(new Object[] {id, gp, nm, desc, val});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProdServ = new javax.swing.JTable();
        jcbTipoFiltro = new javax.swing.JComboBox<>();
        tfFiltro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listagem de produtos/serviços");

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));

        tableProdServ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Grupo de serviço", "Produto/serviço", "Descrição", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProdServ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProdServMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProdServ);

        jcbTipoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "ID", "Grupo de serviço", "Produto/serviço", "Descrição", "Preço" }));
        jcbTipoFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTipoFiltroActionPerformed(evt);
            }
        });

        tfFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFiltroActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jcbTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfFiltro))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBuscar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addContainerGap())
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

    private void tableProdServMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProdServMouseClicked
        try{
            if (evt.getClickCount() == 2){
                ModPrSr modP = new ModPrSr ();

                modP.setId(Integer.parseInt(String.valueOf(tableProdServ.getValueAt(tableProdServ.getSelectedRow(), 0))));
                modP.setNome(String.valueOf(tableProdServ.getValueAt(tableProdServ.getSelectedRow(), 2)));
                modP.setDescricao(String.valueOf(tableProdServ.getValueAt(tableProdServ.getSelectedRow(), 3)));
                modP.setPreco(Double.valueOf(String.valueOf(tableProdServ.getValueAt(tableProdServ.getSelectedRow(), 4))));

                DaoGpServico daoG = new DaoGpServico();
                ResultSet resultset = daoG.listarPorNome(String.valueOf(tableProdServ.getValueAt(tableProdServ.getSelectedRow(), 1)));

                int idGs = -1;

                while(resultset.next())
                idGs = resultset.getInt("ID");

                modP.setIdGrupo(idGs);

                DadosTemporarios.tempObject = (ModPrSr) modP;

                CadProdServ cadP = new CadProdServ();
                cadP.setVisible(true);
            }
        }   catch (Exception e){
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_tableProdServMouseClicked

    private void jcbTipoFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTipoFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbTipoFiltroActionPerformed

    private void tfFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFiltroActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        switch (jcbTipoFiltro.getSelectedIndex()){
            case 0:
            listarTodos();
            break;
            case 1:
            listarPorId(Integer.parseInt(tfFiltro.getText()));
            break;
            case 2:
            listarPorGrupo(tfFiltro.getText());
            break;
            case 3:
            listarPorNome(tfFiltro.getText());
            break;
            case 4:
            listarPorDesc(tfFiltro.getText());
            break;
            case 5:
            listarPorPreco(Double.valueOf(tfFiltro.getText()));
            break;
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

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
            java.util.logging.Logger.getLogger(ListProdServ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListProdServ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListProdServ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListProdServ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListProdServ().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbTipoFiltro;
    private javax.swing.JTable tableProdServ;
    private javax.swing.JTextField tfFiltro;
    // End of variables declaration//GEN-END:variables
}
