/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.ferramentas.BancoDeDadosMySQL;
import java.sql.Date;

/**
 *
 * @author brian.7908
 */
public class DaoOrdemServico extends BancoDeDadosMySQL{
    private String sql;
    
    public Boolean inserir (int id, int idEmp, int idVeic, int idCliente, int idGser, int idFuncionario, String obs, String expiracao){
        try{
        sql = "INSERT INTO ORDEM_DE_SERVICO (ID, IDEMPRESA, IDVEICULO, IDCLIENTE, IDFUNCIONARIO, OBSERVACAO, IDGSERVICO, EXPIRACAO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        setStatement(getConexao().prepareStatement(sql));
        
        getStatement().setInt(1, id);
        getStatement().setInt(2, idEmp);
        getStatement().setInt(3, idVeic);
        getStatement().setInt(4, idCliente);
        getStatement().setInt(5, idFuncionario);
        getStatement().setString(6, obs);
        getStatement().setInt(7, idGser);
        getStatement().setString(8, expiracao);

            getStatement().executeUpdate();
            
            return true;
        }catch (Exception e){
        
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Boolean alterar (int id, int nidEmp, int nidVeic, int nidCliente, int nidGser, int nidFuncionario, String nobs, String nEx){
    try{
        sql = "UPDATE ORDEM_DE_SERVICO SET IDEMPRESA = ?, IDVEICULO = ?, IDCLIENTE = ?, IDFUNCIONARIO = ?, OBSERVACAO = ?, IDGSERVICO = ?, EXPIRACAO = ? WHERE ID= ?";
        
        setStatement(getConexao().prepareStatement(sql));
        
        getStatement().setInt(1, nidEmp);
        getStatement().setInt(2, nidVeic);
        getStatement().setInt(3, nidCliente);
        getStatement().setInt(4, nidFuncionario);
        getStatement().setString(5, nobs);
        getStatement().setInt(6, nidGser);
        getStatement().setString(7, nEx);
        getStatement().setInt(8, id);
        
            getStatement().executeUpdate();
            
            return true;
    }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
    }
    }
    
    public Boolean excluir (int id){
        try{
            sql = "DELETE FROM ORDEM_DE_SERVICO WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            getStatement().executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public int buscarProximoId (){
        int id = -1;
        
        try{
            sql = "SELECT IFNULL (MAX(ID), 0) + 1 FROM ORDEM_DE_SERVICO";
            
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