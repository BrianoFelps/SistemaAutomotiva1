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
public class DaoVeiculo extends BancoDeDadosMySQL{
    private String sql;
    
     public Boolean inserir (int id, int idM,  String nom, String pla, int ano){
        try{
            sql = "INSERT INTO VEICULO (ID, IDMARCA, NOME, PLACA, ANO) VALUES (?, ?, ?, ?, ?)";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
                    getStatement().setInt(2, idM);
                    getStatement().setString(3, nom);
                    getStatement().setString(4, pla);
                    getStatement().setInt(5, ano);
                    
                    getStatement().executeUpdate();
                    
                    return true;
        }catch (Exception e){
        
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Boolean alterar (int id, int nIdM, String nNom, String nPlaca, int nAno){
        try{
            sql = "UPDATE VEICULO SET IDMARCA = ?, NOME = ?, PLACA = ?, ANO = ? WHERE ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
                getStatement().setInt(1, nIdM);
                getStatement().setString(2, nNom);
                getStatement().setString(3, nPlaca);
                getStatement().setInt(4, nAno);
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
            sql = "DELETE FROM VEICULO WHERE ID = ?";
            
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
            sql = "SELECT V.ID, M.NOME, V.NOME, V.PLACA, V.ANO FROM VEICULO V JOIN MARCA M ON V.IDMARCA = M.ID ORDER BY V.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            setResultado(getStatement().executeQuery());
            
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
    
     public ResultSet listarPorId(int id){
        try{
            sql = "SELECT V.ID, M.NOME, V.NOME, V.PLACA, V.ANO FROM VEICULO V JOIN MARCA M ON V.IDMARCA = M.ID WHERE V.ID = ?";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, id);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
     
     public ResultSet listarPorMarca(String marca){
        try{
            sql = "SELECT V.ID, M.NOME, V.NOME, V.PLACA, V.ANO FROM VEICULO V JOIN MARCA M ON V.IDMARCA = M.ID WHERE M.NOME LIKE ? ORDER BY V.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, marca + "%");
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
     
     public ResultSet listarPorNome(String nome){
        try{
            sql = "SELECT V.ID, M.NOME, V.NOME, V.PLACA, V.ANO FROM VEICULO V JOIN MARCA M ON V.IDMARCA = M.ID WHERE V.NOME LIKE ? ORDER BY V.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, nome + "%");
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
     
     public ResultSet listarPorPlaca(String placa){
        try{
            sql = "SELECT V.ID, M.NOME, V.NOME, V.PLACA, V.ANO FROM VEICULO V JOIN MARCA M ON V.IDMARCA = M.ID WHERE V.PLACA LIKE ? ORDER BY V.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setString(1, placa + "%");
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
     
     public ResultSet listarPorAno(int ano){
        try{
            sql = "SELECT V.ID, M.NOME, V.NOME, V.PLACA, V.ANO FROM VEICULO V JOIN MARCA M ON V.IDMARCA = M.ID WHERE V.ANO = ? ORDER BY V.ID";
            
            setStatement(getConexao().prepareStatement(sql));
            
            getStatement().setInt(1, ano);
            
            setResultado (getStatement().executeQuery());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return getResultado();
    }
     
       public int buscarProximoId (){
        int id = -1;
        
        try{
            sql = "SELECT IFNULL (MAX(ID), 0) + 1 FROM VEICULO";
            
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
