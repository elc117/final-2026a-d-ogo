package io.github.Aldoria.model.skill;

public class Skill {

    private String nome;
    private int custoMp;
    private int valor;

    public Skill(
        String nome,
        int custoMp,
        int valor
    ) {
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

    /*
     * GUERREIRO
     */

    public static Skill golpePesado() {
        return new Skill(
            "Golpe Pesado",
            0,
            35
        );
    }

    public static Skill gritoGuerra() {
        return new Skill(
            "Grito de Guerra",
            5,
            15
        );
    }

    /*
     * LADINO
     */

    public static Skill ataqueFurtivo() {
        return new Skill(
            "Ataque Furtivo",
            0,
            45
        );
    }

    public static Skill golpeVenenoso() {
        return new Skill(
            "Golpe Venenoso",
            5,
            30
        );
    }

    /*
     * MAGO
     */

    public static Skill bolaFogo() {
        return new Skill(
            "Bola de Fogo",
            15,
            40
        );
    }

    public static Skill nevasca() {
        return new Skill(
            "Nevasca",
            20,
            55
        );
    }

    public static Skill meteoro() {
        return new Skill(
            "Meteoro",
            40,
            100
        );
    }

    /*
     * CLERIGO
     * valores negativos = cura
     */

    public static Skill cura() {
        return new Skill(
            "Cura",
            10,
            -30
        );
    }

    public static Skill curaMaior() {
        return new Skill(
            "Cura Maior",
            20,
            -60
        );
    }

    public static Skill milagre() {
        return new Skill(
            "Milagre",
            40,
            -120
        );
    }

    public static Skill reviverLuz() {
        return new Skill(
            "Reviver Luz",
            50,
            -999
        );
    }
}
