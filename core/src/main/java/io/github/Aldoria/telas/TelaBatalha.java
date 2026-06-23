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
import io.github.Aldoria.model.entidades.Heroi;
import io.github.Aldoria.model.itens.ItemConsumivel;

import io.github.Aldoria.controle.ControleSpawnerInimigo;
import io.github.Aldoria.controle.MapaSprites;
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

    // ── Sprites ──────────────────────────────────────────────────────────────
    private MapaSprites sprites;
    private Texture texturaFundo;
    private Texture texturaInimigoAtual;

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

        // ── Carregamento dos sprites ──────────────────────────────────────────
        sprites = new MapaSprites();
        sprites.carregarTudo();
        sprites.finalizarCarregamento(); // carregamento simples e bloqueante

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

        // Define o fundo e o sprite do inimigo de acordo com o nome
        texturaFundo = sprites.getTexturaFundo(nomeInimigo);
        texturaInimigoAtual = sprites.getTexturaInimigo(nomeInimigo);
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

        if (escolhendoAlvoCura) {

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

            if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

                usarCuraClerigo();
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {

                escolhendoAlvoCura = false;
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

                    escolhendoAlvoCura = true;

                    alvoSelecionado = 0;

                    mensagem =
                        "Escolha quem curar";

                    break;
                }

            case 2:

                usandoItem = true;

                break;

            case 3:

                jogo.setScreen(telaRetorno);

                break;
        }
    }

    private void desenharFundo() {

        // Desenha a imagem de fundo do cenário (caverna, floresta,
        // cemitério ou caverna do dragão) de acordo com o inimigo atual.
        if (texturaFundo != null) {

            batch.begin();

            batch.draw(
                texturaFundo,
                0,
                0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
            );

            batch.end();

        } else {

            // Fallback: cor sólida caso a textura não tenha sido carregada
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
    }

    private void desenharHerois() {

        int quantidade =
            contexto.grupo.size();

        // ── Sprites dos heróis ────────────────────────────────────────────────
        batch.begin();

        float tamanhoSprite = 96f;

        for (int i = 0; i < quantidade; i++) {

            Heroi heroi =
                contexto.grupo.get(i);

            Texture texturaHeroi =
                sprites.getTexturaHeroi(heroi.getNome());

            float x = 120 + (i * 120) - (tamanhoSprite / 2f);
            float y = 220 - (tamanhoSprite / 2f);

            if (!heroi.estaVivo()) {
                batch.setColor(1f, 1f, 1f, 0.35f); // esmaece heróis mortos
            } else {
                batch.setColor(Color.WHITE);
            }

            batch.draw(
                texturaHeroi,
                x,
                y,
                tamanhoSprite,
                tamanhoSprite
            );
        }

        batch.setColor(Color.WHITE);
        batch.end();

        // ── Indicadores de seleção e barras de HP/MP ───────────────────────────
        renderer.begin(
            ShapeRenderer.ShapeType.Filled
        );

        for (int i = 0; i < quantidade; i++) {

            Heroi heroi =
                contexto.grupo.get(i);

            float x = 120 + (i * 120);

            // Indicador de seleção (substitui o círculo antigo do herói)
            if (escolhendoAlvoCura && i == alvoSelecionado) {

                renderer.setColor(
                    Color.CYAN
                );

                renderer.rect(
                    x - 50,
                    160,
                    100,
                    6
                );

            } else if (i == heroiSelecionado) {

                renderer.setColor(
                    Color.GOLD
                );

                renderer.rect(
                    x - 50,
                    160,
                    100,
                    6
                );
            }

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

        // Desenha o sprite do inimigo atual (slime, lobo, esqueleto ou dragão)
        // no lugar do círculo vermelho antigo.
        if (texturaInimigoAtual != null) {

            float tamanhoSprite = 160f;

            batch.begin();

            batch.draw(
                texturaInimigoAtual,
                850 - (tamanhoSprite / 2f),
                320 - (tamanhoSprite / 2f),
                tamanhoSprite,
                tamanhoSprite
            );

            batch.end();

        } else {

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
        if (escolhendoAlvoCura) {

            font.draw(
                batch,
                "Escolha quem curar",
                300,
                160
            );

            for (int i = 0;
                 i < contexto.grupo.size();
                 i++) {

                String texto =
                    (i == alvoSelecionado
                        ? "> "
                        : "  ")
                        +
                        contexto.grupo
                            .get(i)
                            .getNome();

                font.draw(
                    batch,
                    texto,
                    300,
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

        if (sprites != null) {
            sprites.dispose();
        }
    }

    private void usarCuraClerigo() {

        Heroi clerigo =
            contexto.grupo.get(
                heroiSelecionado
            );

        if (!clerigo.usarCargaDivina()) {

            mensagem =
                "Sem cargas divinas!";

            escolhendoAlvoCura = false;

            return;
        }

        Heroi alvo =
            contexto.grupo.get(
                alvoSelecionado
            );

        alvo.curar(80);

        mensagem =
            clerigo.getNome()
                + " curou "
                + alvo.getNome()
                + " em 80 HP";

        escolhendoAlvoCura = false;

        if (hpInimigo > 0) {

            turnoInimigo();
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
