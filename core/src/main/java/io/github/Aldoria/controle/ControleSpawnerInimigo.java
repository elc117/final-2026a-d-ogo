package io.github.Aldoria.controle;

import io.github.Aldoria.model.entidades.Inimigo;

import java.util.List;

public class ControleSpawnerInimigo {

    public List<Inimigo> gerar() {
        return List.of(Inimigo.spawnBasico());
    }
}
