package io.github.Aldoria.controle;

import io.github.Aldoria.model.entidades.Heroi;

import java.util.ArrayList;
import java.util.List;

public class ControleGrupo {

    private final List<Heroi> herois = new ArrayList<>();

    public List<Heroi> getAll() {
        return herois;
    }

    public Heroi get(int index) {
        return herois.get(index);
    }

    public List<Heroi> getVivos() {

        List<Heroi> vivos = new ArrayList<>();

        for (Heroi h : herois) {
            if (h.estaVivo()) {
                vivos.add(h);
            }
        }

        return vivos;
    }
}
