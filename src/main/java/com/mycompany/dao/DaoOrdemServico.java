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
    
    public Boolean inserir (int id, int idEmp, int idVeic, int idCliente, int idProdSer, int idFuncionario, String obs, Date FaturaIn, Date FaturaFim){
        try{
        sql = "INSERT INTO ORDEM_DE_SERVICO (ID, IDEMPRESA, IDVEICULO, IDCLIENTE, IDPRODSER, IDFUNCIONARIO, OBSERVACAO, FATURAIN, FATURAFIM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        setStatement(getConexao().prepareStatement(sql));
        
        getStatement().setInt(1, id);
        getStatement().setInt(2, idEmp);
        getStatement().setInt(3, idVeic);
        getStatement().setInt(4, idCliente);
        getStatement().setInt(5, idProdSer);
        getStatement().setInt(6, idFuncionario);
        getStatement().setString(7, obs);
        getStatement().setDate(8, FaturaIn);
        getStatement().setDate(9, FaturaFim);

            getStatement().executeUpdate();
            
            return true;
        }catch (Exception e){
        
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Boolean alterar (int id, int nidEmp, int nidVeic, int nidCliente, int nidProdSer, int nidFuncionario, String nobs, Date nFaturaIn, Date nFaturaFim){
    try{
        sql = "UPDATE ORDEM_DE_SERVICO SET IDEMPRESA = ?, IDVEICULO = ?, IDCLIENTE = ?, IDPRODSER = ?, IDFUNCIONARIO = ?, OBSERVACAO = ?, FATURAIN = ?, FATURAFIM = ? WHERE ID= ?";
        
        setStatement(getConexao().prepareStatement(sql));
        
        getStatement().setInt(9, id);
        getStatement().setInt(1, nidEmp);
        getStatement().setInt(2, nidVeic);
        getStatement().setInt(3, nidCliente);
        getStatement().setInt(4, nidProdSer);
        getStatement().setInt(5, nidFuncionario);
        getStatement().setString(6, nobs);
        getStatement().setDate(7, nFaturaIn);
        getStatement().setDate(8, nFaturaIn);

            getStatement().executeUpdate();
            
            return true;
    }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
    }
    }
}
