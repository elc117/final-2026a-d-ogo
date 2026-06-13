package io.github.Aldoria.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.Aldoria.GameContext;
import io.github.Aldoria.Main;

public class TelaMapa implements Screen {

    private final Main jogo;
    private final GameContext contexto;

    private SpriteBatch batch;
    private BitmapFont font;

    public TelaMapa(Main jogo, GameContext contexto) {
        this.jogo = jogo;
        this.contexto = contexto;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();

        System.out.println("Mapa carregado");
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.1f, 0.2f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        font.draw(batch,
            "ALDORIA RPG",
            100,
            300);

        font.draw(batch,
            "Jogo carregado com sucesso",
            100,
            260);

        font.draw(batch,
            "Heroi: " + contexto.grupo.get(0).getNome(),
            100,
            220);

        batch.end();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
