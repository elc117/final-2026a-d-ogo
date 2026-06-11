package io.github.Aldoria.model.mundo;

//tilemap
public class TiposLadrilhos {
    CHAO(true),
    PAREDE(false),
    ARVORE(false),
    WATER(false),
    BAU(true),
    PORTAO_CHEFE(true);

    private final boolean caminhavel;

    TiposLadrilhos(boolean caminhavel) {
        this.caminhavel = caminhavel;
    }

    public boolean isCaminhavel() {return caminhavel;}
}
