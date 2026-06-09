package io.github.Aldoria.model.entidades;

import io.github.Aldoria.model.skill.Skill;

import java.util.List;

/**
 * Inimigo (comum ou chefe).
 * A IA escolhe o alvo com menor HP dentre os heróis vivos.
 */

public class Inimigo extends Personagem {

    private final int recompensaExp;
    private final boolean umChefe;
    private String rotuloAcaoEspecial = null; //rotulo exibido na HUD

    public Inimigo(String nome, int vidaMax, int manaMax,
                   int ataque, int defesa, int velocidade,
                   int recompensaExp, boolean umChefe) {
        super(nome,vidaMax,manaMax,ataque,defesa,velocidade);
        this.recompensaExp = recompensaExp;
        this.umChefe = umChefe;
    }

    public int escolheAlvo(List<Heroi> heroi){
        rotuloAcaoEspecial = "Ataque";
        int alvo = -1;
        int vidaBaixa = Integer.MAX_VALUE;

        for(int i = 0; i < heroi.size(); i++){
            Heroi heroi = herois.get(i);
            if(heroi.estaVivo() && heroi.getVidaAtual() < vidaBaixa){
                vidaBaixa = heroi.getVidaAtual();
                alvo = i;
            }
        }
        return alvo;
    }

    public int getRecompensaExp() {return recompensaExp;}
    public boolean umChefe() {return umChefe;}
    public String getRotuloAcaoEspecial() {return rotuloAcaoEspecial;}
    protected  void setRotuloAcaoEspecial(String label) {rotuloAcaoEspecial = label;}

    public static Inimigo[] colocarMapa1() {
        return new Inimigo[]{
            new Inimigo("Aranha Sombria", 60, 0, 18, 5, 12, 20, false),
            new Inimigo("Espinho Corrompido", 80, 0, 22, 8, 10, 30, false),
            new Inimigo("Lobo do Vazio", 70, 0, 25, 6, 18, 25, false)
        };
    }

    public static Inimigo[] colocarMapa2() {
        return new Inimigo[]{
            new Inimigo("Golem Fragmentado", 120, 0, 30, 20, 8, 50, false),
            new Inimigo("Espectro Ruinoso", 90, 40, 28, 10, 20, 45, false),
            new Inimigo("Guardião de Pedra", 100, 0, 22, 25, 7, 40, false)
        };
    }

    public static Inimigo[] colocarMapa3() {
        return new Inimigo[]{
            new Inimigo("Demônio do Vazio", 150, 50, 40, 18, 22, 70, false),
            new Inimigo("Sombra Eterna", 130, 60, 35, 15, 28, 65, false),
            new Inimigo("Carcaça Animada", 140, 0, 38, 22, 15, 60, false)

        };
    }

    public static Inimigo mapaChefe1(){
        Inimigo boss = new Inimigo("Araclyx", 350, 0, 45, 15, 14, 200, true);
        return boss;
    }

    public static Inimigo mapaChefe2(){
        Inimigo boss = new Inimigo("Golem do Vazio", 500, 0, 55, 30, 8, 350, true);
        return boss;
    }

    public static Inimigo[] faseFinalChefe(){
        return new Inimigo[]{
            new Inimigo("O Sem-Nome (Fase A)", 400, 80, 60, 20, 18, 0, true),
            new Inimigo("O Sem-Nome (Fase B)", 350, 100, 50, 25, 25, 0, true),
            new Inimigo("O Sem-Nome (Fase C)", 450, 60, 65, 15, 15, 0, true),
            new Inimigo("O Sem-Nome (Fase D)", 380, 90, 55, 30, 20, 0, true),
            new Inimigo("O Sem-Nome (Fase E)", 300, 120, 45, 10, 30, 0, true)
        };
    }
}
