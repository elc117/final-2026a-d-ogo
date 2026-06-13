package io.github.Aldoria.model.skill;

public class Skill {

    private String nome;
    private int custoMp;
    private int valor;

    public Skill(String nome, int custoMp, int valor) {
        this.nome = nome;
        this.custoMp = custoMp;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public int getCustoMp() {
        return custoMp;
    }

    public int getValor() {
        return valor;
    }

    public static Skill gritoGuerra() {
        return new Skill("Grito de Guerra", 5, 10);
    }

    public static Skill bolaFogo() {
        return new Skill("Bola de Fogo", 8, 25);
    }

    public static Skill nevasca() {
        return new Skill("Nevasca", 10, 30);
    }

    public static Skill tiroSombrio() {
        return new Skill("Tiro Sombrio", 6, 20);
    }

    public static Skill cura() {
        return new Skill("Cura", 5, -30);
    }

    public static Skill reviverLuz() {
        return new Skill("Reviver Luz", 15, 0);
    }
}
