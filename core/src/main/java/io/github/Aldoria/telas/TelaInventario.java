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

public class TelaInventario implements Screen {

    private final Main jogo;
    private final GameContext contexto;
    private final Screen telaAnterior;

    private SpriteBatch batch;
    private BitmapFont font;
    private ShapeRenderer renderer;

    private int itemSelecionado = 0;
    private int heroiSelecionado = 0;

    private boolean escolhendoHeroi = false;

    public TelaInventario(
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
            0.05f,
            0.05f,
            0.10f,
            1f
        );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        desenharFundo();
        desenharInventario();
        desenharHerois();
        desenharAjuda();
    }

    private void processarEntrada() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {

            jogo.setScreen(telaAnterior);
            return;
        }

        if (!escolhendoHeroi) {

            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {

                itemSelecionado--;

                if (itemSelecionado < 0) {

                    itemSelecionado =
                        contexto.inventario.size() - 1;
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

                itemSelecionado++;

                if (itemSelecionado >= contexto.inventario.size()) {

                    itemSelecionado = 0;
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

                if (!contexto.inventario.isEmpty()) {

                    escolhendoHeroi = true;
                }
            }

        } else {

            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {

                heroiSelecionado--;

                if (heroiSelecionado < 0) {

                    heroiSelecionado =
                        contexto.grupo.size() - 1;
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

                heroiSelecionado++;

                if (heroiSelecionado >= contexto.grupo.size()) {

                    heroiSelecionado = 0;
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

                distribuirItem();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)) {

                escolhendoHeroi = false;
            }
        }
    }

    private void distribuirItem() {

        if (contexto.inventario.isEmpty()) {

            escolhendoHeroi = false;
            return;
        }

        Item item =
            contexto.inventario.get(itemSelecionado);

        Heroi heroi =
            contexto.grupo.get(heroiSelecionado);

        heroi.adicionarItem(item);

        contexto.inventario.remove(item);

        if (itemSelecionado >= contexto.inventario.size()) {

            itemSelecionado =
                Math.max(
                    0,
                    contexto.inventario.size() - 1
                );
        }

        escolhendoHeroi = false;
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

        renderer.setColor(
            0.18f,
            0.18f,
            0.25f,
            1f
        );

        renderer.rect(
            20,
            20,
            420,
            520
        );

        renderer.rect(
            470,
            20,
            490,
            520
        );

        renderer.end();
    }

    private void desenharInventario() {

        batch.begin();

        font.draw(
            batch,
            "=== INVENTARIO DO GRUPO ===",
            40,
            510
        );

        if (contexto.inventario.isEmpty()) {

            font.draw(
                batch,
                "Nenhum item encontrado.",
                40,
                470
            );

        } else {

            for (int i = 0;
                 i < contexto.inventario.size();
                 i++) {

                Item item =
                    contexto.inventario.get(i);

                String texto;

                if (i == itemSelecionado &&
                    !escolhendoHeroi) {

                    texto = "> ";

                } else {

                    texto = "  ";
                }

                texto += item.getNome();

                if (item instanceof ItemArma) {

                    texto +=
                        "  ATK +"
                            + ((ItemArma) item).getDano();

                } else if (item instanceof ItemArmadura) {

                    texto +=
                        "  ARM";

                } else if (item instanceof ItemAcessorio) {

                    texto +=
                        "  ACC";
                }

                font.draw(
                    batch,
                    texto,
                    40,
                    470 - (i * 30)
                );
            }
        }

        batch.end();
    }

    private void desenharHerois() {

        batch.begin();

        font.draw(
            batch,
            "=== HEROIS ===",
            500,
            510
        );

        for (int i = 0;
             i < contexto.grupo.size();
             i++) {

            Heroi heroi =
                contexto.grupo.get(i);

            String texto;

            if (i == heroiSelecionado &&
                escolhendoHeroi) {

                texto = "> ";

            } else {

                texto = "  ";
            }

            texto += heroi.getNome();

            font.draw(
                batch,
                texto,
                500,
                470 - (i * 90)
            );

            font.draw(
                batch,
                "HP "
                    + heroi.getHpAtual()
                    + "/"
                    + heroi.getHpMaximo(),
                530,
                445 - (i * 90)
            );

            font.draw(
                batch,
                "MP "
                    + heroi.getMpAtual()
                    + "/"
                    + heroi.getMpMaximo(),
                530,
                425 - (i * 90)
            );

            if (heroi.getArmaEquipada() != null) {

                font.draw(
                    batch,
                    "Arma: "
                        + heroi.getArmaEquipada().getNome(),
                    530,
                    405 - (i * 90)
                );
            } else {

                font.draw(
                    batch,
                    "Arma: Nenhuma",
                    530,
                    405 - (i * 90)
                );
            }

            if (heroi.getArmaduraEquipada() != null) {

                font.draw(
                    batch,
                    "Armadura: "
                        + heroi.getArmaduraEquipada().getNome(),
                    530,
                    385 - (i * 90)
                );
            }

            if (heroi.getAcessorioEquipado() != null) {

                font.draw(
                    batch,
                    "Acessorio: "
                        + heroi.getAcessorioEquipado().getNome(),
                    530,
                    365 - (i * 90)
                );
            }
        }

        batch.end();
    }
    private void desenharAjuda() {

        batch.begin();

        font.draw(
            batch,
            "ENTER = Distribuir Item",
            30,
            80
        );

        font.draw(
            batch,
            "BACKSPACE = Voltar para lista",
            30,
            55
        );

        font.draw(
            batch,
            "ESC = Voltar ao mapa",
            30,
            30
        );

        if (escolhendoHeroi) {

            font.draw(
                batch,
                "Selecione um heroi para receber o item",
                320,
                80
            );
        }

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
