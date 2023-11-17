/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author brian.7908
 */
public class ModFuncionario {
    private int id, idFuncao, idPess;

    public ModFuncionario() {
    }

    public ModFuncionario(int id, int idFuncao, int idPess) {
        this.id = id;
        this.idFuncao = idFuncao;
        this.idPess = idPess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFuncao() {
        return idFuncao;
    }

    public void setIdFuncao(int idFuncao) {
        this.idFuncao = idFuncao;
    }

    public int getIdPess() {
        return idPess;
    }

    public void setIdPess(int idPess) {
        this.idPess = idPess;
    }
    
    @Override
    public String toString(){
        return "ModFuncionario{" + "id=" + id + ", idFuncao=" + idFuncao + "idPessoa=" + idPess + "}";
    }
}
