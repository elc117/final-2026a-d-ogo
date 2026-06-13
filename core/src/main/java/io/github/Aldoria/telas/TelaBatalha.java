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

public class TelaBatalha implements Screen {

    private final Main jogo;
    private final GameContext contexto;
    private final Screen telaRetorno;
    private final boolean batalhaChefe;

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private BitmapFont font;

    private int opcaoSelecionada = 0;
    private int heroiSelecionado = 0;

    private boolean escolhendoHeroi = true;

    private final String[] menu = {
        "Atacar",
        "Skill",
        "Item",
        "Fugir"
    };

    private int hpInimigo = 100;
    private int hpMaximoInimigo = 100;

    private String mensagem = "Um Slime apareceu!";

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

        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        font = new BitmapFont();

        font.getData().setScale(1.4f);
    }

    @Override
    public void render(float delta) {

        processarEntrada();

        Gdx.gl.glClearColor(
            0.05f,
            0.05f,
            0.15f,
            1f
        );

        Gdx.gl.glClear(
            GL20.GL_COLOR_BUFFER_BIT
        );

        desenharFundo();
        desenharInimigo();
        desenharHerois();
        desenharPainel();
        desenharMensagem();

        verificarFimDaBatalha();
    }

    private void processarEntrada() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {

            if (escolhendoHeroi) {

                heroiSelecionado--;

                if (heroiSelecionado < 0) {
                    heroiSelecionado =
                        contexto.grupo.size() - 1;
                }

            } else {

                opcaoSelecionada--;

                if (opcaoSelecionada < 0) {
                    opcaoSelecionada =
                        menu.length - 1;
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

            if (escolhendoHeroi) {

                heroiSelecionado++;

                if (heroiSelecionado >= contexto.grupo.size()) {
                    heroiSelecionado = 0;
                }

            } else {

                opcaoSelecionada++;

                if (opcaoSelecionada >= menu.length) {
                    opcaoSelecionada = 0;
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

            if (escolhendoHeroi) {

                escolhendoHeroi = false;

            } else {

                executarOpcao();

                escolhendoHeroi = true;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            escolhendoHeroi = true;
        }
    }

    private void executarOpcao() {

        String nomeHeroi =
            contexto.grupo
                .get(heroiSelecionado)
                .getNome();

        switch (opcaoSelecionada) {

            case 0:

                int dano =
                    10 +
                        (int)(Math.random() * 15);

                hpInimigo -= dano;

                if (hpInimigo < 0) {
                    hpInimigo = 0;
                }

                mensagem =
                    nomeHeroi +
                        " causou " +
                        dano +
                        " de dano!";

                break;

            case 1:

                mensagem =
                    nomeHeroi +
                        " tentou usar Skill.";

                break;

            case 2:

                mensagem =
                    nomeHeroi +
                        " tentou usar Item.";

                break;

            case 3:

                jogo.setScreen(
                    telaRetorno
                );

                break;
        }
    }

    private void desenharFundo() {

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        renderer.setColor(
            0.15f,
            0.15f,
            0.25f,
            1f
        );

        renderer.rect(
            0,
            0,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight()
        );

        renderer.end();
    }

    private void desenharHerois() {

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        int quantidade =
            contexto.grupo.size();

        for (int i = 0; i < quantidade; i++) {

            if (i == heroiSelecionado) {

                renderer.setColor(
                    Color.GOLD
                );

            } else {

                renderer.setColor(
                    Color.YELLOW
                );
            }

            renderer.circle(
                120 + (i * 120),
                220,
                30
            );
        }

        renderer.end();

        batch.begin();

        for (int i = 0; i < quantidade; i++) {

            font.draw(
                batch,
                contexto.grupo
                    .get(i)
                    .getNome(),
                80 + (i * 120),
                170
            );
        }

        batch.end();
    }

    private void desenharInimigo() {

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        renderer.setColor(Color.RED);

        renderer.circle(
            850,
            320,
            45
        );

        renderer.end();

        float larguraBarra = 220f;

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        renderer.setColor(Color.DARK_GRAY);

        renderer.rect(
            730,
            400,
            larguraBarra,
            18
        );

        renderer.setColor(Color.GREEN);

        renderer.rect(
            730,
            400,
            larguraBarra *
                ((float) hpInimigo /
                    hpMaximoInimigo),
            18
        );

        renderer.end();
    }

    private void desenharPainel() {

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        renderer.setColor(
            0f,
            0f,
            0f,
            0.8f
        );

        renderer.rect(
            0,
            0,
            Gdx.graphics.getWidth(),
            180
        );

        renderer.end();

        batch.begin();

        if (escolhendoHeroi) {

            font.draw(
                batch,
                "Escolha o Heroi",
                40,
                160
            );

            for (int i = 0;
                 i < contexto.grupo.size();
                 i++) {

                String texto =
                    (i == heroiSelecionado
                        ? "> "
                        : "  ")
                        +
                        contexto.grupo
                            .get(i)
                            .getNome();

                font.draw(
                    batch,
                    texto,
                    40,
                    130 - (i * 30)
                );
            }

        } else {

            font.draw(
                batch,
                "Acao",
                40,
                160
            );

            for (int i = 0;
                 i < menu.length;
                 i++) {

                String texto =
                    (i == opcaoSelecionada
                        ? "> "
                        : "  ")
                        + menu[i];

                font.draw(
                    batch,
                    texto,
                    40,
                    130 - (i * 30)
                );
            }
        }

        batch.end();
    }

    private void desenharMensagem() {

        batch.begin();

        font.draw(
            batch,
            "SLIME",
            800,
            450
        );

        font.draw(
            batch,
            "HP: "
                + hpInimigo
                + "/"
                + hpMaximoInimigo,
            730,
            390
        );

        font.draw(
            batch,
            mensagem,
            300,
            80
        );

        batch.end();
    }

    private void verificarFimDaBatalha() {

        if (hpInimigo <= 0) {

            mensagem = "Vitoria!";

            jogo.setScreen(
                telaRetorno
            );
        }
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

        if (renderer != null) {
            renderer.dispose();
        }

        if (font != null) {
            font.dispose();
        }
    }
}
