package io.github.Aldoria.controle;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Centraliza o carregamento e o mapeamento de sprites do jogo via
 * {@link AssetManager}.
 *
 * <p>Mapeia o nome do herói/inimigo (String) para o caminho do PNG
 * dentro da pasta assets/, e mapeia o nome do inimigo para o fundo
 * de cenário correspondente.</p>
 *
 * <p>Uso típico:</p>
 * <pre>
 *   MapaSprites sprites = new MapaSprites();
 *   sprites.carregarTudo();
 *   sprites.finalizarCarregamento(); // bloqueia até terminar (uso simples)
 *   Texture tex = sprites.getTexturaHeroi("Guerreiro");
 * </pre>
 */
public class MapasSprites {

    private final AssetManager gerenciador = new AssetManager();

    // ── Caminhos dos heróis ──────────────────────────────────────────────────
    private static final String CAMINHO_GUERREIRO = "guerreiro.png";
    private static final String CAMINHO_MAGO       = "mago.png";
    private static final String CAMINHO_LADINO     = "ladino.png";
    private static final String CAMINHO_CLERIGO    = "clerigo.png";

    // ── Caminhos dos inimigos ────────────────────────────────────────────────
    private static final String CAMINHO_SLIME      = "slime.png";
    private static final String CAMINHO_LOBO       = "lobo.png";
    private static final String CAMINHO_ESQUELETO  = "esqueleto.png";
    private static final String CAMINHO_DRAGAO     = "dragao.png";

    // ── Caminhos dos fundos de cenário ───────────────────────────────────────
    private static final String FUNDO_CAVERNA           = "caverna.png";
    private static final String FUNDO_FLORESTA           = "floresta.png";
    private static final String FUNDO_CEMITERIO          = "cemiterio.png";
    private static final String FUNDO_CAVERNA_DRAGAO     = "caverna_dragao.png";

    /**
     * Enfileira todos os PNGs usados na tela de batalha para carregamento.
     * Chame {@link #finalizarCarregamento()} depois para garantir que tudo
     * esteja pronto antes de desenhar.
     */
    public void carregarTudo() {
        // Heróis
        gerenciador.load(CAMINHO_GUERREIRO, Texture.class);
        gerenciador.load(CAMINHO_MAGO, Texture.class);
        gerenciador.load(CAMINHO_LADINO, Texture.class);
        gerenciador.load(CAMINHO_CLERIGO, Texture.class);

        // Inimigos
        gerenciador.load(CAMINHO_SLIME, Texture.class);
        gerenciador.load(CAMINHO_LOBO, Texture.class);
        gerenciador.load(CAMINHO_ESQUELETO, Texture.class);
        gerenciador.load(CAMINHO_DRAGAO, Texture.class);

        // Fundos
        gerenciador.load(FUNDO_CAVERNA, Texture.class);
        gerenciador.load(FUNDO_FLORESTA, Texture.class);
        gerenciador.load(FUNDO_CEMITERIO, Texture.class);
        gerenciador.load(FUNDO_CAVERNA_DRAGAO, Texture.class);
    }

    /**
     * Bloqueia a thread até todos os assets enfileirados terminarem de
     * carregar. Simples e adequado para um jogo pequeno como este;
     * em projetos maiores prefira {@link #atualizar()} a cada frame
     * com uma tela de loading.
     */
    public void finalizarCarregamento() {
        gerenciador.finishLoading();
    }

    /**
     * Atualiza o carregamento assíncrono. Retorna true quando tudo
     * terminou de carregar. Use em uma tela de loading se quiser
     * evitar o bloqueio de {@link #finalizarCarregamento()}.
     */
    public boolean atualizar() {
        return gerenciador.update();
    }

    public boolean estaCarregado() {
        return gerenciador.isFinished();
    }

    // ── Acesso a texturas de heróis ──────────────────────────────────────────

    /**
     * Retorna a textura do herói pelo nome ("Guerreiro", "Mago",
     * "Ladino" ou "Clerigo" — case-insensitive).
     */
    public Texture getTexturaHeroi(String nomeHeroi) {
        String chave;
        switch (nomeHeroi.toLowerCase()) {
            case "guerreiro":
                chave = CAMINHO_GUERREIRO;
                break;
            case "mago":
                chave = CAMINHO_MAGO;
                break;
            case "ladino":
                chave = CAMINHO_LADINO;
                break;
            case "clerigo":
                chave = CAMINHO_CLERIGO;
                break;
            default:
                chave = CAMINHO_GUERREIRO; // fallback seguro
                break;
        }
        return gerenciador.get(chave, Texture.class);
    }

    // ── Acesso a texturas de inimigos ─────────────────────────────────────────

    /**
     * Retorna a textura do inimigo pelo nome ("Slime", "Lobo",
     * "Esqueleto" ou "Dragao Ancestral" — case-insensitive,
     * usa contains() para tolerar variações como "Dragao Ancestral").
     */
    public Texture getTexturaInimigo(String nomeInimigo) {
        String nomeLower = nomeInimigo.toLowerCase();
        String chave;
        if (nomeLower.contains("slime")) {
            chave = CAMINHO_SLIME;
        } else if (nomeLower.contains("lobo")) {
            chave = CAMINHO_LOBO;
        } else if (nomeLower.contains("esqueleto")) {
            chave = CAMINHO_ESQUELETO;
        } else if (nomeLower.contains("dragao")) {
            chave = CAMINHO_DRAGAO;
        } else {
            chave = CAMINHO_SLIME; // fallback seguro
        }
        return gerenciador.get(chave, Texture.class);
    }

    /**
     * Retorna a textura do fundo de cenário correspondente ao
     * inimigo enfrentado.
     */
    public Texture getTexturaFundo(String nomeInimigo) {
        String nomeLower = nomeInimigo.toLowerCase();
        String chave;
        if (nomeLower.contains("slime")) {
            chave = FUNDO_CAVERNA;
        } else if (nomeLower.contains("lobo")) {
            chave = FUNDO_FLORESTA;
        } else if (nomeLower.contains("esqueleto")) {
            chave = FUNDO_CEMITERIO;
        } else if (nomeLower.contains("dragao")) {
            chave = FUNDO_CAVERNA_DRAGAO;
        } else {
            chave = FUNDO_CAVERNA; // fallback seguro
        }
        return gerenciador.get(chave, Texture.class);
    }

    public void dispose() {
        gerenciador.dispose();
    }
}
