/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ferramentas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author brian.7908
 */
public class BancoDeDadosMySQL {
        private static final String URL =
        "jdbc:mysql://localhost:3306/Banco_de_Dados_Automotiva?useSSL=false&allowPublicKeyRetrieval=true";
        
        private static final String USUARIO = "root";
        private static final String SENHA = "admin";
        
         private static Connection conexao = null;
            private static PreparedStatement statement = null;
            private static ResultSet resultado = null;
            
            public static Connection obterConexao() {
                conexao = null;
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    
                    conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
                } catch (ClassNotFoundException e){
                    System.out.println("Erro ao carregar o driver JDBC " + e.getMessage());
                } catch (SQLException e) {
                    System.err.println("Erro ao obter a conexão com o banco de dados: " + e.getMessage());
                }
                  return conexao;
            }
            
            public static void ExclusaoAutomatica() {
        // Inicializa o serviço de agendamento
                ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Agendamento da tarefa de exclusão a cada 24 horas (pode ajustar conforme necessário)
        scheduler.scheduleAtFixedRate(BancoDeDadosMySQL::executarExclusao, 0, 24, TimeUnit.HOURS);
    }
            
            private static void executarExclusao() {
        try (Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA)) {
            
           // Define a data limite para 60 dias no passado
            long limite = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(60);

            // Cria a instrução SQL para excluir registros expirados
            String sql = "DELETE FROM ORDEM_DE_SERVICO WHERE EXPIRACAO < ?";
            
            try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {
                preparedStatement.setTimestamp(1, new java.sql.Timestamp(limite));
                
                // Executa a instrução SQL
                int linhasAfetadas = preparedStatement.executeUpdate();
                
                // Exibe o número de registros excluídos (opcional)
                System.out.println("Registros excluídos: " + linhasAfetadas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
            
            public static void fecharConexao(Connection conexao){
                if (conexao != null){
                    try{
                    conexao.close();
                    } catch (SQLException e){
                        System.err.println("Erro ao fechar a conexão com o banco de dados:" + e.getMessage());
                    }
                }
            }
            
            public static boolean conectar (){
                setConexao (BancoDeDadosMySQL.obterConexao());
                
                if (getConexao() != null){
                    System.out.println("Conexão com o banco de dados estabelecida.");
                    return true;
            }else{
                    return false;
                    }
            }
            
            public static Connection getConexao() {
                return conexao;
            }
            
            public static void setConexao(Connection conexao) {
                BancoDeDadosMySQL.conexao = conexao;
            }
            public static PreparedStatement getStatement(){
                return statement;
            }
            public static void setStatement (PreparedStatement statement) {
                BancoDeDadosMySQL.statement = statement;
            }
            public static ResultSet getResultado(){
                return resultado;
            }
            public static void setResultado(ResultSet resultado) {
                BancoDeDadosMySQL.resultado = resultado;
            }
}

