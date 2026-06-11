package io.github.Aldoria.model.mundo;

import static jdk.internal.classfile.Classfile.build;

public class MapaJogo {

    public static final int COLS = 20;
    public static final int ROWS = 15;

    private final String nome;
    private final TiposLadrilhos[][] ladrilhos;
    private final int startX;
    private final int startY;
    private final int chefeX;
    private final int chefeY;
    private final int mapIndex;

    public MapaJogo(String nome, TiposLadrilhos[][] ladrilhos,
                    int startX, int startY, int chefeX, int chefeY, int mapIndex) {
        this.nome = nome;
        this.ladrilhos = ladrilhos;
        this.startX = startX;
        this.startY = startY;
        this.chefeX = chefeX;
        this.chefeY = chefeY;
        this.mapIndex = mapIndex;
    }

    public boolean isCaminhavel(int row, int col){
        return getLadrilhos(row, col).isCaminhavel();
    }
    public boolean isPosicaoChefe(int row, int col){
        return row == chefeY && col == chefeX;
    }

    public String getNome() {return nome;}
    public int getStartX() {return startX;}
    public int getStartY() {return startY;}
    public int getChefeX() {return chefeX;}
    public int getChefeY() {return chefeY;}
    public int getMapIndex() {return mapIndex;}

    // mapas do jogo TODO: revisar isso apos ver com a prof como fazer melhor
    // F=chao W=parede T=arvore B= portao do chefe
    public static MapaJogo criarMapa1(){
        String[] layout = {
            "WWWWWWWWWWWWWWWWWWWW",
            "WFFFFFFFFFFFFTFFFFFW",
            "WFTTTFFFFFTTTTFFFFFW",
            "WFFFFFWWWWFFFFFTTFFW",
            "WFFFFFWFFFWFFFFTTFFW",
            "WFTTTFWFFFWFFFFTTTFW",
            "WFFFFFWWWWFFFFFFFTTW",
            "WFFFFFFFFFFFFFFFFFFFBW",
            "WFFTTFFFFTTTFFFFFFFW",
            "WFFFFFTTFFFFFTTFFFFW",
            "WFTTTFFFFFFTTTFFFFFW",
            "WFFFFFFFFFFFTTTFFFFW",
            "WFTTFFFFTTTFFFFFFFFW",
            "WFFFFFFFFFFFFFFFFFFFFW",
            "WWWWWWWWWWWWWWWWWWWW"
        };

        return build("Floresta corrompida", layout, 1,1, 18, 7, 0);
    }

    public static MapaJogo criarMapa2(){
        String[] layout = {
            "WWWWWWWWWWWWWWWWWWWW",
            "WFFFFFFFFFFFFFFFFFFFFW",
            "WFWWWFFFFFWWWFFFFFFFW",
            "WFFFWFFFFFWFFFWWWFFFW",
            "WFFFWWWWWWWFFFWFFFW ",
            "WFFFFFFFFFFFFFFFFFFFF",
            "WFWWWFFFWFWWWFFFFFFFW",
            "WFWFFFWWWFWFFFFFFFFFFW",
            "WFWFFFFFFWWFFFFFFFFFFF",
            "WFFFFFFFFFFFFFFFFFFFFW",
            "WFWWWFFFFFFFFFFFWWWFW",
            "WFFFFFFFFFFFFFFFFFFFFF",
            "WFWWWWWWWWWWWWWWWFFW",
            "WFFFFFFFFFFFFFFFFFFFBW",
            "WWWWWWWWWWWWWWWWWWWW"
        };
        return build("Ruinas de aldoria", layout,1, 1, 18, 13, 1);
    }
    public static MapaJogo criarMapa3(){
        String[] layout = {
            "WWWWWWWWWWWWWWWWWWWW",
            "WFFFFFFFFFFFFFFFFFFFFW",
            "WFFFWWWFFFFFWWWFFFFFW",
            "WFFFFFFFFFFFFFFFFFFFF",
            "WFFFWFFFWFFWFFFWFFFFFW",
            "WFFFFFFFFFFFFFFFFFFFF",
            "WWWWWFFFFFFFFFFFWWWWW",
            "WFFFFFFFFFFFFFFFFFFFF",
            "WWWWWFFFFFFFFFFFWWWWW",
            "WFFFFFFFFFFFFFFFFFFFF",
            "WFFFWFFFWFFWFFFWFFFFFW",
            "WFFFFFFFFFFFFFFFFFFFF",
            "WFFFWWWFFFFFWWWFFFFFW",
            "WFFFFFFFFFFFFFFFFFFBw",
            "WWWWWWWWWWWWWWWWWWWW"
        };
        return build("Santuário do Esquecido", layout, 1, 1, 18, 13, 2);
    }

    private static  MapaJogo build(String nome, String[] layout,
                                   int sx, int sy, int bx, int by, int idx){
        TiposLadrilhos[][] ladrilhos = new TiposLadrilhos[ROWS][COLS];
        for (int r = 0; r < ROWS; r++){
            String row = r < layout.length ? layout[r] : "";
            for (int c = 0; c < COLS; c++){
                char ch = (c < row.length() ? row.charAt(c) : 'W');
                ladrilhos[r][c] = switch (ch){
                    case 'F', 'f' -> TiposLadrilhos.CHAO;
                    case 'T', 't' -> TiposLadrilhos.ARVORE;
                    case 'B'      -> TiposLadrilhos.PORTAO_CHEFE;
                    default       -> TiposLadrilhos.PAREDE;
                };
            }
        }
        return MapaJogo(nome, ladrilhos, sx,sy,bx,by, idx);
    }
}
