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
import io.github.Aldoria.model.entidades.Heroi;
import io.github.Aldoria.model.itens.ItemConsumivel;

import io.github.Aldoria.controle.ControleSpawnerInimigo;
import io.github.Aldoria.model.entidades.Inimigo;

import java.util.Random;

public class TelaBatalha implements Screen {

    private final Main jogo;
    private final GameContext contexto;
    private final Screen telaRetorno;
    private final boolean batalhaChefe;
    private ControleSpawnerInimigo spawner;

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private BitmapFont font;

    private int opcaoSelecionada = 0;
    private int heroiSelecionado = 0;

    private boolean escolhendoHeroi = true;
    private boolean usandoItem = false;

    private int itemSelecionado = 0;

    private boolean escolhendoAlvoCura = false;
    private boolean escolhendoAlvoReviver = false;
    private int alvoSelecionado = 0;


    private final String[] menu = {
        "Atacar",
        "Skill",
        "Item",
        "Fugir"
    };

    private int hpInimigo = 100;
    private int hpMaximoInimigo = 100;

    private String mensagem = "";
    private String nomeInimigo = "Slime";
    private int ataqueInimigo = 10;

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
        spawner = new ControleSpawnerInimigo();

        font.getData().setScale(1.4f);

        if (!contexto.inimigos.isEmpty()) {

            Inimigo inimigo =
                contexto.inimigos.get(0);

            nomeInimigo =
                inimigo.getNome();

            hpInimigo =
                inimigo.getHpAtual();

            hpMaximoInimigo =
                inimigo.getHpMaximo();

            ataqueInimigo =
                inimigo.getAtaque();

            mensagem =
                "Um " + nomeInimigo + " apareceu!";
        }
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

        if (usandoItem) {
            desenharMenuItens();
        }

