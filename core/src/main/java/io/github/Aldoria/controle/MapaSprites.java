package io.github.Aldoria.controle;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class MapaSprites {

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


    public void finalizarCarregamento() {
        gerenciador.finishLoading();
    }


    public boolean atualizar() {
        return gerenciador.update();
    }

    public boolean estaCarregado() {
        return gerenciador.isFinished();
    }


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
