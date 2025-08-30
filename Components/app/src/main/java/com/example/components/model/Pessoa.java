package com.example.components.model;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private String nome;
    private String AreaAtuacao;
    private String Sexo;
    private String Notificacao;
    private String DataAgendamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaAtuacao() {
        return AreaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        AreaAtuacao = areaAtuacao;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getNotificacao() {
        return Notificacao;
    }

    public void setNotificacao(String notificacao) {
        Notificacao = notificacao;
    }

    public String getDataAgendamento() {
        return DataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        DataAgendamento = dataAgendamento;
    }
}
