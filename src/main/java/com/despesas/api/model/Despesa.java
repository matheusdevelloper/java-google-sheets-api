package com.despesas.api.model;

public class Despesa {

    private String data;
    private String categoria;
    private String descricao;
    private Double valor;

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

}
