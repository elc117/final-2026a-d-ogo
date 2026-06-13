package io.github.Aldoria.model.entidades;

import io.github.Aldoria.model.itens.ItemArma;

public class Personagem {

    protected String nome;
    protected int hpMaximo;
    protected int hpAtual;
    protected int ataque;
    protected int defesa;
    protected int velocidade;

    protected ItemArma armaEquipada;

    public Personagem(
        String nome,
        int hpMaximo,
        int ataque,
        int defesa,
        int velocidade,
        int lixo
    ) {
        this.nome = nome;
        this.hpMaximo = hpMaximo;
        this.hpAtual = hpMaximo;
        this.ataque = ataque;
        this.defesa = defesa;
        this.velocidade = velocidade;
    }

    public int getAtaque() {
        return ataque;
    }

    public ItemArma getArmaEquipada() {
        return armaEquipada;
    }
}
