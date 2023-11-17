/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import static com.mycompany.ferramentas.BancoDeDadosMySQL.getConexao;
import static com.mycompany.ferramentas.BancoDeDadosMySQL.getResultado;
import static com.mycompany.ferramentas.BancoDeDadosMySQL.getStatement;
import static com.mycompany.ferramentas.BancoDeDadosMySQL.setResultado;
import static com.mycompany.ferramentas.BancoDeDadosMySQL.setStatement;
import java.sql.ResultSet;

/**
 *
 * @author brian.7908
 */
public class DaoGpServico {
    private String sql;
    
    public Boolean inserir (int id, String nome){
        try{
            sql = "INSERT INTO GRUPO_SERVICO (ID, NOME) VALUES (?, ?)";
            
            setStatement(getConexao().prepareStatement(sql));
                
                getStatement().setInt(1, id);
                getStatement().setString(2, nome);

                getStatement().executeUpdate();
                
                return true;
        }catch (Exception e){
            
            System.out.println(e.getMessage());
            return false;
            
        }
    }
    
    public Boolean alterar (int id, String novonome){
       try{
           sql = "UPDATE GRUPO_SERVICO SET NOME = ? WHERE ID = ?";
           
           setStatement(getConexao().prepareStatement(sql));
           
           getStatement().setInt(2, id);
           getStatement().setString(1, novonome);
           
           getStatement().executeUpdate();
           
           return true;
       }catch (Exception e){
       
           System.out.println(e.getMessage());
           return false;
       }
   }
    
    public Boolean excluir(int id){
        try{
            sql = "DELETE FROM GRUPO_SERVICO WHERE ID = ?";
                    
                    setStatement(getConexao().prepareStatement(sql));
                    
                    getStatement().setInt(1, id);
                    
                    getStatement().executeUpdate();
                    
                    return true;
        }catch (Exception e){
        
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ResultSet listarTodos(){
        try {
            //se a descrição for nula, em vez de aparecer "null" aparece um espaço vazio.
            sql = "SELECT ID, NOME FROM GRUPO_SERVICO ";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }   
        return getResultado();
     }
    
      public ResultSet listarPorId(int id){
        try{
            sql = "SELECT ID, NOME FROM GRUPO_SERVICO WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
      
      public ResultSet listarPorNome(String nome){
        try{
        sql = "SELECT ID, NOME FROM GRUPO_SERVICO WHERE NOME LIKE ?";
        
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, nome + "%");
            
            setResultado(getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
      
       public int buscarProximoId (){
        int id = -1;
        
        try{
            sql = "SELECT IFNULL(MAX(ID), 0) + 1 FROM GRUPO_SERVICO";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
            
            getResultado().next();
           
            id = getResultado().getInt(1);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return id;
    } 
}
