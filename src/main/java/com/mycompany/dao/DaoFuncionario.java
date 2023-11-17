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
public class DaoFuncionario extends BancoDeDadosMySQL{
    private String sql;
    
    public  Boolean inserir (int id, int idF, int idP){
        try{
            sql = "INSERT INTO FUNCIONARIO (ID, IDFUNCAO, IDPESSOA) VALUES (?, ?, ?) ";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            getStatement().setInt(2, idF);
            getStatement().setInt(3, idP);

                    
                    getStatement().executeUpdate();
                    
                    return true;
        }catch (Exception e){
        
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Boolean alterar (int id, int nIdF, int nIdP){
        try{
            sql = "UPDATE FUNCIONARIO SET IDFUNCAO = ?, IDPESSOA = ? WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
                getStatement().setInt(1, nIdF);
                getStatement().setInt(2, nIdP);
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
            sql = "DELETE FROM FUNCIONARIO WHERE ID = ?";
            
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
            sql = "SELECT FR.ID, F.NOME, P.NOME, P.SOBRENOME FROM FUNCIONARIO FR JOIN FUNCAO F ON FR.IDFUNCAO = F.ID JOIN PESSOA P ON FR.IDPESSOA = P.ID ORDER BY FR.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorId(int id){
        try{
            sql = "SELECT FR.ID, F.NOME, P.NOME, P.SOBRENOME FROM FUNCIONARIO FR JOIN FUNCAO F ON FR.IDFUNCAO = F.ID JOIN PESSOA P ON FR.IDPESSOA = P.ID WHERE FR.ID = ? ORDER BY FR.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorFuncao(String funcao){
        try{
            sql = "SELECT FR.ID, F.NOME, P.NOME, P.SOBRENOME FROM FUNCIONARIO FR JOIN FUNCAO F ON FR.IDFUNCAO = F.ID JOIN PESSOA P ON FR.IDPESSOA = P.ID WHERE F.NOME LIKE ? ORDER BY FR.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, funcao + "%");
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorNome(String nome){
        try{
            sql = "SELECT FR.ID, F.NOME, P.NOME, P.SOBRENOME FROM FUNCIONARIO FR JOIN FUNCAO F ON FR.IDFUNCAO = F.ID JOIN PESSOA P ON FR.IDPESSOA = P.ID WHERE P.NOME LIKE ? ORDER BY FR.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, nome + "%");
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public int buscarProximoId (){
        int id = -1;
        
        try{
            sql = "SELECT IFNULL (MAX(ID), 0) + 1 FROM FUNCIONARIO";
            
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
