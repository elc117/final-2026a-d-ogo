package io.github.Aldoria.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.Aldoria.GameContext;
import io.github.Aldoria.Main;
import io.github.Aldoria.model.entidades.Heroi;
import io.github.Aldoria.model.itens.Item;

public class TelaGrupo implements Screen {

    private final Main jogo;
    private final GameContext contexto;
    private final Screen telaAnterior;

    private SpriteBatch batch;
    private BitmapFont font;

    private int cursorHeroi = 0;

    public TelaGrupo(
        Main jogo,
        GameContext contexto,
        Screen telaAnterior
    ) {

        this.jogo = jogo;
        this.contexto = contexto;
        this.telaAnterior = telaAnterior;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        font = new BitmapFont();

        font.getData().setScale(1.4f);
    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {

            cursorHeroi--;

            if (cursorHeroi < 0) {

                cursorHeroi = contexto.grupo.size() - 1;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

            cursorHeroi++;

            if (cursorHeroi >= contexto.grupo.size()) {

                cursorHeroi = 0;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {

            jogo.setScreen(telaAnterior);
        }

        Gdx.gl.glClearColor(
            0.08f,
            0.08f,
            0.12f,
            1f
        );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        font.draw(batch,
            "=== GRUPO ===",
            40,
            460);

        for (int i = 0; i < contexto.grupo.size(); i++) {

            Heroi heroi = contexto.grupo.get(i);

            String texto =
                (i == cursorHeroi ? "> " : "  ")
                    + heroi.getNome();

            font.draw(
                batch,
                texto,
                40,
                420 - i * 30
            );
        }

        Heroi heroi =
            contexto.grupo.get(cursorHeroi);

        font.draw(
            batch,
            "HP: "
                + heroi.getHpAtual()
                + "/"
                + heroi.getHpMaximo(),
            320,
            420
        );

        font.draw(
            batch,
            "MP: "
                + heroi.getMpAtual()
                + "/"
                + heroi.getMpMaximo(),
            320,
            390
        );

        font.draw(
            batch,
            "ATK: " + heroi.getAtaque(),
            320,
            360
        );

        font.draw(
            batch,
            "DEF: " + heroi.getDefesa(),
            320,
            330
        );

        font.draw(
            batch,
            "VEL: " + heroi.getVelocidade(),
            320,
            300
        );

        font.draw(
            batch,
            "Skills:",
            320,
            250
        );

        int y = 220;

        for (String skill : heroi.getSkills()) {

            font.draw(
                batch,
                "- " + skill,
                340,
                y
            );

            y -= 25;
        }

        font.draw(
            batch,
            "Inventario Global:",
            600,
            420
        );

        int yItem = 390;

        for (Item item : contexto.inventario) {

            font.draw(
                batch,
                item.getNome(),
                620,
                yItem
            );

            yItem -= 25;
        }

        font.draw(
            batch,
            "ESC - Voltar",
            40,
            30
        );

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {

        if (batch != null) {
            batch.dispose();
        }

        if (font != null) {
            font.dispose();
        }
    }
}
