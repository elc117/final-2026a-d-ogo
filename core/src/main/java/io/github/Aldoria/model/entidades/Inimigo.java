package io.github.Aldoria.model.entidades;

public class Inimigo {

    private String nome;
    private int hpAtual;
    private int hpMaximo;
    private int ataque;

    public Inimigo(
        String nome,
        int hp,
        int ataque
    ) {
        this.nome = nome;
        this.hpAtual = hp;
        this.hpMaximo = hp;
        this.ataque = ataque;
    }

    public String getNome() {
        return nome;
    }

    public int getHpAtual() {
        return hpAtual;
    }

    public int getHpMaximo() {
        return hpMaximo;
    }

    public int getAtaque() {
        return ataque;
    }

    public void receberDano(int dano) {
        hpAtual -= dano;
        if (hpAtual < 0) {
            hpAtual = 0;
        }
    }

    public boolean estaVivo() {
        return hpAtual > 0;
    }

    public static Inimigo gerarGoblin() {
        return new Inimigo(
            "Slime",
            50 + (int)(Math.random() * 200),
            10 + (int)(Math.random() * 15)
        );
    }

    public static Inimigo gerarLobo() {
        return new Inimigo(
            "Lobo",
            80 + (int)(Math.random() * 250),
            20 + (int)(Math.random() * 20)
        );
    }

    public static Inimigo gerarEsqueleto() {
        return new Inimigo(
            "Esqueleto",
            100 + (int)(Math.random() * 300),
            30 + (int)(Math.random() * 50)
        );
    }

    public static Inimigo gerarDragao() {
        return new Inimigo(
            "Dragao Ancestral",
            800 + (int)(Math.random() * 1000),
            150 + (int)(Math.random() * 300)
        );
    }
}
