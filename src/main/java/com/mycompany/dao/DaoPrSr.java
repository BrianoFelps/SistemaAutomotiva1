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
public class DaoPrSr extends BancoDeDadosMySQL{
    private String sql;
    
    public Boolean inserir (int id, int idGp, String nome, String desc, Double preco){
        try{
        sql = "INSERT INTO PRODUTOSSERVICOS (ID, IDGRUPO, NOME, DESCRICAO, PRECO) VALUES (?, ?, ?, ?, ?)";
        
        setStatement(getConexao().prepareStatement(sql));
        
        getStatement().setInt(1, id);
        getStatement().setInt(2, idGp);
        getStatement().setString(3, nome);
        getStatement().setString(4, desc);
        getStatement().setDouble(5, preco);

            getStatement().executeUpdate();
            
            return true;
    }catch (Exception e){
        
            System.out.println(e.getMessage());
            return false;
    }
    }
    
    public Boolean alterar (int id, int nIdGp, String nNome, String nDesc, Double nPreco){
    try{
        sql = "UPDATE PRODUTOSSERVICOS SET IDGRUPO = ?, NOME = ?, DESCRICAO = ?, PRECO= ? WHERE ID= ?";
        
        setStatement(getConexao().prepareStatement(sql));
        
        getStatement().setInt(5, id);
        getStatement().setInt(1, nIdGp);
        getStatement().setString(2, nNome);
        getStatement().setString(3, nDesc);
        getStatement().setDouble(4, nPreco);

            getStatement().executeUpdate();
            
            return true;
    }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
    }
    }
    
    public Boolean excluir(int id){
    try{
        sql = "DELETE FROM PRODUTOSSERVICOS WHERE ID = ?";
        
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
            sql = "SELECT P.ID, G.NOME, P.NOME, P.DESCRICAO, P.PRECO FROM PRODUTOSSERVICOS P JOIN GRUPO_SERVICO G ON P.IDGRUPO = G.ID ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorId(int id){
        try{
            sql = "SELECT P.ID, G.NOME, P.NOME, P.DESCRICAO, P.PRECO FROM PRODUTOSSERVICOS P JOIN GRUPO_SERVICO G ON P.IDGRUPO = G.ID WHERE P.ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorGrupo(String Grupo){
        try{
            sql = "SELECT P.ID, G.NOME, P.NOME, P.DESCRICAO, P.PRECO FROM PRODUTOSSERVICOS P JOIN GRUPO_SERVICO G ON P.IDGRUPO = G.ID WHERE G.NOME LIKE ? ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
             getStatement().setString(1, Grupo + "%");
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorNome(String nome){
        try{
            sql = "SELECT P.ID, G.NOME, P.NOME, P.DESCRICAO, P.PRECO FROM PRODUTOSSERVICOS P JOIN GRUPO_SERVICO G ON P.IDGRUPO = G.ID WHERE P.NOME LIKE ? ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
             getStatement().setString(1, nome + "%");
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorDescricao(String desc){
        try{
            sql = "SELECT P.ID, G.NOME, P.NOME, P.DESCRICAO, P.PRECO FROM PRODUTOSSERVICOS P JOIN GRUPO_SERVICO G ON P.IDGRUPO = G.ID WHERE P.DESCRICAO LIKE ? ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
             getStatement().setString(1, desc + "%");
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorPreco(String preco){
        try{
            sql = "SELECT P.ID, G.NOME, P.NOME, P.DESCRICAO, P.PRECO FROM PRODUTOSSERVICOS P JOIN GRUPO_SERVICO G ON P.IDGRUPO = G.ID WHERE P.PRECO LIKE ? ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
             getStatement().setString(1, preco + "%");
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
     public int buscarProximoId (){
        int id = -1;
        
        try{
            sql = "SELECT IFNULL (MAX(ID), 0) + 1 FROM PRODUTOSSERVICOS";
            
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
