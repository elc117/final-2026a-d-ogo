package io.github.Aldoria.model.mundo;

public class MapaJogo {

    public static final int LINHAS = 15;
    public static final int COLUNAS = 20;

    private final TiposLadrilhos[][] mapa;

    public MapaJogo() {

        mapa = new TiposLadrilhos[LINHAS][COLUNAS];

        for (int linha = 0; linha < LINHAS; linha++) {

            for (int coluna = 0; coluna < COLUNAS; coluna++) {

                mapa[linha][coluna] = TiposLadrilhos.CHAO;

                if (
                    linha == 0 ||
                        linha == LINHAS - 1 ||
                        coluna == 0 ||
                        coluna == COLUNAS - 1
                ) {
                    mapa[linha][coluna] =
                        TiposLadrilhos.PAREDE;
                }
            }
        }
    }

    public TiposLadrilhos getTile(
        int linha,
        int coluna
    ) {
        return mapa[linha][coluna];
    }

    public String getNome() {
        return "Mapa Inicial";
    }
}
