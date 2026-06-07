package io.github.Aldoria.model.entidades;

import io.github.Aldoria.model.itens.ArmaItem;
import io.github.Aldoria.model.skill.Skill;

import java.util.ArrayList;
import java.util.List;

// classe para controlar todos os personagens (amigo e inimigo)

public class personagem {
    protected  String nome;
    protected  int vidaMax;
    protected  int vidaAtual;
    protected  int manaMax;
    protected int manaAtual;
    protected int ataque;
    protected int defesa;
    protected int velocidade;

    protected boolean defendendo = false ;
    protected  boolean envenenado = false;
    protected int turnosEnvenenado = 0;

    protected List<Skill> skill = new ArrayList<>();
    protected  ArmaItem itemEquipado;

    public personagem(String nome, int vidaMax, int manaMax, int ataque, int velocidade, int defesa){

        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vidaAtual = vidaMax;
        this.manaMax = manaMax;
        this.manaAtual = manaMax;
        this.ataque = ataque;
        this.velocidade = velocidade;
        this.defesa = defesa;
    }

    public int recebeDano(int danoBruto){
        int defesaEfetiva = defendendo ? defesa * 2 : defesa;
        int dano = Math.max(1, danoBruto - defesaEfetiva);
        vidaAtual = Math.max(0, vidaAtual - dano);
        return dano;
    }

    public int cura(int quantiaCura){
        int curado = Math.min(quantiaCura, vidaMax - vidaAtual);
        vidaAtual +=  curado;
        return curado;
    }

    public int restaurarMana(int quantiaMana){
        int restaurado = Math.min(quantiaMana, manaMax - manaAtual);
        manaAtual +=  restaurado;
        return restaurado;
    }

    public boolean gastarMana(int quantiaMana){
        if(manaAtual < quantiaMana) return false;
        manaAtual -=  quantiaMana;
        return true;
    }

    public void aplicarVeneno(int turnos){
        envenenado = true;
        turnosEnvenenado = turnos;
    }

    public int calculaVeneno(){
        if(!envenenado) return 0;
        int dano = Math.max(1, vidaMax / 10);
        vidaAtual = Math.max(0, vidaAtual - dano);
        turnosEnvenenado--;
        if (turnosEnvenenado <= 0) envenenado = false;
        return dano;
    }

    public void setaDefesa(boolean defendendo){
        this.defendendo = defendendo;
    }

    public boolean estaVivo(){
        return vidaAtual > 0;
    }

    public void equiparArma(itemArma arma){
        this.itemEquipado = arma;
    }

    public void desequiparArma(){
        this.itemEquipado = null;
    }

    public int getAtaqueCritico(){
        return equiparArma != null ? ataque + equiparArma.getBonusAtaque() : ataque ;
    }

    public void addSkill(Skill skill){
        skill.add(skill);
    }

    public List<Skill> getSkill(){
        return skill;
    }

    public String getNome() {return nome;}
    public int getVidaMax() {return vidaMax;}
    public int getVidaAtual() {return vidaAtual;}
    public  int getManaMax() {return manaMax;}
    public int getManaAtual() {return manaAtual;}
    public int getataque() {return ataque;}
    public  int getDefesa() {return defesa;}
    public int getVelocidade() {return velocidade;}
    public boolean isDefendendo() {return defendendo;}
    public boolean isEnvenenado() {return envenenado;}
    public ArmaItem getItemEquipado() {return itemEquipado;}

    @Override
    public String toString() {
        return String.format("%s [HP:%d/%d MP:%d/%d ATK:%d DEF:%d SPD:%d]",
            nome, vidaAtual, vidaMax, manaAtual, manaMax, getAtaqueCritico(), defesa, velocidade);
    }
}
