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
public class DaoPessoa extends BancoDeDadosMySQL{
    private String sql;
    
    public Boolean inserir (int id, int idend,  String nom, String sob, String gen, String tel, String ema){
        try{
            sql = "INSERT INTO PESSOA (ID, IDENDERECO, NOME, SOBRENOME, GENERO, TELEFONE, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
                    getStatement().setInt(2, idend);
                    getStatement().setString(3, nom);
                    getStatement().setString(4, sob);
                    getStatement().setString(5, gen);
                    getStatement().setString(6, tel);
                    getStatement().setString(7, ema);
                    
                    getStatement().executeUpdate();
                    
                    return true;
        }catch (Exception e){
        
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Boolean alterar (int id, int novoidend, String novonom, String novosob, String novogen, String novotel, String novoema){
        try{
            sql = "UPDATE PESSOA SET IDENDERECO = ?, NOME = ?, SOBRENOME = ?, GENERO = ?, TELEFONE = ?, EMAIL = ? WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
                getStatement().setInt(1, novoidend);
                getStatement().setString(2, novonom);
                getStatement().setString(3, novosob);
                getStatement().setString(4, novogen);
                getStatement().setString(5, novotel);
                getStatement().setString(6, novoema);
                 getStatement().setInt(7, id);
                 
                getStatement().executeUpdate();
                
                return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
     
    public Boolean excluir (int id){
        try{
            sql = "DELETE FROM PESSOA WHERE ID = ?";
            
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
            sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorId(int id){
        try{
            sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                     + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "WHERE P.ID = ? "
                    + "ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorRua(String nomerua){
        try{
        sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "WHERE E.RUA LIKE ? "
                    + "ORDER BY P.ID";
        
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, nomerua + "%");
            
            setResultado(getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorCEP(String cep){
        try{
        sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "WHERE E.CEP LIKE ? "
                    + "ORDER BY P.ID";
        
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, cep + "%");
            
            setResultado(getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorNumResid(String numR){
        try{
        sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "WHERE E.NUM_RESID LIKE ? "
                    + "ORDER BY P.ID";
        
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, numR + "%");
            
            setResultado(getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
     public ResultSet listarPorNome(String nome){
        try{
            sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "WHERE P.NOME LIKE ? "
                    + "ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, nome + "%");
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorSob(String sobn){
        try{
            sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "WHERE P.SOBRENOME LIKE ? "
                    + "ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, sobn + "%");
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorGen(String gen){
        try{
            sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID " 
                    + "WHERE P.GENERO LIKE ? "
                    + "ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, gen + "%");
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorTel(String tel){
        try{
            sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO= E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "WHERE P.TELEFONE LIKE ? "
                    + "ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, tel + "%");
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
    public ResultSet listarPorEmail(String email){
        try{
            sql = "SELECT P.ID, C.NOME, E.RUA, E.CEP, E.NUM_RESID, P.NOME, P.SOBRENOME, P.GENERO, P.TELEFONE, P.EMAIL "
                    + "FROM PESSOA P "
                    + "JOIN ENDERECO E "
                    + "ON P.IDENDERECO = E.ID "
                    + "JOIN CIDADE C "
                    + "ON E.IDCID = C.ID "
                    + "WHERE P.EMAIL LIKE ? "
                    + "ORDER BY P.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, email + "%");
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
     public int buscarProximoId (){
        int id = -1;
        
        try{
            sql = "SELECT IFNULL (MAX(ID), 0) + 1 FROM PESSOA";
            
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
