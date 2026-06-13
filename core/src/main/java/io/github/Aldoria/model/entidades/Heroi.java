package io.github.Aldoria.model.entidades;

import io.github.Aldoria.model.itens.ItemArma;

import java.util.ArrayList;
import java.util.List;

public class Heroi {

    private String nome;
    private int hp = 100;

    private int defesa = 10;
    private int velocidade = 10;

    private ItemArma armaEquipada;

    private List<String> skills = new ArrayList<>();

    public Heroi(String nome) {
        this.nome = nome;
        skills.add("Ataque Básico");
    }

    public String getNome() {
        return nome;
    }

    public int getHpAtual() {
        return hp;
    }

    public int getHpMaximo() {
        return 100;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public boolean estaVivo() {
        return hp > 0;
    }

    public ItemArma getArmaEquipada() {
        return armaEquipada;
    }

    public void equipar(ItemArma arma) {
        this.armaEquipada = arma;
    }

    public List<String> getSkills() {
        return skills;
    }
}
