package com.example.cfp.model;

import java.io.Serializable;
import java.util.Date;

public class Despesa implements Serializable {
    private int id;
    private String despesa;
    private double valor;
    private Date data;

    public Despesa() {}

    public Despesa(int id, String despesa, double valor, Date data) {
        this.id = id;
        this.despesa = despesa;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDespesa() {
        return despesa;
    }

    public void setDespesa(String despesa) {
        this.despesa = despesa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
