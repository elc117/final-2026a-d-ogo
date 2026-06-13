package io.github.Aldoria.model.itens;

public class ItemConsumivel extends Item {

    private int cura;

    public ItemConsumivel(String nome, String descricao, int cura) {
        super(nome, descricao);
        this.cura = cura;
    }

    public int getCura() {
        return cura;
    }
}
