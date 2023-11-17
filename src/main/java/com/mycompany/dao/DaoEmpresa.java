/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.ferramentas.BancoDeDadosMySQL;
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
public class DaoEmpresa extends BancoDeDadosMySQL{
    private String sql;
    
    public Boolean inserir (int id, String nome, String CNPJ){
        try{
            sql = "INSERT INTO EMPRESA (ID, NOME, CNPJ) VALUES (?, ?, ?)";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            getStatement().setString(2, nome);
            getStatement().setString(3, CNPJ);
            
            getStatement().executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Boolean alterar (int id, String nNome, String nCNPJ){
        try{
            sql = "UPDATE EMPRESA SET NOME = ?, CNPJ = ? WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
                getStatement().setString(1, nNome);
                getStatement().setString(2, nCNPJ);
                getStatement().setInt(3, id);
                
                getStatement().executeUpdate();
                
                return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
     public Boolean excluir (int id){
        try{
            sql = "DELETE FROM EMPRESA WHERE ID = ?";
            
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
         try{
             sql = "SELECT ID, NOME, CNPJ FROM EMPRESA";
             
             setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
         }
     
     public ResultSet listarPorId(int id){
         try{
             sql = "SELECT ID, NOME, CNPJ FROM EMPRESA WHERE ID = ?";
             
              setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();   
     }
     
     public ResultSet listarPorNome(String pnome){
         try{
             sql = "SELECT ID, NOME, CNPJ FROM EMPRESA WHERE NOME LIKE ?";
             
             setStatement(getConexao().prepareStatement(sql));
             
             getStatement().setString(1, pnome + "%");
            
            setResultado(getStatement().executeQuery());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
         }
     
     public ResultSet listarPorCNPJ(String pCNPJ){
         try{
             sql = "SELECT ID, NOME, CNPJ FROM EMPRESA WHERE CNPJ LIKE ?";
             
             setStatement(getConexao().prepareStatement(sql));
             
             getStatement().setString(1, pCNPJ + "%");
            
            setResultado(getStatement().executeQuery());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
         }
     
      public int buscarProximoId (){
        int id = -1;
        
        try{
            sql = "SELECT MAX(ID) + 1 FROM EMPRESA";
            
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
