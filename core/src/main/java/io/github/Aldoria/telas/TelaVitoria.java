package io.github.Aldoria.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.Aldoria.GameContext;
import io.github.Aldoria.Main;
import io.github.Aldoria.model.entidades.Heroi;

public class TelaVitoria implements Screen {

    private final Main jogo;
    private final GameContext contexto;

    private SpriteBatch loteSprites;
    private BitmapFont fonte;
    private BitmapFont fonteGrande;

    public TelaVitoria(Main jogo, GameContext contexto) {
        this.jogo = jogo;
        this.contexto = contexto;
    }

    @Override
    public void show() {

        loteSprites = new SpriteBatch();

        fonte = new BitmapFont();
        fonteGrande = new BitmapFont();

        fonte.getData().setScale(1.4f);
        fonteGrande.getData().setScale(3f);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.02f, 0.08f, 0.02f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float larguraTela = Gdx.graphics.getWidth();
        float alturaTela = Gdx.graphics.getHeight();
        float centroX = larguraTela / 2f;

        loteSprites.begin();

        fonteGrande.setColor(Color.GOLD);
        fonteGrande.draw(
            loteSprites,
            "VITÓRIA!",
            centroX - 130f,
            alturaTela - 80f
        );

        fonte.setColor(Color.WHITE);

        fonte.draw(
            loteSprites,
            "O Sem-Nome foi derrotado!",
            centroX - 150f,
            alturaTela - 180f
        );

        fonte.draw(
            loteSprites,
            "Aldoria está salva.",
            centroX - 100f,
            alturaTela - 220f
        );

        fonte.setColor(Color.LIGHT_GRAY);

        fonte.draw(
            loteSprites,
            "Heróis sobreviventes:",
            centroX - 110f,
            alturaTela - 290f
        );

        float posicaoY = alturaTela - 320f;

        for (Heroi heroi : contexto.grupo) {

            if(!heroi.estaVivo()) continue;
        }

        fonte.setColor(Color.GRAY);

        fonte.draw(
            loteSprites,
            "Pressione ENTER para voltar ao menu principal",
            centroX - 220f,
            60f
        );

        loteSprites.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            jogo.setScreen(
                new TelaMenuPrincipal(jogo, contexto)
            );
        }
    }

    @Override
    public void resize(int largura, int altura) {
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

        loteSprites.dispose();
        fonte.dispose();
        fonteGrande.dispose();
    }
}
