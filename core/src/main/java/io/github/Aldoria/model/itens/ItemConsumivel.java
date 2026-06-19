package io.github.Aldoria.model.itens;

public class ItemConsumivel extends Item {

    private int valor;

    public ItemConsumivel(
        String nome,
        String descricao,
        int valor
    ) {
        super(nome, descricao);
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static ItemConsumivel pocaoVida() {

        return new ItemConsumivel(
            "Pocao de Vida",
            "Recupera 50 pontos de HP",
            50
        );
    }

    public static ItemConsumivel pocaoMana() {

        return new ItemConsumivel(
            "Pocao de Mana",
            "Recupera 30 pontos de MP",
            30
        );
    }
}
