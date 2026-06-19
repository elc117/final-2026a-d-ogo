package io.github.Aldoria.model.entidades;

import io.github.Aldoria.model.itens.ItemArma;

import java.util.ArrayList;
import java.util.List;

public class Heroi {

    private String nome;

    private int hpAtual;
    private int hpMaximo;

    private int mpAtual;
    private int mpMaximo;

    private int ataque;
    private int defesa;
    private int velocidade;

    private int cargasMagia = 3;
    private int cargasDivinas = 3;

    private ItemArma armaEquipada;

    private List<String> skills;

    public Heroi(String nome) {

        this.nome = nome;

        skills = new ArrayList<>();

        configurarClasse(nome);
    }

    private void configurarClasse(String classe) {

        switch (classe.toLowerCase()) {

            case "guerreiro":

                hpMaximo = 220;
                hpAtual = hpMaximo;

                mpMaximo = 20;
                mpAtual = mpMaximo;

                ataque = 22;
                defesa = 20;
                velocidade = 8;

                skills.add("Golpe Pesado");

                break;

            case "ladino":

                hpMaximo = 110;
                hpAtual = hpMaximo;

                mpMaximo = 30;
                mpAtual = mpMaximo;

                ataque = 40;
                defesa = 8;
                velocidade = 20;

                skills.add("Ataque Furtivo");

                break;

            case "mago":

                hpMaximo = 80;
                hpAtual = hpMaximo;

                mpMaximo = 120;
                mpAtual = mpMaximo;

                ataque = 8;
                defesa = 6;
                velocidade = 12;

                skills.add("Bola de Fogo");
                skills.add("Raio");

                break;

            case "clerigo":

                hpMaximo = 100;
                hpAtual = hpMaximo;

                mpMaximo = 100;
                mpAtual = mpMaximo;

                ataque = 10;
                defesa = 10;
                velocidade = 10;

                skills.add("Cura");
                skills.add("Reviver");

                break;

            default:

                hpMaximo = 100;
                hpAtual = hpMaximo;

                mpMaximo = 50;
                mpAtual = mpMaximo;

                ataque = 10;
                defesa = 10;
                velocidade = 10;

                break;
        }
    }

    public void receberDano(int dano) {

        hpAtual -= dano;

        if (hpAtual < 0) {
            hpAtual = 0;
        }
    }

    public void curar(int valor) {

        if (hpAtual <= 0) {
            return;
        }

        hpAtual += valor;

        if (hpAtual > hpMaximo) {
            hpAtual = hpMaximo;
        }
    }

    public void reviver() {

        if (hpAtual > 0) {
            return;
        }

        hpAtual = hpMaximo / 2;
    }

    public boolean gastarMana(int custo) {

        if (mpAtual < custo) {
            return false;
        }

        mpAtual -= custo;

        return true;
    }

    public boolean estaVivo() {
        return hpAtual > 0;
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

    public int getMpAtual() {
        return mpAtual;
    }

    public int getMpMaximo() {
        return mpMaximo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getVelocidade() {
        return velocidade;
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

    public int getCargasMagia() {
        return cargasMagia;
    }

    public int getCargasDivinas() {
        return cargasDivinas;
    }

    public boolean usarCargaMagia() {

        if (cargasMagia <= 0) {
            return false;
        }

        cargasMagia--;

        return true;
    }

    public boolean usarCargaDivina() {

        if (cargasDivinas <= 0) {
            return false;
        }

        cargasDivinas--;

        return true;
    }

    public void recuperarCargaMagia() {
        cargasMagia++;
    }

    public void recuperarCargaDivina() {
        cargasDivinas++;
    }
}
