package io.github.Aldoria.model.entidades;

import io.github.Aldoria.model.itens.Item;
import io.github.Aldoria.model.itens.ItemArma;
import io.github.Aldoria.model.itens.ItemArmadura;
import io.github.Aldoria.model.itens.ItemAcessorio;

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

    private List<String> skills;

    private List<Item> inventario = new ArrayList<>();

    private ItemArma armaEquipada;
    private ItemArmadura armaduraEquipada;
    private ItemAcessorio acessorioEquipado;


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

    public List<Item> getInventario() {

        return inventario;
    }

    public void adicionarItem(Item item) {

        inventario.add(item);
    }

    public void removerItem(Item item) {

        inventario.remove(item);
    }

    public void equiparArma(ItemArma arma) {

        armaEquipada = arma;
    }

    public ItemArmadura getArmaduraEquipada() {

        return armaduraEquipada;
    }

    public void equiparArmadura(ItemArmadura armadura) {

        armaduraEquipada = armadura;
    }

    public ItemAcessorio getAcessorioEquipado() {

        return acessorioEquipado;
    }

    public void equiparAcessorio(ItemAcessorio acessorio) {

        acessorioEquipado = acessorio;
    }

    public void aumentarAtaque(int valor) {
        ataque += valor;
    }

    public void aumentarDefesa(int valor) {
        defesa += valor;
    }

    public void aumentarHpMaximo(int valor) {
        hpMaximo += valor;
        hpAtual += valor;
    }

    public void aumentarMpMaximo(int valor) {
        mpMaximo += valor;
        mpAtual += valor;
    }

    public void aumentarVelocidade(int valor) {
        velocidade += valor;
    }

    public void diminuirAtaque(int valor) {
        ataque -= valor;
    }

    public void diminuirDefesa(int valor) {
        defesa -= valor;
    }

    public void diminuirHpMaximo(int valor) {

        hpMaximo -= valor;

        if (hpAtual > hpMaximo) {
            hpAtual = hpMaximo;
        }
    }

    public void diminuirMpMaximo(int valor) {

        mpMaximo -= valor;

        if (mpAtual > mpMaximo) {
            mpAtual = mpMaximo;
        }
    }

    public void diminuirVelocidade(int valor) {
        velocidade -= valor;
    }
}
