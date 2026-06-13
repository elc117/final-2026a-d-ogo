package io.github.Aldoria.model.itens;

public class ItemArma extends Item {

    private int dano;

    public ItemArma(String nome, String descricao, int dano) {
        super(nome, descricao);
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }
}
