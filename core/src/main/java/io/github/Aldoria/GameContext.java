package io.github.Aldoria;

import io.github.Aldoria.model.entidades.Heroi;
import io.github.Aldoria.model.entidades.Inimigo;
import io.github.Aldoria.model.itens.Item;
import io.github.Aldoria.model.mundo.MapaJogo;

import java.util.ArrayList;
import java.util.List;

public class GameContext {

    public List<Heroi> grupo = new ArrayList<>();
    public List<Item> inventario = new ArrayList<>();
    public List<Inimigo> inimigos = new ArrayList<>();

    public MapaJogo mapaAtual = new MapaJogo();

    public int linhaJogador = 5;
    public int colunaJogador = 5;

    public GameContext() {
        grupo.add(new Heroi("Herói"));
    }
}
