package io.github.Aldoria.telas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Aldoria.GameContext;
import io.github.Aldoria.Main;
import io.github.Aldoria.model.entidades.Heroi;
import io.github.Aldoria.model.itens.Item;
import io.github.Aldoria.model.itens.ItemArma;
import io.github.Aldoria.model.itens.ItemArmadura;
import io.github.Aldoria.model.itens.ItemAcessorio;

public class TelaEquipamentos implements Screen {

    private final Main jogo;
    private final GameContext contexto;
    private final Screen telaAnterior;

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer renderer;

    private int heroiSelecionado = 0;
    private int itemSelecionado = 0;

    private boolean escolhendoHeroi = true;

    public TelaEquipamentos(
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
        renderer = new ShapeRenderer();

        font.getData().setScale(1.3f);
    }

    @Override
    public void render(float delta) {

        processarEntrada();

        Gdx.gl.glClearColor(
            0.08f,
            0.08f,
            0.12f,
            1f
        );

        Gdx.gl.glClear(
            GL20.GL_COLOR_BUFFER_BIT
        );

        desenharFundo();
        desenharHerois();
        desenharInventarioHeroi();
        desenharEquipamentos();
        desenharAjuda();
    }

    private void processarEntrada() {

        if (Gdx.input.isKeyJustPressed(
            Input.Keys.ESCAPE
        )) {

            jogo.setScreen(
                telaAnterior
            );

            return;
        }

        if (Gdx.input.isKeyJustPressed(
            Input.Keys.BACKSPACE
        )) {

            escolhendoHeroi = true;
        }

        if (Gdx.input.isKeyJustPressed(
            Input.Keys.UP
        )) {

            if (escolhendoHeroi) {

                heroiSelecionado--;

                if (heroiSelecionado < 0) {

                    heroiSelecionado =
                        contexto.grupo.size() - 1;
                }

            } else {

                Heroi heroi =
                    contexto.grupo.get(
                        heroiSelecionado
                    );

                if (!heroi.getInventario().isEmpty()) {

                    itemSelecionado--;

                    if (itemSelecionado < 0) {

                        itemSelecionado =
                            heroi.getInventario().size() - 1;
                    }
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(
            Input.Keys.DOWN
        )) {

            if (escolhendoHeroi) {

                heroiSelecionado++;

                if (heroiSelecionado >=
                    contexto.grupo.size()) {

                    heroiSelecionado = 0;
                }

            } else {

                Heroi heroi =
                    contexto.grupo.get(
                        heroiSelecionado
                    );

                if (!heroi.getInventario().isEmpty()) {

                    itemSelecionado++;

                    if (itemSelecionado >=
                        heroi.getInventario().size()) {

                        itemSelecionado = 0;
                    }
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(
            Input.Keys.ENTER
        )) {

            if (escolhendoHeroi) {

                escolhendoHeroi = false;

            } else {

                equiparItemSelecionado();
            }
        }
    }

    private void equiparItemSelecionado() {

        Heroi heroi =
            contexto.grupo.get(
                heroiSelecionado
            );

        if (heroi.getInventario().isEmpty()) {

            return;
        }

        Item item =
            heroi.getInventario().get(
                itemSelecionado
            );

        if (item instanceof ItemArma) {

            heroi.equiparArma(
                (ItemArma) item
            );
        }

        if (item instanceof ItemArmadura) {

            heroi.equiparArmadura(
                (ItemArmadura) item
            );
        }

        if (item instanceof ItemAcessorio) {

            heroi.equiparAcessorio(
                (ItemAcessorio) item
            );
        }
    }

    private void desenharFundo() {

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        renderer.setColor(
            0.12f,
            0.12f,
            0.18f,
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

        batch.begin();

        font.draw(
            batch,
            "=== HEROIS ===",
            40,
            520
        );

        for (
            int i = 0;
            i < contexto.grupo.size();
            i++
        ) {

            Heroi heroi =
                contexto.grupo.get(i);

            String texto =
                (i == heroiSelecionado
                    ? "> "
                    : "  ")
                    + heroi.getNome();

            font.draw(
                batch,
                texto,
                40,
                480 - (i * 35)
            );
        }

        batch.end();
    }

    private void desenharInventarioHeroi() {

        Heroi heroi =
            contexto.grupo.get(
                heroiSelecionado
            );

        batch.begin();

        font.draw(
            batch,
            "=== INVENTARIO ===",
            300,
            520
        );

        if (
            heroi.getInventario().isEmpty()
        ) {

            font.draw(
                batch,
                "Nenhum item",
                300,
                480
            );

        } else {

            for (
                int i = 0;
                i < heroi.getInventario().size();
                i++
            ) {

                Item item =
                    heroi.getInventario().get(i);

                String texto =
                    (i == itemSelecionado &&
                        !escolhendoHeroi
                        ? "> "
                        : "  ")
                        + item.getNome();

                font.draw(
                    batch,
                    texto,
                    300,
                    480 - (i * 30)
                );
            }
        }

        batch.end();
    }
    private void desenharAjuda() {

        batch.begin();

        font.draw(
            batch,
            "ENTER = Equipar item",
            20,
            90
        );

        font.draw(
            batch,
            "BACKSPACE = Voltar para herois",
            20,
            65
        );

        font.draw(
            batch,
            "ESC = Voltar ao mapa",
            20,
            40
        );

        if (escolhendoHeroi) {

            font.draw(
                batch,
                "Selecione um heroi",
                320,
                90
            );

        } else {

            font.draw(
                batch,
                "Selecione um equipamento",
                320,
                90
            );
        }

        batch.end();
    }

    private void desenharEquipamentos() {

        Heroi heroi =
            contexto.grupo.get(
                heroiSelecionado
            );

        batch.begin();

        font.draw(
            batch,
            "===== EQUIPADO =====",
            620,
            520
        );

        String arma = "Nenhuma";

        if (heroi.getArmaEquipada() != null) {

            arma =
                heroi.getArmaEquipada()
                    .getNome();
        }

        String armadura = "Nenhuma";

        if (heroi.getArmaduraEquipada() != null) {

            armadura =
                heroi.getArmaduraEquipada()
                    .getNome();
        }

        String acessorio = "Nenhum";

        if (heroi.getAcessorioEquipado() != null) {

            acessorio =
                heroi.getAcessorioEquipado()
                    .getNome();
        }

        font.draw(
            batch,
            "Arma:",
            620,
            470
        );

        font.draw(
            batch,
            arma,
            620,
            445
        );

        font.draw(
            batch,
            "Armadura:",
            620,
            395
        );

        font.draw(
            batch,
            armadura,
            620,
            370
        );

        font.draw(
            batch,
            "Acessorio:",
            620,
            320
        );

        font.draw(
            batch,
            acessorio,
            620,
            295
        );

        font.draw(
            batch,
            "ATK: " + heroi.getAtaque(),
            620,
            220
        );

        font.draw(
            batch,
            "DEF: " + heroi.getDefesa(),
            620,
            195
        );

        font.draw(
            batch,
            "HP : "
                + heroi.getHpAtual()
                + "/"
                + heroi.getHpMaximo(),
            620,
            170
        );

        font.draw(
            batch,
            "MP : "
                + heroi.getMpAtual()
                + "/"
                + heroi.getMpMaximo(),
            620,
            145
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
