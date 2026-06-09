package io.github.Aldoria.model.itens;

public abstract class Item {

    public enum TipoItem {COMSUMIVEL, ARMA}

    private final String nome;
    private final String descricao;
    private final TipoItem tipo;

    public Item(String nome, String descricao, TipoItem tipo) {
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public String getNome() {return nome;}
    public String getDescricao() {return descricao;}
    public TipoItem getTipo() {return tipo;}

    @Override
    public String toString(){return nome;}
}
