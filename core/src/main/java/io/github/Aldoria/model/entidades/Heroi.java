package io.github.Aldoria.model.entidades;

public class heroi extends Personagem {
    public enum ClaseHeroi {GUERREIRO, MAGO, LADRAO, CLERIGO}

    public final ClaseHeroi claseHeroi;
    private int ataqueBonusTurno = 0;
    private float multiplicadorBonusAtaque = 1f;

    public heroi(String nome, ClaseHeroi claseHeroi, int vidaMax, int manaMax, int ataque, int defesa, int velocidade,) {
        super(nome, vidaMax, manaMax, ataque, defesa, velocidade);
        this.claseHeroi = claseHeroi;
    }

    public void aplicarBonusAtaque(float multiplicadorBonusAtaque, int turnos) {
        this.multiplicadorBonusAtaque = multiplicadorBonusAtaque;
        this.ataqueBonusTurno = turnos;
    }

    public void buffs(){
        if (ataqueBonusTurno > 0){
            ataqueBonusTurno--;
            if (ataqueBonusTurno == 0) multiplicadorBonusAtaque = 1f;
        }
    }

    @Override
    public int getAtaqueCritico() {
        return (int)(super.getAtaqueCritico() * multiplicadorBonusAtaque);
    }

    public ClaseHeroi getClaseHeroi() {
        return claseHeroi;
    }

    public int getAtaqueBonusTurno() {return ataqueBonusTurno;}

    public static Heroi[] criarEquipe() {
        Heroi kael   = new Heroi("Kael",   claseHeroi.WARRIOR, 180, 40, 55, 30, 20);
        Heroi lirien = new Heroi("Lirien", claseHeroi.MAGE,     90, 120, 25, 15, 25);
        Heroi thorn  = new Heroi("Thorn",  claseHeroi.ROGUE,   110, 70, 45, 20, 35);
        Heroi sera   = new Heroi("Sera",   claseHeroi.HEALER,  120, 110, 20, 25, 22);
        return new Heroi[]{kael, lirien, thorn, sera};

    }
}
