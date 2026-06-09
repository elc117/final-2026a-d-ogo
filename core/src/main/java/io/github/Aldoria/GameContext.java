package io.github.Aldoria;

import io.github.Aldoria.controle.ControleBatalha;
import io.github.Aldoria.controle.controleInimigo;
import io.github.Aldoria.controle.controleInventario;
import io.github.Aldoria.controle.controleGrupo;
import io.github.Aldoria.model.world.GameMap;

/**
 * Contexto global do jogo — único ponto de acesso aos managers.
 * Inicializado em {@link Main} e passado às screens.
 */

public class gameContext {

    public final controleGrupo grupo;
    public final controleInventario inventario;
    public final controleBatalha batalha;
    public final controleInimigoSpawner spawner;

    public GameContext() {
        grupo = new controleGrupo();
        inventario = new controleInventario();
        batalha = new controleBatalha(grupo, inventario);
        spawner = new inimigoSpawner();
        carregarMapa(0);
    }

    public void carregarMapa(int index){
        currentMapIndex = index;
        currentMap = switch (index) {
            case 1 -> GameMap.criarMapa2();
            case 2 -> GameMap.criarMapa3();
            default -> GameMap.criarMapa1();
        };
        jogadorCol = currentMap.getStartX();
        jogadorLin = currentMap.getStartY();
        spawner.initFinalBoss(); //reinicia as fases do chefe ao mudar o mapa
    }
}
