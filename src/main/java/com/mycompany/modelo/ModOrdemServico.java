/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

import java.sql.Date;

/**
 *
 * @author brian.7908
 */
public class ModOrdemServico {
    private int id, idEmp, idVeic, idCliente, idProdSer, idFuncionario, idGs;
    private String obs, data;

    public ModOrdemServico() {
    }

    public ModOrdemServico(int id, int idEmp, int idVeic, int idCliente, int idProdSer, int idFuncionario, String obs) {
        this.id = id;
        this.idEmp = idEmp;
        this.idVeic = idVeic;
        this.idCliente = idCliente;
        this.idProdSer = idProdSer;
        this.idFuncionario = idFuncionario;
        this.obs = obs;
        this.idGs = idGs;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdVeic() {
        return idVeic;
    }

    public void setIdVeic(int idVeic) {
        this.idVeic = idVeic;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProdSer() {
        return idProdSer;
    }

    public void setIdProdSer(int idProdSer) {
        this.idProdSer = idProdSer;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getIdGs() {
        return idGs;
    }

    public void setIdGs(int idGs) {
        this.idGs = idGs;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    @Override
    public String toString(){
        return "ModOrdemServico{" + "id=" + id + ", idEmpresa =" + idEmp + ", idVeiculo=" + idVeic + ", idCliente=" + idCliente + ", idProdSer=" + idProdSer + ", idFuncionario=" + idFuncionario + ", Observação=" + obs + ", idGservico =" + idGs + ", Expiracao=" + data + '}';
    }
}
