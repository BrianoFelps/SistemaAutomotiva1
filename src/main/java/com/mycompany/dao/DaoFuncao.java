/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.ferramentas.BancoDeDadosMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brian.7908
 */
public class DaoFuncao extends BancoDeDadosMySQL {
    private String sql;
    
    public Boolean inserir(int id, String nome){
        try{
        sql = "INSERT INTO FUNCAO (ID, NOME) VALUES (?, ?)";
        
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
    
    public Boolean alterar(int id, String nNome){
        try{
            sql = "UPDATE FUNCAO SET NOME = ? WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, nNome);
            getStatement().setInt(2, id);
            
            getStatement().executeUpdate();
            
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            
            return false;
        }
    }
    
    public Boolean excluir(int id){
        try{
            sql = "DELETE FROM FUNCAO WHERE ID = ?";
            
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
            sql = "SELECT ID, NOME FROM FUNCAO";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
        }catch (Exception e){
            System.out.println(e.getMessage());   
        }  
        return getResultado();
        }
    
         public ResultSet listarPorId(int id){
        try{
            sql = "SELECT ID, NOME FROM FUNCAO WHERE ID = ?";
            
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
        sql = "SELECT ID, NOME FROM FUNCAO WHERE NOME LIKE ?";
        
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, nome + "%");
            
            setResultado(getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
         
         public int buscarProximoId(){
             int id = -1;
             
             try{
                 sql = "SELECT MAX(ID) + 1 FROM FUNCAO";
                 
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

