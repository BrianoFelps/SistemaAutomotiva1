/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo;

/**
 *
 * @author brian.7908
 */
public class ModPessoa {
    private int id, idEnd;
    private String nm, sb, gen, tel, ema;

    public ModPessoa() {
    }

    public ModPessoa(int id, int idEnd, String nm, String sb, String gen, String tel, String ema) {
        this.id = id;
        this.idEnd = idEnd;
        this.nm = nm;
        this.sb = sb;
        this.gen = gen;
        this.tel = tel;
        this.ema = ema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEnd() {
        return idEnd;
    }

    public void setIdEnd(int idEnd) {
        this.idEnd = idEnd;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEma() {
        return ema;
    }

    public void setEma(String ema) {
        this.ema = ema;
    }
    
    @Override
    public String toString() {
        return "ModPessoa{" + "id=" + id + ", idEndereco=" + idEnd +  ", nome=" + nm + ", sobrenome=" + sb + ", genero=" + gen + ", telefone=" + tel + ", email=" + ema + '}';
    }
}
