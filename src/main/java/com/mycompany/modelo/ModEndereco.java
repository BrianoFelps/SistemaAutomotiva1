/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author brian.7908
 */
public class ModEndereco {
    private int id, idCid, numResid;
    private String rua, CEP;

    public ModEndereco() {
    }

    public ModEndereco(int id, int idCid, int numResid, String rua, String CEP) {
        this.id = id;
        this.idCid = idCid;
        this.numResid = numResid;
        this.rua = rua;
        this.CEP = CEP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCid() {
        return idCid;
    }

    public void setIdCid(int idCid) {
        this.idCid = idCid;
    }

    public int getNumResid() {
        return numResid;
    }

    public void setNumResid(int numResid) {
        this.numResid = numResid;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }
    
    @Override 
    public String toString(){
        return "Endereco{" + "id=" + id + ", id cidade=" + idCid + ", rua=" + rua + ", CEP=" + CEP + "Num resid=" + numResid + "}";
    }
}
