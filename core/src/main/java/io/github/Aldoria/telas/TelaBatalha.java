package io.github.Aldoria.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Aldoria.GameContext;
import io.github.Aldoria.Main;
import io.github.Aldoria.model.skill.Skill;

public class TelaBatalha implements Screen {

    private enum EstadoBatalha {
        TURNO_JOGADOR,
        SELECIONAR_SKILL,
        SELECIONAR_ITEM,
        SELECIONAR_ALVO_INIMIGO,
        SELECIONAR_ALVO_ALIADO,
        TURNO_INIMIGO,
        BATALHA_ENCERRADA
    }

    private static final String[] MENU_PRINCIPAL = {
        "Atacar",
        "Magia",
        "Item",
        "Defender",
        "Fugir"
    };

    private final Main jogo;
    private final GameContext contexto;
    private final Screen telaRetorno;
    private final boolean batalhaChefe;

    private SpriteBatch loteSprites;
    private ShapeRenderer renderizadorFormas;
    private BitmapFont fonte;

    private EstadoBatalha estado = EstadoBatalha.TURNO_JOGADOR;

    private int cursorMenu = 0;
    private int cursorSubmenu = 0;

    private String logBatalha = "";
    private float tempoLog = 0f;
    private float atrasoTurnoInimigo = 0f;

    private Skill skillPendente = null;

    public TelaBatalha(
        Main jogo,
        GameContext contexto,
        Screen telaRetorno,
        boolean batalhaChefe
    ) {
        this.jogo = jogo;
        this.contexto = contexto;
        this.telaRetorno = telaRetorno;
        this.batalhaChefe = batalhaChefe;
    }

    @Override
    public void show() {
        loteSprites = new SpriteBatch();
        renderizadorFormas = new ShapeRenderer();
        fonte = new BitmapFont();

        fonte.getData().setScale(1.3f);

        logBatalha = "Batalha iniciada";
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.05f, 0.03f, 0.08f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (tempoLog > 0) {
            tempoLog -= delta;
        }

        if (atrasoTurnoInimigo > 0) {

            atrasoTurnoInimigo -= delta;

            if (atrasoTurnoInimigo <= 0 &&
                estado == EstadoBatalha.TURNO_INIMIGO) {

                executarTurnoInimigo();
            }
        }

        renderizarInimigos();
        renderizarGrupo();
        renderizarMenu();
        renderizarLog();

        tratarEntrada();
        verificarFimBatalha();
    }

    private void executarTurnoInimigo() {
    }

    private void renderizarInimigos() {
    }

    private void renderizarGrupo() {
    }

    private void renderizarMenu() {
    }

    private void renderizarLog() {
    }

    private void tratarEntrada() {
    }

    private void verificarFimBatalha() {
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

        if (loteSprites != null) {
            loteSprites.dispose();
        }

        if (renderizadorFormas != null) {
            renderizadorFormas.dispose();
        }

        if (fonte != null) {
            fonte.dispose();
        }
    }
}
