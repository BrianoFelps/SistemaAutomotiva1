/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author brian.7908
 */
public class ModCliente {
    private int id, idPess;

    public ModCliente() {
    }

    public ModCliente(int id, int idPess) {
        this.id = id;
        this.idPess = idPess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPess() {
        return idPess;
    }

    public void setIdPess(int idPess) {
        this.idPess = idPess;
    }
    
    @Override
    public String toString(){
        return "ModCliente{" + "id =" + id + ", id pessoa=" + idPess + "}";
    }
}
