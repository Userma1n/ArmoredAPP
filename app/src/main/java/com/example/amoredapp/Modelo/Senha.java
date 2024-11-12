package com.example.amoredapp.Modelo;

public class Senha {

    String id, titulo, conta, usuario, senha, site, nota, t_registro, t_atualizacao;

    public Senha(String id, String titulo, String conta, String usuario, String senha, String site, String nota, String t_registro, String t_atualizacao) {
        this.id = id;
        this.titulo = titulo;
        this.conta = conta;
        this.usuario = usuario;
        this.senha = senha;
        this.site = site;
        this.nota = nota;
        this.t_registro = t_registro;
        this.t_atualizacao = t_atualizacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getT_registro() {
        return t_registro;
    }

    public void setT_registro(String t_registro) {
        this.t_registro = t_registro;
    }

    public String getT_atualizacao() {
        return t_atualizacao;
    }

    public void setT_atualizacao(String t_atualizacao) {
        this.t_atualizacao = t_atualizacao;
    }
}
