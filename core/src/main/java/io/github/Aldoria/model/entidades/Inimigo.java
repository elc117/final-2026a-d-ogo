package io.github.Aldoria.model.entidades;

public class Inimigo {

    private String nome;
    private int hp = 50;

    public Inimigo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public boolean estaVivo() {
        return hp > 0;
    }

    public static Inimigo spawnBasico() {
        return new Inimigo("Slime");
    }
}
