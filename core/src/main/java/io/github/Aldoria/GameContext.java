package io.github.Aldoria;

import io.github.Aldoria.model.entidades.Heroi;
import io.github.Aldoria.model.entidades.Inimigo;
import io.github.Aldoria.model.itens.Item;
import io.github.Aldoria.model.mundo.MapaJogo;

import java.util.ArrayList;
import java.util.List;

public class GameContext {

    public List<Heroi> grupo;
    public List<Item> inventario;
    public List<Inimigo> inimigos;

    public MapaJogo mapaAtual;

    public int linhaJogador;
    public int colunaJogador;
    public int inimigosDerrotados = 0;
    public List<Item> inventarioGlobal = new ArrayList<>();

    public GameContext() {

        grupo = new ArrayList<>();
        inventario = new ArrayList<>();
        inimigos = new ArrayList<>();

        mapaAtual = new MapaJogo();

        linhaJogador = 5;
        colunaJogador = 5;

        inicializarGrupo();
    }

    private void inicializarGrupo() {

        grupo.add(new Heroi("Guerreiro"));
        grupo.add(new Heroi("Ladino"));
        grupo.add(new Heroi("Mago"));
        grupo.add(new Heroi("Clerigo"));
    }

    public Heroi getHeroiPrincipal() {
        return grupo.get(0);
    }

    public List<Heroi> getGrupo() {
        return grupo;
    }

    public List<Inimigo> getInimigos() {
        return inimigos;
    }
}
