/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author brian.7908
 */
public class ModPrSr {
    private int id, idGrupo;
    private String nome, descricao;
    private Double preco;

    public ModPrSr() {
    }

    public ModPrSr(int id, int idGrupo, String nome, String descricao, Double preco) {
        this.id = id;
        this.idGrupo = idGrupo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    @Override
    public String toString(){
        return "ModPrSr {" + "id= " + id + ", idGrupo=" + idGrupo + ", nome=" + nome + ", descrição=" + descricao + ", preço=" + preco + "}";
    }
}
