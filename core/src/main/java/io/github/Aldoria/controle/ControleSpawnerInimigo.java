package io.github.Aldoria.controle;

import io.github.Aldoria.model.entidades.Inimigo;

import java.util.Random;

public class ControleSpawnerInimigo {

    public Inimigo gerarInimigo(int derrotados) {

        Random random = new Random();

        if (derrotados >= 3) {

            return Inimigo.gerarDragao();
        }

        int tipo = random.nextInt(3);

        switch (tipo) {

            case 0:
                return Inimigo.gerarGoblin();

            case 1:
                return Inimigo.gerarLobo();

            default:
                return Inimigo.gerarEsqueleto();
        }
    }
}
