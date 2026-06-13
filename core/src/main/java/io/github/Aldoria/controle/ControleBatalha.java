package io.github.Aldoria.controle;

import io.github.Aldoria.model.entidades.Heroi;
import io.github.Aldoria.model.entidades.Inimigo;

import java.util.ArrayList;
import java.util.List;

public class ControleBatalha {

    private final List<Heroi> herois;
    private final List<Inimigo> inimigos = new ArrayList<>();

    private final List<String> log = new ArrayList<>();

    public ControleBatalha(List<Heroi> herois, ControleInventario inventario) {
        this.herois = herois;
    }

    public void iniciar(List<Inimigo> inimigos) {
        this.inimigos.clear();
        this.inimigos.addAll(inimigos);
    }

    public String getUltimoLog() {
        if (log.isEmpty()) return "";
        return log.get(log.size() - 1);
    }
}
