package io.github.Aldoria.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Aldoria.GameContext;
import io.github.Aldoria.Main;

public class TelaMapa implements Screen {

    private static final int TILE_SIZE = 48;
    private static final int MAP_WIDTH = 20;
    private static final int MAP_HEIGHT = 12;

    private final Main jogo;
    private final GameContext contexto;

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer renderer;

    public TelaMapa(Main jogo, GameContext contexto) {
        this.jogo = jogo;
        this.contexto = contexto;
    }

    @Override
    public void show() {

        batch = new SpriteBatch();
        font = new BitmapFont();
        renderer = new ShapeRenderer();

        System.out.println("Mapa carregado");
    }

    @Override
    public void render(float delta) {

        processarMovimento();

        Gdx.gl.glClearColor(
            0.1f,
            0.2f,
            0.1f,
            1f
        );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        desenharGrid();
        desenharJogador();
        desenharHud();
    }

    private void processarMovimento() {

        boolean moveu = false;

        if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            contexto.linhaJogador++;
            moveu = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            contexto.linhaJogador--;
            moveu = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            contexto.colunaJogador--;
            moveu = true;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            contexto.colunaJogador++;
            moveu = true;
        }

        contexto.linhaJogador =
            Math.max(
                0,
                Math.min(
                    MAP_HEIGHT - 1,
                    contexto.linhaJogador
                )
            );

        contexto.colunaJogador =
            Math.max(
                0,
                Math.min(
                    MAP_WIDTH - 1,
                    contexto.colunaJogador
                )
            );

        if (moveu) {
            verificarEncontroAleatorio();
        }
    }

    private void verificarEncontroAleatorio() {

        float chance = (float) Math.random();

        if (chance < 0.10f) {

            System.out.println(
                "Encontro aleatório!"
            );

            jogo.setScreen(
                new TelaBatalha(
                    jogo,
                    contexto,
                    this,
                    false
                )
            );
        }
    }

    private void desenharGrid() {

        renderer.begin(
            ShapeRenderer.ShapeType.Line
        );

        renderer.setColor(Color.DARK_GRAY);

        for (int x = 0; x <= MAP_WIDTH; x++) {

            renderer.line(
                x * TILE_SIZE,
                0,
                x * TILE_SIZE,
                MAP_HEIGHT * TILE_SIZE
            );
        }

        for (int y = 0; y <= MAP_HEIGHT; y++) {

            renderer.line(
                0,
                y * TILE_SIZE,
                MAP_WIDTH * TILE_SIZE,
                y * TILE_SIZE
            );
        }

        renderer.end();
    }

    private void desenharJogador() {

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        renderer.setColor(Color.YELLOW);

        renderer.circle(
            contexto.colunaJogador * TILE_SIZE + TILE_SIZE / 2f,
            contexto.linhaJogador * TILE_SIZE + TILE_SIZE / 2f,
            12
        );

        renderer.end();
    }

    private void desenharHud() {

        batch.begin();

        font.draw(
            batch,
            "ALDORIA RPG",
            20,
            Gdx.graphics.getHeight() - 20
        );

        font.draw(
            batch,
            "Heroi: " +
                contexto.grupo.get(0).getNome(),
            20,
            Gdx.graphics.getHeight() - 50
        );

        font.draw(
            batch,
            "Posicao: (" +
                contexto.colunaJogador +
                ", " +
                contexto.linhaJogador +
                ")",
            20,
            Gdx.graphics.getHeight() - 80
        );

        font.draw(
            batch,
            "W A S D para mover",
            20,
            30
        );

        batch.end();
    }

    @Override
    public void resize(
        int width,
        int height
    ) {
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

        if (renderer != null) {
            renderer.dispose();
        }
    }
}
