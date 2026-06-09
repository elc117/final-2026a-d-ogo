package io.github.Aldoria.model.itens;

import io.github.Aldoria.model.entidades.Personagem;
public class ItemConsumivel extends Item {
    public enum Efeito {RECUPERAR_VIDA, RECUPERAR_MANA, CURAR_VENENO, DADO_INSTANTANEO}

    private final Efeito efeito;
    private final int valor;

    public ItemConsumivel(String nome, String descricao, Efeito efeito, int valor){
        super(nome, descricao, TipoItem.COMSUMIVEL);
        this.efeito = efeito;
        this.valor = valor;
    }

    public Efeito getEfeito() {return efeito;}
    public int getValor() {return valor;}

    public static ItemConsumivel pocao() {
        return new ItemConsumivel("Poção", "Restaura 50 HP", Effect.HEAL_HP, 50);
    }

    public static ItemConsumivel ether() {
        return new ItemConsumivel("Éter", "Restaura 30 MP", Effect.HEAL_MP, 30);
    }

    public static ItemConsumivel antidoto() {
        return new ItemConsumivel("Antídoto", "Cura veneno", Effect.CURE_POISON, 0);
    }

    public static ItemConsumivel bomba() {
        return new ItemConsumivel("Bomba", "Causa 60 de dano a todos inimigos", Effect.DAMAGE_ALL, 60);
    }

}
