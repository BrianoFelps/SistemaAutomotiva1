/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author brian.7908
 */
public class ModVeiculo {
    private int id, idMar, ano;
    private String nome, placa;

    public ModVeiculo() {
    }

    public ModVeiculo(int id, int idMar, int ano, String nome, String placa) {
        this.id = id;
        this.idMar = idMar;
        this.ano = ano;
        this.nome = nome;
        this.placa = placa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMar() {
        return idMar;
    }

    public void setIdMar(int idMar) {
        this.idMar = idMar;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    @Override
    public String toString(){
        return "ModVeiculo {" + "id=" + id + ", idMarca=" + idMar + ", nome=" + nome + ", placa=" + placa + ", ano=" + ano + "}";
    }
}