        verificarFimDaBatalha();
    }

    private void desenharMenuItens() {

        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        renderer.setColor(
            0f,
            0f,
            0f,
            0.95f
        );

        renderer.rect(
            250,
            100,
            500,
            300
        );

        renderer.end();

        batch.begin();

        font.draw(
            batch,
            "ITENS",
            300,
            370
        );

        for (int i = 0;
             i < contexto.inventario.size();
             i++) {

            String texto =
                (i == itemSelecionado
                    ? "> "
                    : "  ")
                    +
                    contexto.inventario
                        .get(i)
                        .getNome();

            font.draw(
                batch,
                texto,
                300,
                330 - (i * 30)
            );
        }

        batch.end();
    }

    private void processarEntrada() {

        if (escolhendoAlvoCura ||
            escolhendoAlvoReviver) {

            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {

                alvoSelecionado--;

                if (alvoSelecionado < 0) {

                    alvoSelecionado =
                        contexto.grupo.size() - 1;
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

                alvoSelecionado++;

                if (alvoSelecionado >= contexto.grupo.size()) {

                    alvoSelecionado = 0;
                }
            }

            return;
        }

        if (usandoItem) {

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

            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {

                usandoItem = false;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

                usarItemSelecionado();
            }

            return;
        }

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

    private void usarItemSelecionado() {

        if (contexto.inventario.isEmpty()) {

            mensagem = "Inventario vazio";
            usandoItem = false;
            return;
        }

        Heroi alvo =
            contexto.grupo.get(
                heroiSelecionado
            );

        ItemConsumivel item =
            (ItemConsumivel)
                contexto.inventario.get(
                    itemSelecionado
                );

        if (item.getNome()
            .equals("Pocao de Vida")) {

            alvo.curar(
                item.getValor()
            );

            mensagem =
                alvo.getNome()
                    + " recuperou "
                    + item.getValor()
                    + " HP";
        }

        if (item.getNome()
            .equals("Pocao de Mana")) {

            if (alvo.getNome()
                .equalsIgnoreCase("Mago")) {

                alvo.recuperarCargaMagia();

                mensagem =
                    "Carga magica recuperada";
            }

            if (alvo.getNome()
                .equalsIgnoreCase("Clerigo")) {

                alvo.recuperarCargaDivina();

                mensagem =
                    "Carga divina recuperada";
            }
        }

        contexto.inventario.remove(
            itemSelecionado
        );

        usandoItem = false;
    }

    private void executarOpcao() {

        Heroi heroi =
            contexto.grupo.get(heroiSelecionado);

        if (!heroi.estaVivo()) {

            mensagem =
                heroi.getNome()
                    + " esta morto!";

            return;
        }

        switch (opcaoSelecionada) {

            case 0:

                int dano = heroi.getAtaque();

                hpInimigo -= dano;

                if (hpInimigo < 0) {
                    hpInimigo = 0;
                }

                mensagem =
                    heroi.getNome()
                        + " causou "
                        + dano
                        + " de dano!";

                if (hpInimigo > 0) {
                    turnoInimigo();
                }

                break;

            case 1:

                if (heroi.getNome().equalsIgnoreCase("Mago")) {

                    if (!heroi.usarCargaMagia()) {

                        mensagem =
                            "Sem cargas de magia!";

                        break;
                    }

                    int danoMagia =
                        heroi.getAtaque() * 5;

                    hpInimigo -= danoMagia;

                    if (hpInimigo < 0) {
                        hpInimigo = 0;
                    }

                    mensagem =
                        heroi.getNome()
                            + " lançou Bola de Fogo e causou "
                            + danoMagia
                            + " de dano!";

                    if (hpInimigo > 0) {
                        turnoInimigo();
                    }

                    break;
                }

                if (heroi.getNome().equalsIgnoreCase("Clerigo")) {

                    if (!heroi.usarCargaDivina()) {

                        mensagem =
                            "Sem cargas divinas!";

                        break;
                    }

                    Heroi alvo =
                        contexto.grupo.get(alvoSelecionado);

                    if (!alvo.estaVivo()) {

                        alvo.reviver();

                        mensagem =
                            alvo.getNome()
                                + " foi revivido!";

                    } else {

                        alvo.curar(80);

                        mensagem =
                            alvo.getNome()
                                + " recuperou 80 HP!";
                    }

                    if (hpInimigo > 0) {
                        turnoInimigo();
                    }

                    break;
                }

                mensagem =
                    heroi.getNome()
                        + " nao possui skill.";

                break;

            case 2:

                usandoItem = true;

                break;

            case 3:

                jogo.setScreen(telaRetorno);

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

            Heroi heroi =
                contexto.grupo.get(i);

            if (!heroi.estaVivo()) {

                renderer.setColor(
                    Color.RED
                );

            } else if (i == heroiSelecionado) {

                renderer.setColor(
                    Color.GOLD
                );

            } else {

                renderer.setColor(
                    Color.YELLOW
                );
            }

            float x = 120 + (i * 120);

            renderer.circle(
                x,
                220,
                30
            );

            renderer.setColor(
                Color.DARK_GRAY
            );

            renderer.rect(
                x - 35,
                260,
                70,
                8
            );

            renderer.setColor(
                Color.GREEN
            );

            renderer.rect(
                x - 35,
                260,
                70 *
                    ((float) heroi.getHpAtual()
                        / heroi.getHpMaximo()),
                8
            );

            renderer.setColor(
                Color.DARK_GRAY
            );

            renderer.rect(
                x - 35,
                245,
                70,
                6
            );

            renderer.setColor(
                Color.BLUE
            );

            renderer.rect(
                x - 35,
                245,
                70 *
                    ((float) heroi.getMpAtual()
                        / heroi.getMpMaximo()),
                6
            );
        }

        renderer.end();

        batch.begin();

        for (int i = 0; i < quantidade; i++) {

            Heroi heroi =
                contexto.grupo.get(i);

            float x = 60 + (i * 120);

            font.draw(
                batch,
                heroi.getNome(),
                x,
                170
            );

            font.draw(
                batch,
                "HP "
                    + heroi.getHpAtual()
                    + "/"
                    + heroi.getHpMaximo(),
                x,
                145
            );

            font.draw(
                batch,
                "MP "
                    + heroi.getMpAtual()
                    + "/"
                    + heroi.getMpMaximo(),
                x,
                125
            );

            if (heroi.getNome().equalsIgnoreCase("Mago")) {

                font.draw(
                    batch,
                    "MAGIAS: "
                        + heroi.getCargasMagia(),
                    x,
                    105
                );
            }

            if (heroi.getNome().equalsIgnoreCase("Clerigo")) {

                font.draw(
                    batch,
                    "DIVINO: "
                        + heroi.getCargasDivinas(),
                    x,
                    105
                );
            }

            if (!heroi.estaVivo()) {

                font.draw(
                    batch,
                    "MORTO",
                    x,
                    85
                );
            }
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
            nomeInimigo,
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

            contexto.inimigosDerrotados++;

            Random random = new Random();

            if (random.nextBoolean()) {

                contexto.inventario.add(
                    ItemConsumivel.pocaoVida()
                );

                System.out.println(
                    "Dropou Pocao de Vida"
                );

            } else {

                contexto.inventario.add(
                    ItemConsumivel.pocaoMana()
                );

                System.out.println(
                    "Dropou Pocao de Mana"
                );
            }

            System.out.println(
                "Inimigos derrotados: "
                    + contexto.inimigosDerrotados
            );

            jogo.setScreen(telaRetorno);
        }

        boolean grupoVivo = false;

        for (Heroi heroi : contexto.grupo) {

            if (heroi.estaVivo()) {
                grupoVivo = true;
                break;
            }
        }

        if (!grupoVivo) {

            mensagem = "Game Over";

            jogo.setScreen(telaRetorno);
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

    private void turnoInimigo() {

        if (contexto.grupo.isEmpty()) {
            return;
        }

        Heroi alvo = null;

        while (alvo == null) {

            int indice =
                (int)(Math.random()
                    * contexto.grupo.size());

            Heroi candidato =
                contexto.grupo.get(indice);

            if (candidato.estaVivo()) {
                alvo = candidato;
            }
        }

        int dano = ataqueInimigo;

        alvo.receberDano(dano);

        mensagem =
            nomeInimigo
                + " atacou "
                + alvo.getNome()
                + " causando "
                + dano
                + " de dano!";
    }

}
