/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.visao.OrdServ;

import com.mycompany.dao.DaoCliente;
import com.mycompany.dao.DaoEmpresa;
import com.mycompany.dao.DaoFuncionario;
import com.mycompany.dao.DaoGpServico;
import com.mycompany.dao.DaoMarca;
import com.mycompany.dao.DaoOrdemServico;
import com.mycompany.dao.DaoPrSr;
import com.mycompany.dao.DaoVeiculo;
import com.mycompany.ferramentas.BancoDeDadosMySQL;
import com.mycompany.ferramentas.Constantes;
import com.mycompany.ferramentas.DadosTemporarios;
import com.mycompany.ferramentas.Formularios;
import com.mycompany.modelo.ModCliente;
import com.mycompany.modelo.ModMarca;
import com.mycompany.modelo.ModOrdemServico;
import com.mycompany.modelo.ModPessoa;
import com.mycompany.modelo.ModVeiculo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author brian.7908
 */
public class Ordem_de_servico extends javax.swing.JFrame {

    MaskFormatter mfFatura, mfPlaca, mfTel, mfPreUn, mfPreTot;
    
    
    /**
     * Creates new form Ordem_de_servico
     */
    public Ordem_de_servico() {
        try {
            mfPreTot = new MaskFormatter("R$##,###.##");
        } catch (ParseException ex) {
            System.out.println("Ocorreu um erro na criação da máscara de preco total!");
        }

        try {
            mfPreUn = new MaskFormatter("R$##.##");
        } catch (ParseException ex) {
            System.out.println("Ocorreu um erro na criação da máscara de preco un!");
        }

        try {
            mfTel = new MaskFormatter("(##)#####-####");
        } catch (ParseException ex) {
            System.out.println("Ocorreu um erro na criação da máscara de telefone!");
        }

        try {
            mfPlaca = new MaskFormatter("UUU-#U##");
        } catch (ParseException ex) {
            System.out.println("Ocorreu um erro na criação da máscara de placa!");
        }

        try {
            mfFatura = new MaskFormatter("##/##/####");
        } catch (ParseException ex) {
            System.out.println("Ocorreu um erro na criação da máscara de fatura!");
        }
        
           initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        BancoDeDadosMySQL.ExclusaoAutomatica();
    
        CarregarAll();
        
        jcbProdS.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                carregarGrupoParaProdutoSelecionado();
            }
            });
        
        if (!existeDadosTemporarios()){
            DaoOrdemServico daoO = new DaoOrdemServico();
            DaoVeiculo daoV = new DaoVeiculo();
            
            int id = daoO.buscarProximoId();
            int idV = daoV.buscarProximoId();
            
            if(id>0){
                tfId.setText(String.valueOf(id));
                tfIdVeiculo.setText(String.valueOf(idV));
            }
            btnAcao.setText(Constantes.BTN_SALVAR_TEXT);
            }else{
            btnAcao.setText(Constantes.BTN_ALTERAR_TEXT);
        }
        setLocationRelativeTo(null);
        
        tfId.setVisible(false);
        tfIdProdS.setVisible(false);
        tfIdEmpresa.setVisible(false);
        tfIdGrupo.setVisible(false);
        tfIdResponsavel.setVisible(false);
        tfIdVeiculo.setVisible(false);
        tfIdProdS.setVisible(false);
        tfIdExServ.setVisible(false);
        tfIdMV.setVisible(false);
        tfIdCliente.setVisible(false);
        jcbGrupo.setEnabled(false);
    }
    
    private Boolean existeDadosTemporarios(){
        if(DadosTemporarios.tempObject instanceof ModOrdemServico){
            int id = ((ModOrdemServico) DadosTemporarios.tempObject).getId();
            int idEmp = ((ModOrdemServico) DadosTemporarios.tempObject).getIdEmp();
            int idVe = ((ModOrdemServico) DadosTemporarios.tempObject).getIdVeic();
            int idCli = ((ModOrdemServico) DadosTemporarios.tempObject).getIdCliente();
            int idProd = ((ModOrdemServico) DadosTemporarios.tempObject).getIdProdSer();
            int idGs = ((ModOrdemServico) DadosTemporarios.tempObject).getIdGs();
            int idFunc = ((ModOrdemServico) DadosTemporarios.tempObject).getIdFuncionario();
            String Obs =  ((ModOrdemServico) DadosTemporarios.tempObject).getObs();
            String date = ((ModOrdemServico) DadosTemporarios.tempObject).getData();
            
            tfId.setText(String.valueOf(id));
            tfIdEmpresa.setText(String.valueOf(idEmp));
            tfIdVeiculo.setText(String.valueOf(idVe));
            tfIdCliente.setText(String.valueOf(idCli));
            tfIdProdS.setText(String.valueOf(idProd));
            tfIdResponsavel.setText(String.valueOf(idFunc));
            tfIdExServ.setText(String.valueOf(idFunc));
            taDescCli.setText(String.valueOf(Obs));
            tfIdGrupo.setText(String.valueOf(idGs));
            ftExpira.setText(date);
              //
            try{
                DaoEmpresa daoEmp = new DaoEmpresa();
                ResultSet resultSet = daoEmp.listarPorId(idEmp);
                resultSet.next();
                String Emp = resultSet.getString("NOME");
                int index = 0;
                for(int i = 0; i < jcbEmpresa.getItemCount(); i++){
                    if(jcbEmpresa.getItemAt(i).equals(Emp)){
                        index = i;
                        break;
                    }
                } 
                jcbEmpresa.setSelectedIndex(index);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            //
            
              //
            try{
                DaoVeiculo daoV = new DaoVeiculo();
                ResultSet resultSet = daoV.listarPorId(idVe);
                resultSet.next();
                String MVeiculo = resultSet.getString("M.NOME");
                int index = 0;
                for(int i = 0; i < jcbMarcaVeiculo.getItemCount(); i++){
                    if(jcbMarcaVeiculo.getItemAt(i).equals(MVeiculo)){
                        index = i;
                        break;
                    }
                } 
                jcbMarcaVeiculo.setSelectedIndex(index);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            //
            
              //
            try{
                DaoCliente daoC = new DaoCliente();
                ResultSet resultSet = daoC.listarPorId(idCli);
                resultSet.next();
                String Cli = resultSet.getString("P.NOME");
                int index = 0;
                for(int i = 0; i < jcbCliente.getItemCount(); i++){
                    if(jcbCliente.getItemAt(i).equals(Cli)){
                        index = i;
                        break;
                    }
                } 
                jcbCliente.setSelectedIndex(index);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            //
            
              //
            try{
                DaoFuncionario daoF = new DaoFuncionario();
                ResultSet resultSet = daoF.listarPorId(idFunc);
                resultSet.next();
                String Fun = resultSet.getString("P.NOME");
                int index = 0;
                for(int i = 0; i < jcbResponsavel.getItemCount(); i++){
                    if(jcbResponsavel.getItemAt(i).equals(Fun)){
                        index = i;
                        break;
                    }
                } 
                jcbResponsavel.setSelectedIndex(index);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            //
            
              //
            try{
                DaoFuncionario daoF = new DaoFuncionario();
                ResultSet resultSet = daoF.listarPorId(idFunc);
                resultSet.next();
                String Fun = resultSet.getString("P.NOME");
                int index = 0;
                for(int i = 0; i < jcbExecutor.getItemCount(); i++){
                    if(jcbExecutor.getItemAt(i).equals(Fun)){
                        index = i;
                        break;
                    }
                } 
                jcbExecutor.setSelectedIndex(index);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            //
            
              //
            try{
                DaoPrSr daoP = new DaoPrSr();
                ResultSet resultSet = daoP.listarPorId(idProd);
                resultSet.next();
                String Ps = resultSet.getString("P.NOME");
                int index = 0;
                for(int i = 0; i < jcbProdS.getItemCount(); i++){
                    if(jcbProdS.getItemAt(i).equals(Ps)){
                        index = i;
                        break;
                    }
                } 
                jcbProdS.setSelectedIndex(index);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            //
            
            //
            int idV = ((ModVeiculo)DadosTemporarios.tempObject1).getId();
            String veic = ((ModVeiculo) DadosTemporarios.tempObject1).getNome();
            String placa = ((ModVeiculo) DadosTemporarios.tempObject1).getPlaca();
            int ano = ((ModVeiculo) DadosTemporarios.tempObject1).getAno();
            tfIdVeiculo.setText(String.valueOf(idV));
            tfVeiculo.setText(veic);
            ftPlaca.setText(placa);
            tfAno.setText(String.valueOf(ano));
            //
            
            //
            int idCliente = ((ModCliente) DadosTemporarios.tempObject2).getId();
            tfIdCliente.setText(String.valueOf(idCliente));
            //
            
            //
            String telefone = ((ModPessoa) DadosTemporarios.tempObject3).getTel();
            ftTelefone.setText(String.valueOf(telefone));
            //
            
            //
            int idMarca = ((ModMarca) DadosTemporarios.tempObject4).getId();
            tfIdMV.setText(String.valueOf(idMarca));
            //
            
            DadosTemporarios.tempObject = null;
            DadosTemporarios.tempObject1 = null;
            DadosTemporarios.tempObject2 = null;
            DadosTemporarios.tempObject3 = null;
            DadosTemporarios.tempObject4 = null;
            
                return true;
        }else
                return false;
    }
    
    private void carregarGrupoParaProdutoSelecionado() {
        
    String produtoSelecionado = jcbProdS.getSelectedItem().toString();

    DaoOrdemServico daoO = new DaoOrdemServico();
    DaoGpServico daoG = new DaoGpServico();

    String grupo = daoO.obterGrupoDoBancoDeDados(produtoSelecionado);

    if (grupo != null) {
        jcbGrupo.setSelectedItem(grupo);
        tfIdGrupo.setText(daoO.obterIdDoGrupoDoBancoDeDados(grupo));
    } else {
        jcbGrupo.setSelectedItem(null);
        tfIdGrupo.setText(daoO.obterIdDoGrupoDoBancoDeDados(grupo));
    }
}

   private String conversaoData(String dataDigitada) {
    String stringData = "";

    try {
        DateFormat dfEntrada = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date utilDate = dfEntrada.parse(dataDigitada);

        DateFormat dfSaida = new SimpleDateFormat("yyyy-MM-dd");
        stringData = dfSaida.format(utilDate);

        // Você pode imprimir para verificar se a conversão está correta
//        System.out.println(stringData);
    } catch (ParseException e) {
        e.printStackTrace();
        // Trate a exceção ou informe ao usuário que a data está em formato incorreto
    }

    return stringData;
}
   
    private void inserir(){
        DaoOrdemServico daoO = new DaoOrdemServico();
        
         String stringData = conversaoData(ftExpira.getText());
        
        if(daoO.inserir(Integer.parseInt(tfId.getText()), Integer.parseInt(tfIdEmpresa.getText()), Integer.parseInt(tfIdVeiculo.getText()), Integer.parseInt(tfIdCliente.getText()), Integer.parseInt(tfIdGrupo.getText()), Integer.parseInt(tfIdResponsavel.getText()), taDescCli.getText(), stringData)){
        JOptionPane.showMessageDialog(null, "Dados da ordem de serviço adicionados ao banco de dados!");
        
        taDescCli.setText("");
    }else {
    JOptionPane.showMessageDialog(null, "Não foi possível salvar os dados!");
}
    }
    
    private void inserirVeiculo(){
        DaoVeiculo daoV = new DaoVeiculo();
        
        if(daoV.inserir(Integer.parseInt(tfIdVeiculo.getText()), Integer.parseInt(tfIdMV.getText()), tfVeiculo.getText(), ftPlaca.getText(), Integer.parseInt(tfAno.getText()))){
            JOptionPane.showMessageDialog(null, "Veículo salvo com sucesso!");
            
            tfVeiculo.setText("");
            ftPlaca.setText("");
            tfAno.setText("");
            
        }else {
            JOptionPane.showMessageDialog(null, "Não foi possível salvar o veículo!");
        }
    }
    
    private void alterar(){
        DaoOrdemServico daoO = new DaoOrdemServico();
        
        String stringData = conversaoData(ftExpira.getText());
        
        if(daoO.alterar(Integer.parseInt(tfId.getText()), Integer.parseInt(tfIdEmpresa.getText()), Integer.parseInt(tfIdVeiculo.getText()), Integer.parseInt(tfIdCliente.getText()), Integer.parseInt(tfIdGrupo.getText()), Integer.parseInt(tfIdResponsavel.getText()), taDescCli.getText(), stringData)){
              JOptionPane.showMessageDialog(null, "Ordem de serviço alterada!");
              
            taDescCli.setText("");
            
        }else{
        JOptionPane.showMessageDialog(null, "Não foi possível alterar a ordem de serviço!");
        }
        
//        ((List_OS) Formularios.List_OS).listarTodos();
        
        dispose();
    }
    
    private void alterarVeiculo(){
        DaoVeiculo daoV = new DaoVeiculo();
        
        if(daoV.alterar(Integer.parseInt(tfIdVeiculo.getText()), Integer.parseInt(tfIdMV.getText()), tfVeiculo.getText(), ftPlaca.getText(), Integer.parseInt(tfAno.getText()))){
            JOptionPane.showMessageDialog(null, "Veículo alterado com sucesso!");
            
            tfVeiculo.setText("");
            ftPlaca.setText("");
            tfAno.setText("");
            
        }else {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o veículo!");
        }
    }
    
    private void carregarEmpresas(){
        try{
            DaoEmpresa daoE = new DaoEmpresa();

            ResultSet resultSet = daoE.listarTodos();

            while(resultSet.next()){
                jcbEmpresa.addItem(resultSet.getString("NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdEmp(){
        try{
            DaoEmpresa daoE = new DaoEmpresa();
            ResultSet resultSet = daoE.listarPorNome(jcbEmpresa.getSelectedItem().toString());
            resultSet.next();
            tfIdEmpresa.setText(resultSet.getString("ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
    private void carregarMarcas(){
        try{
            DaoMarca daoM = new DaoMarca();

            ResultSet resultSet = daoM.listarTodos();

            while(resultSet.next()){
                jcbMarcaVeiculo.addItem(resultSet.getString("NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdMarca(){
        try{
            DaoMarca daoM = new DaoMarca();
            ResultSet resultSet = daoM.listarPorNome(jcbMarcaVeiculo.getSelectedItem().toString());
            resultSet.next();
            tfIdMV.setText(resultSet.getString("ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
    private void carregarClientes(){
        try{
            DaoCliente daoC = new DaoCliente();

            ResultSet resultSet = daoC.listarTodos();

            while(resultSet.next()){
                jcbCliente.addItem(resultSet.getString("P.NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdCliente(){
        try{
            DaoCliente daoC = new DaoCliente();
            ResultSet resultSet = daoC.listarPorNome(jcbCliente.getSelectedItem().toString());
            resultSet.next();
            tfIdCliente.setText(resultSet.getString("C.ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
    private void carregarResp(){
        try{
            DaoFuncionario daoF = new DaoFuncionario();

            ResultSet resultSet = daoF.listarTodos();

            while(resultSet.next()){
                jcbResponsavel.addItem(resultSet.getString("P.NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdResponsavel(){
        try{
            DaoFuncionario daoF = new DaoFuncionario();
            ResultSet resultSet = daoF.listarPorNome(jcbResponsavel.getSelectedItem().toString());
            resultSet.next();
            tfIdResponsavel.setText(resultSet.getString("FR.ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
    private void carregarGrupos(){
        try{
            DaoGpServico daoG = new DaoGpServico();

            ResultSet resultSet = daoG.listarTodos();

            while(resultSet.next()){
                jcbGrupo.addItem(resultSet.getString("NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdGrupo(){
        try{
            DaoGpServico daoG = new DaoGpServico();
            ResultSet resultSet = daoG.listarPorNome(jcbGrupo.getSelectedItem().toString());
            resultSet.next();
            tfIdGrupo.setText(resultSet.getString("ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
    private void carregarExec(){
        try{
            DaoFuncionario daoF = new DaoFuncionario();

            ResultSet resultSet = daoF.listarTodos();

            while(resultSet.next()){
                jcbExecutor.addItem(resultSet.getString("P.NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdExec(){
        try{
            DaoFuncionario daoF = new DaoFuncionario();
            ResultSet resultSet = daoF.listarPorNome(jcbExecutor.getSelectedItem().toString());
            resultSet.next();
            tfIdExServ.setText(resultSet.getString("FR.ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
        
    private void carregarProdS(){
        try{
            DaoPrSr daoP = new DaoPrSr();

            ResultSet resultSet = daoP.listarTodos();

            while(resultSet.next()){
                jcbProdS.addItem(resultSet.getString("P.NOME"));
            }
        }catch(Exception e){
            
        }
    }
        
        private void recuperaIdProd(){
        try{
            DaoPrSr daoP = new DaoPrSr();
            ResultSet resultSet = daoP.listarPorNome(jcbProdS.getSelectedItem().toString());
            resultSet.next();
            tfIdProdS.setText(resultSet.getString("P.ID"));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
        private boolean camposObrigatoriosPreenchidos(JTextField campos[]){
            boolean b = true;
            
            for (int i = 0; i < campos.length; i++) {
                if(campos[i].getText().equals("")){
                JOptionPane.showMessageDialog(null, "O campo " + campos[i].getToolTipText() + " é obrigatório!");
                campos[i].requestFocus();
                b = false;
                break;
                }
            }
            return b;
        }
        
        private void CarregarAll(){
            
        carregarEmpresas();
        carregarClientes();
        carregarMarcas();
        carregarResp();
        carregarGrupos();
        carregarExec();
        carregarProdS();
        
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
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jcbEmpresa = new javax.swing.JComboBox<>();
        tfId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tfAno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcbMarcaVeiculo = new javax.swing.JComboBox<>();
        tfIdEmpresa = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jcbResponsavel = new javax.swing.JComboBox<>();
        tfIdResponsavel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taDescCli = new javax.swing.JTextArea();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfIdProdS = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tfQnt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jcbGrupo = new javax.swing.JComboBox<>();
        jcbExecutor = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfDesc1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tfIdGrupo = new javax.swing.JTextField();
        tfIdExServ = new javax.swing.JTextField();
        ftPreUn = new JFormattedTextField(mfPreUn);
        ftTotal = new JFormattedTextField(mfPreTot);
        jcbProdS = new javax.swing.JComboBox<>();
        ftPreUn1 = new JFormattedTextField(mfPreTot);
        tfIdVeiculo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btnAcao = new javax.swing.JButton();
        jcbCliente = new javax.swing.JComboBox<>();
        tfIdCliente = new javax.swing.JTextField();
        ftPlaca = new JFormattedTextField(mfPlaca);
        ftTelefone = new JFormattedTextField(mfTel);
        ftFatura1 = new JFormattedTextField(mfFatura);
        ftFatura2 = new JFormattedTextField(mfFatura);
        jLabel25 = new javax.swing.JLabel();
        tfVeiculo = new javax.swing.JTextField();
        tfIdMV = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        ftExpira = new JFormattedTextField(mfFatura);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de serviço");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Dados da ordem de serviço");

        jcbEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbEmpresa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbEmpresaItemStateChanged(evt);
            }
        });
        jcbEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbEmpresaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Empresa");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("ID");

        tfAno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("Faturamento:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Placa");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Marca do veículo");

        jcbMarcaVeiculo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbMarcaVeiculo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbMarcaVeiculoItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Cliente");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Ano");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Telefone");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Responsável");

        jcbResponsavel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbResponsavel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbResponsavelItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("Observação");

        taDescCli.setColumns(20);
        taDescCli.setRows(5);
        jScrollPane1.setViewportView(taDescCli);

        jTabbedPane1.setBackground(java.awt.Color.white);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("ID");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Adição de Produto e Serviços");

        tfIdProdS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Produto/Serviço");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Qnt");

        tfQnt.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("$ Unitário");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("$ Total");

        jcbGrupo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbGrupo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbGrupoItemStateChanged(evt);
            }
        });

        jcbExecutor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbExecutor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbExecutorItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Grupo");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Executor de serviço");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setText("Complemento");

        tfDesc1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Incluir item na O.S");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel22.setText("Total da O.S");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Produtos e serviços");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Referência", "Descrição", "Qnt", "R$ Unitário", "R$ Total", "Executor", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(8).setPreferredWidth(5);
        }

        ftPreUn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ftTotal.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        jcbProdS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbProdS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbProdSItemStateChanged(evt);
            }
        });

        ftPreUn1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfIdGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(332, 332, 332))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfIdProdS, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jcbProdS, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tfQnt, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ftPreUn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(74, 74, 74)
                                        .addComponent(jLabel17)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(ftPreUn1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbExecutor, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tfIdExServ, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel20))))
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 472, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDesc1, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfIdGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdExServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIdProdS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfQnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbExecutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftPreUn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbProdS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftPreUn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDesc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(ftTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Produtos e serviços", jPanel2);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Início");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("Fim");

        btnAcao.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcaoActionPerformed(evt);
            }
        });

        jcbCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jcbCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbClienteItemStateChanged(evt);
            }
        });

        ftPlaca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ftTelefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ftFatura1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        ftFatura2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText("Veículo");

        tfVeiculo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel26.setText("Data de expiração do serviço");

        ftExpira.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(tfIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftFatura1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftFatura2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfIdMV, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jcbMarcaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfAno, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftExpira, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfIdResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAcao, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jcbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ftTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(ftExpira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jcbResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(tfIdEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel24)
                    .addComponent(ftFatura1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftFatura2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdMV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jcbMarcaVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tfAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(tfVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jcbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ftTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnAcao, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        Formularios.Ordem_de_servico = null;
    }//GEN-LAST:event_formWindowClosed

    private void jcbClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbClienteItemStateChanged
        // TODO add your handling code here:
        recuperaIdCliente();
    }//GEN-LAST:event_jcbClienteItemStateChanged

    private void btnAcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcaoActionPerformed
        // TODO add your handling code here:
        DaoOrdemServico daoO = new DaoOrdemServico();
        DaoVeiculo daoV = new DaoVeiculo();

        if(camposObrigatoriosPreenchidos(new JTextField[]{tfVeiculo, tfAno, ftExpira, ftFatura1, ftFatura2, ftPlaca})){
            if(btnAcao.getText() == Constantes.BTN_SALVAR_TEXT){
                inserirVeiculo();
                inserir();

                tfId.setText(String.valueOf(daoO.buscarProximoId()));
                tfIdVeiculo.setText(String.valueOf(daoV.buscarProximoId()));

            }else if (btnAcao.getText().equals(Constantes.BTN_ALTERAR_TEXT)){
                alterar();
                alterarVeiculo();
            }
        }
    }//GEN-LAST:event_btnAcaoActionPerformed

    private void jcbExecutorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbExecutorItemStateChanged
        // TODO add your handling code here:
        recuperaIdExec();
    }//GEN-LAST:event_jcbExecutorItemStateChanged

    private void jcbGrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbGrupoItemStateChanged
        // TODO add your handling code here:
        recuperaIdGrupo();
    }//GEN-LAST:event_jcbGrupoItemStateChanged

    private void jcbResponsavelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbResponsavelItemStateChanged
        // TODO add your handling code here:
        recuperaIdResponsavel();
    }//GEN-LAST:event_jcbResponsavelItemStateChanged

    private void jcbMarcaVeiculoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbMarcaVeiculoItemStateChanged
        // TODO add your handling code here:
        recuperaIdMarca();
    }//GEN-LAST:event_jcbMarcaVeiculoItemStateChanged

    private void jcbEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbEmpresaActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jcbEmpresaActionPerformed

    private void jcbEmpresaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbEmpresaItemStateChanged
        // TODO add your handling code here:
        recuperaIdEmp();
    }//GEN-LAST:event_jcbEmpresaItemStateChanged

    private void jcbProdSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbProdSItemStateChanged
        // TODO add your handling code here:
        recuperaIdProd();
    }//GEN-LAST:event_jcbProdSItemStateChanged

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
            java.util.logging.Logger.getLogger(Ordem_de_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ordem_de_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ordem_de_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ordem_de_servico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ordem_de_servico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcao;
    private javax.swing.JFormattedTextField ftExpira;
    private javax.swing.JFormattedTextField ftFatura1;
    private javax.swing.JFormattedTextField ftFatura2;
    private javax.swing.JFormattedTextField ftPlaca;
    private javax.swing.JFormattedTextField ftPreUn;
    private javax.swing.JFormattedTextField ftPreUn1;
    private javax.swing.JFormattedTextField ftTelefone;
    private javax.swing.JFormattedTextField ftTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcbCliente;
    private javax.swing.JComboBox<String> jcbEmpresa;
    private javax.swing.JComboBox<String> jcbExecutor;
    private javax.swing.JComboBox<String> jcbGrupo;
    private javax.swing.JComboBox<String> jcbMarcaVeiculo;
    private javax.swing.JComboBox<String> jcbProdS;
    private javax.swing.JComboBox<String> jcbResponsavel;
    private javax.swing.JTextArea taDescCli;
    private javax.swing.JTextField tfAno;
    private javax.swing.JTextField tfDesc1;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfIdCliente;
    private javax.swing.JTextField tfIdEmpresa;
    private javax.swing.JTextField tfIdExServ;
    private javax.swing.JTextField tfIdGrupo;
    private javax.swing.JTextField tfIdMV;
    private javax.swing.JTextField tfIdProdS;
    private javax.swing.JTextField tfIdResponsavel;
    private javax.swing.JTextField tfIdVeiculo;
    private javax.swing.JTextField tfQnt;
    private javax.swing.JTextField tfVeiculo;
    // End of variables declaration//GEN-END:variables

}
