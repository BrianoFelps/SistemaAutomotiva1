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
public class DaoEndereco extends BancoDeDadosMySQL{
    private String sql;
    
    public Boolean inserir (int id, int idcid, int numRes, String rua, String CEP){
        try{
            sql= "INSERT INTO ENDERECO (ID, IDCID, RUA, CEP, NUM_RESID) VALUES (?, ?, ?, ?, ?)";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            getStatement().setInt(2, idcid);
            getStatement().setString(3, rua);
            getStatement().setString(4, CEP);
            getStatement().setInt(5, numRes);
            
            getStatement().executeUpdate();
            
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
      public Boolean alterar (int id, int NidCid, String nRua, String nCEP, int NnumRes){
        try{
            sql = "UPDATE ENDERECO SET IDCID = ?, RUA = ?, CEP = ?, NUM_RESID = ? WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
                getStatement().setInt(1, NidCid);
                getStatement().setString(2, nRua);
                getStatement().setString(3, nCEP);
                getStatement().setInt(4, NnumRes);
                getStatement().setInt(5, id);
                
                getStatement().executeUpdate();
                
                return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public Boolean excluir (int id){
        try{
            sql = "DELETE FROM ENDERECO WHERE ID = ?";
            
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
            sql = "SELECT E.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID FROM ENDERECO E JOIN CIDADE C ON E.IDCID = C.ID ORDER BY E.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorId(int id){
        try{
            sql = "SELECT E.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID FROM ENDERECO E JOIN CIDADE C ON E.IDCID = C.ID WHERE E.ID = ? ORDER BY E.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorNomeCid(String cid){
        try{
        sql = "SELECT E.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID FROM ENDERECO E JOIN CIDADE C ON E.IDCID = C.ID WHERE C.NOME LIKE ? ORDER BY E.ID";
        
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, cid + "%");
            
            setResultado(getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorNomeRua(String rua){
        try{
        sql = "SELECT E.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID FROM ENDERECO E JOIN CIDADE C ON E.IDCID = C.ID WHERE E.RUA LIKE ? ORDER BY E.ID";
        
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, rua + "%");
            
            setResultado(getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorCEP(String CEP){
        try{
        sql = "SELECT E.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID FROM ENDERECO E JOIN CIDADE C ON E.IDCID = C.ID WHERE E.CEP LIKE ? ORDER BY E.ID";
        
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, CEP + "%");
            
            setResultado(getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorNumResid(int NumRes){
        try{
            sql = "SELECT E.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID FROM ENDERECO E JOIN CIDADE C ON E.IDCID = C.ID WHERE E.NUM_RESID = ? ORDER BY E.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, NumRes);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public int buscarProximoId (){
        int id = -1;
        
        try{
            sql = "SELECT MAX(ID) + 1 FROM ENDERECO";
            
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
