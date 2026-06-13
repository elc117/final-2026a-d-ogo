package io.github.Aldoria.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Aldoria.GameContext;
import io.github.Aldoria.Main;

public class TelaMenuPrincipal implements Screen {

    private final Main jogo;
    private final GameContext contexto;

    private SpriteBatch loteSprites;
    private BitmapFont fonte;
    private BitmapFont fonteTitulo;
    private ShapeRenderer renderizadorFormas;

    private static final float LARGURA_BOTAO = 200f;
    private static final float ALTURA_BOTAO = 50f;

    private float botaoIniciarX;
    private float botaoIniciarY;

    private float botaoSairX;
    private float botaoSairY;

    public TelaMenuPrincipal(Main jogo, GameContext contexto) {
        this.jogo = jogo;
        this.contexto = contexto;
    }

    @Override
    public void show() {
        loteSprites = new SpriteBatch();
        fonte = new BitmapFont();
        fonteTitulo = new BitmapFont();
        renderizadorFormas = new ShapeRenderer();

        fonte.getData().setScale(1.5f);
        fonteTitulo.getData().setScale(3f);

        float centroX = Gdx.graphics.getWidth() / 2f;
        float centroY = Gdx.graphics.getHeight() / 2f;

        botaoIniciarX = centroX - LARGURA_BOTAO / 2f;
        botaoIniciarY = centroY - 20f;

        botaoSairX = centroX - LARGURA_BOTAO / 2f;
        botaoSairY = centroY - 90f;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.05f, 0.05f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float alturaTela = Gdx.graphics.getHeight();

        renderizadorFormas.begin(ShapeRenderer.ShapeType.Filled);

        float mouseX = Gdx.input.getX();
        float mouseY = alturaTela - Gdx.input.getY();

        boolean sobreIniciar =
            mouseX >= botaoIniciarX &&
                mouseX <= botaoIniciarX + LARGURA_BOTAO &&
                mouseY >= botaoIniciarY &&
                mouseY <= botaoIniciarY + ALTURA_BOTAO;

        boolean sobreSair =
            mouseX >= botaoSairX &&
                mouseX <= botaoSairX + LARGURA_BOTAO &&
                mouseY >= botaoSairY &&
                mouseY <= botaoSairY + ALTURA_BOTAO;

        renderizadorFormas.setColor(sobreIniciar ? new Color(0.4f, 0.7f, 0.4f, 1f)
            : new Color(0.2f, 0.5f, 0.2f, 1f));

        renderizadorFormas.rect(botaoIniciarX, botaoIniciarY, LARGURA_BOTAO, ALTURA_BOTAO);

        renderizadorFormas.setColor(sobreSair ? new Color(0.7f, 0.3f, 0.3f, 1f)
            : new Color(0.5f, 0.15f, 0.15f, 1f));

        renderizadorFormas.rect(botaoSairX, botaoSairY, LARGURA_BOTAO, ALTURA_BOTAO);

        renderizadorFormas.end();

        loteSprites.begin();

        fonteTitulo.setColor(new Color(0.9f, 0.7f, 0.2f, 1f));

        float centroX = Gdx.graphics.getWidth() / 2f;

        fonteTitulo.draw(loteSprites, "Ashes of the", centroX - 200f, alturaTela - 80f);
        fonteTitulo.draw(loteSprites, "Forgotten Realm", centroX - 250f, alturaTela - 140f);

        fonte.setColor(Color.WHITE);

        fonte.draw(loteSprites, "INICIAR JOGO", botaoIniciarX + 30f, botaoIniciarY + 33f);
        fonte.draw(loteSprites, "SAIR", botaoSairX + 75f, botaoSairY + 33f);

        loteSprites.end();

        if (Gdx.input.isTouched()) {

            float toqueX = Gdx.input.getX();
            float toqueY = alturaTela - Gdx.input.getY();

            if (toqueX >= botaoIniciarX && toqueY >= botaoIniciarY &&
                toqueX <= botaoIniciarX + LARGURA_BOTAO &&
                toqueY <= botaoIniciarY + ALTURA_BOTAO) {

                jogo.setScreen(new TelaMapa(jogo, contexto));
            }

            if (toqueX >= botaoSairX && toqueY >= botaoSairY &&
                toqueX <= botaoSairX + LARGURA_BOTAO &&
                toqueY <= botaoSairY + ALTURA_BOTAO) {

                Gdx.app.exit();
            }
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        loteSprites.dispose();
        fonte.dispose();
        fonteTitulo.dispose();
        renderizadorFormas.dispose();
    }
}
