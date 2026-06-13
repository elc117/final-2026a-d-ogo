package io.github.Aldoria;

import com.badlogic.gdx.Game;
import io.github.Aldoria.telas.TelaMapa;

public class Main extends Game {

    private GameContext contexto;

    @Override
    public void create() {
        contexto = new GameContext();
        setScreen(new TelaMapa(this, contexto));
    }
}
