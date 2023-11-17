/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author brian.7908
 */
public class ModCidade {
    private int id;
    private String nmcid;

    public ModCidade() {
    }

    public ModCidade(int id, String cid) {
        this.id = id;
        this.nmcid = cid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNmcid() {
        return nmcid;
    }

    public void setNmcid(String nmcid) {
        this.nmcid = nmcid;
    }
    
    
    @Override
        public String toString(){
            return "Cidade{" + "id=" + id + ", nome =" + nmcid + "}";
        }
}
