/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.visao.pessoa;

import com.mycompany.dao.DaoCliente;
import com.mycompany.dao.DaoEndereco;
import com.mycompany.dao.DaoPessoa;
import com.mycompany.ferramentas.DadosTemporarios;
import com.mycompany.modelo.ModCliente;
import com.mycompany.modelo.ModEndereco;
import com.mycompany.modelo.ModPessoa;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brian.7908
 */
public class ListPessoa extends javax.swing.JFrame {

    /**
     * Creates new form ListPessoa
     */
    public ListPessoa() {
        initComponents();
        
        setLocationRelativeTo(null);
        
        listarTodos();
    }

    public void listarTodos(){
         try{
             DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
             tablePessoa.setModel(dtm);
            
             DaoPessoa daopes = new DaoPessoa();
            
             ResultSet resultset = daopes.listarTodos();
            
            dtm.setRowCount(0);
            
            while (resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
     public void listarPorId(){
        try{
            DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
             tablePessoa.setModel(dtm);
            
             DaoPessoa daopes = new DaoPessoa();
            
            ResultSet resultset = daopes.listarPorId(Integer.parseInt(tfFiltro.getText()));
            
            dtm.setRowCount(0);
            
            while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        }
     
     public void listarPorRua(){
                    try{
                        DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
                        tablePessoa.setModel(dtm);
            
                        DaoPessoa daopes = new DaoPessoa();
                        
                        ResultSet resultset = daopes.listarPorRua(tfFiltro.getText());
                        
                        dtm.setRowCount(0);
                    
                        while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);

                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
                        }
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
     
     public void listarPorCEP(){
                    try{
                        DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
                        tablePessoa.setModel(dtm);
            
                        DaoPessoa daopes = new DaoPessoa();
                        
                        ResultSet resultset = daopes.listarPorCEP(tfFiltro.getText());
                        
                        dtm.setRowCount(0);
                    
                        while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
                        }
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
     
     public void listarPorNumR(){
                    try{
                        DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
                        tablePessoa.setModel(dtm);
            
                        DaoPessoa daopes = new DaoPessoa();
                        
                        ResultSet resultset = daopes.listarPorNumResid(tfFiltro.getText());
                        
                        dtm.setRowCount(0);
                    
                        while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
                        }
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }
     
     public void listarPorNome(){
        try{
            DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
                        tablePessoa.setModel(dtm);
            
                        DaoPessoa daopes = new DaoPessoa();
            
            ResultSet resultset = daopes.listarPorNome(tfFiltro.getText());
            
            dtm.setRowCount(0);
            
               while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        }
        
          public void listarPorSob(){
        try{
            DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
                        tablePessoa.setModel(dtm);
            
                        DaoPessoa daopes = new DaoPessoa();
            
            ResultSet resultset = daopes.listarPorSob(tfFiltro.getText());
            
            dtm.setRowCount(0);
            
           while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        }
          
          public void listarPorGen(){
        try{
            DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
                        tablePessoa.setModel(dtm);
            
                        DaoPessoa daopes = new DaoPessoa();
            
            ResultSet resultset = daopes.listarPorGen(tfFiltro.getText());
            
            dtm.setRowCount(0);
            
            while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        }
          public void listarPorTel(){
        try{
            DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
                        tablePessoa.setModel(dtm);
            
                        DaoPessoa daopes = new DaoPessoa();
            
            ResultSet resultset = daopes.listarPorTel(tfFiltro.getText());
            
            dtm.setRowCount(0);
            
            while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        }
          public void listarPorEma(){
        try{
            DefaultTableModel dtm = (DefaultTableModel) tablePessoa.getModel();
            
                        tablePessoa.setModel(dtm);
            
                        DaoPessoa daopes = new DaoPessoa();
            
            ResultSet resultset = daopes.listarPorEmail(tfFiltro.getText());
            
            dtm.setRowCount(0);
            
            while(resultset.next()){
                String id = resultset.getString(1);
                String cidade = resultset.getString(2);
                String rua = resultset.getString(3);
                String cep = resultset.getString(4);
                String numr = resultset.getString(5);
                String nome = resultset.getString(6);
                String sob = resultset.getString(7);
                String gen = resultset.getString(8);
                String tel = resultset.getString(9);
                String ema = resultset.getString(10);
                
                dtm.addRow(new Object[] {id, cidade, rua, cep, numr, nome, sob, gen, tel, ema});
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
        tablePessoa = new javax.swing.JTable();
        jcbTipoFiltro = new javax.swing.JComboBox<>();
        tfFiltro = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listagem de pessoas");

        jPanel1.setBackground(new java.awt.Color(153, 0, 51));

        tablePessoa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Cidade", "Rua", "CEP", "Núm. Res.", "Nome", "Sobrenome", "Gênero", "Telefone", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePessoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePessoaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablePessoa);

        jcbTipoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "ID", "Rua", "CEP", "Núm. Resid.", "Nome", "Sobrenome", "Gênero", "Telefone", "E-mail" }));
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1164, Short.MAX_VALUE)
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

    private void tablePessoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePessoaMouseClicked
        try{
            if (evt.getClickCount() == 2){
                ModPessoa modpes = new ModPessoa ();

                modpes.setId(Integer.parseInt(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 0))));
                modpes.setNm(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 5)));
                modpes.setSb(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 6)));
                modpes.setGen(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 7)));
                modpes.setTel(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 8)));
                modpes.setEma(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 9)));

                //
                DaoEndereco daoend = new DaoEndereco();
                //                ResultSet resultset = daoend.listarPorId(Integer.parseInt(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 0).toString())));
                ResultSet resultset = daoend.listarPorId(Integer.parseInt(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 0).toString()));

                int idEnd = -1;

                while(resultset.next())
                idEnd = resultset.getInt("ID");

                modpes.setIdEnd(idEnd);
                //

                //
                ModEndereco modEndereco = new ModEndereco();

                modEndereco.setRua(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 2)));
                modEndereco.setCEP(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 3)));
                modEndereco.setNumResid(Integer.parseInt(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 4))));
                //

                //
                DaoCliente daoCli = new DaoCliente();
                resultset = daoCli.listarPorIdPessoa(Integer.parseInt(String.valueOf(tablePessoa.getValueAt(tablePessoa.getSelectedRow(), 0))));

                ModCliente modCli = new ModCliente();

                int idCli = -1;
                while(resultset.next())
                idCli = resultset.getInt("ID");

                modCli.setId(idCli);
                //

                DadosTemporarios.tempObject = (ModPessoa) modpes;
                DadosTemporarios.tempObject1 = (ModEndereco) modEndereco;
                DadosTemporarios.tempObject2 = (ModCliente) modCli;

                CadPessoa cadpes = new CadPessoa();
                cadpes.setVisible(true);
            }
        }   catch (Exception e){
            System.err.println(e.getMessage());
        }
    }//GEN-LAST:event_tablePessoaMouseClicked

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
            listarPorId();
            break;
            case 2:
            listarPorRua();
            break;
            case 3:
            listarPorCEP();
            break;
            case 4:
            listarPorNumR();
            break;
            case 5:
            listarPorNome();
            break;
            case 6:
            listarPorSob();
            break;
            case 7:
            listarPorGen();
            break;
            case 8:
            listarPorTel();
            break;
            case 9:
            listarPorEma();
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
            java.util.logging.Logger.getLogger(ListPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListPessoa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbTipoFiltro;
    private javax.swing.JTable tablePessoa;
    private javax.swing.JTextField tfFiltro;
    // End of variables declaration//GEN-END:variables
}
