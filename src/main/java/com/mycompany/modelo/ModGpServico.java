/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;




/**
 *
 * @author brian.7908
 */
public class ModGpServico{
     private int id;
     private String nome;

    public ModGpServico() {
    }

    public ModGpServico(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
     
     @Override
     public String toString(){
         return "ModGpServico {" + "id=" + id + "nome=" + nome + "}";
     }
}
