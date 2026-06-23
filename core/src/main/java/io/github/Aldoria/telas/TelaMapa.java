package io.github.Aldoria.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Aldoria.GameContext;
import io.github.Aldoria.Main;
import io.github.Aldoria.controle.ControleSpawnerInimigo;
import io.github.Aldoria.model.entidades.Inimigo;

public class TelaMapa implements Screen {

    private static final int TILE_SIZE = 48;
    private static final int MAP_WIDTH = 20;
    private static final int MAP_HEIGHT = 12;

    private final Main jogo;
    private final GameContext contexto;

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer renderer;

    private Texture mapa;

    private final ControleSpawnerInimigo spawner;

    public TelaMapa(
        Main jogo,
        GameContext contexto
    ) {

        this.jogo = jogo;
        this.contexto = contexto;

        this.spawner =
            new ControleSpawnerInimigo();
    }

    @Override
    public void show() {

        batch = new SpriteBatch();

        font = new BitmapFont();

        font.getData().setScale(1.2f);

        renderer =
            new ShapeRenderer();

        mapa =
            new Texture("mapa.png");
    }

    @Override
    public void render(float delta) {

        processarMovimento();

        Gdx.gl.glClearColor(
            0f,
            0f,
            0f,
            1f
        );

        Gdx.gl.glClear(
            GL20.GL_COLOR_BUFFER_BIT
        );

        batch.begin();

        batch.draw(

            mapa,

            0,

            0,

            Gdx.graphics.getWidth(),

            Gdx.graphics.getHeight()

        );

        batch.end();

        desenharJogador();

        desenharHud();
    }

    private void desenharJogador() {

        float x =

            contexto.colunaJogador *

                TILE_SIZE +

                TILE_SIZE / 2f;

        float y =

            contexto.linhaJogador *

                TILE_SIZE +

                TILE_SIZE / 2f;

        renderer.begin(

            ShapeRenderer.ShapeType.Filled

        );

        renderer.setColor(

            Color.YELLOW

        );

        renderer.circle(

            x,

            y,

            12

        );

        renderer.end();
    }

    private void desenharHud() {

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        renderer.setColor(
            0f,
            0f,
            0f,
            0.70f
        );

        renderer.rect(
            0,
            Gdx.graphics.getHeight() - 180,
            430,
            180
        );

        renderer.end();

        batch.begin();

        font.draw(
            batch,
            "ALDORIA RPG",
            20,
            Gdx.graphics.getHeight() - 20
        );

        font.draw(
            batch,
            "Grupo: " + contexto.grupo.size(),
            20,
            Gdx.graphics.getHeight() - 50
        );

        font.draw(
            batch,
            "Derrotados: " + contexto.inimigosDerrotados,
            20,
            Gdx.graphics.getHeight() - 80
        );

        font.draw(
            batch,
            "Inventario: " + contexto.inventario.size(),
            20,
            Gdx.graphics.getHeight() - 110
        );

        font.draw(
            batch,
            "Posicao: "
                + contexto.colunaJogador
                + ", "
                + contexto.linhaJogador,
            20,
            Gdx.graphics.getHeight() - 140
        );

        font.draw(
            batch,
            "WASD=Mover  I=Inventario  G=Grupo  E=Equipamentos",
            20,
            30
        );

        batch.end();
    }

    private void processarMovimento() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {

            jogo.setScreen(

                new TelaInventario(
                    jogo,
                    contexto,
                    this
                )

            );

            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {

            jogo.setScreen(

                new TelaGrupo(
                    jogo,
                    contexto,
                    this
                )

            );

            return;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {

            jogo.setScreen(

                new TelaEquipamentos(
                    jogo,
                    contexto,
                    this
                )

            );

            return;
        }

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

        contexto.linhaJogador = Math.max(
            0,
            Math.min(
                MAP_HEIGHT - 1,
                contexto.linhaJogador
            )
        );

        contexto.colunaJogador = Math.max(
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

            contexto.inimigos.clear();

            Inimigo inimigo =

                spawner.gerarInimigo(

                    contexto.inimigosDerrotados

                );

            contexto.inimigos.add(

                inimigo

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

        if (mapa != null) {

            mapa.dispose();

        }
    }
}
