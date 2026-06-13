package io.github.Aldoria.telas;

import com.badlogic.gdx.Screen;
import io.github.Aldoria.GameContext;
import io.github.Aldoria.model.entidades.Heroi;
import io.github.Aldoria.model.itens.Item;

import java.util.List;

public class TelaGrupo implements Screen {

    private final GameContext contexto;

    private int cursorHeroi = 0;

    public TelaGrupo(GameContext contexto) {
        this.contexto = contexto;
    }

    private void atualizarLista() {

        List<Heroi> herois = contexto.grupo;

        if (herois.isEmpty()) {
            return;
        }

        if (cursorHeroi >= herois.size()) {
            cursorHeroi = herois.size() - 1;
        }
    }

    private void renderizarLista() {

        List<Heroi> herois = contexto.grupo;

        for (int i = 0; i < herois.size(); i++) {

            Heroi heroi = herois.get(i);

            System.out.println(
                (i == cursorHeroi ? "> " : "  ") +
                    heroi.getNome() +
                    " HP " + heroi.getHpAtual() + "/" + heroi.getHpMaximo()
            );
        }
    }

    private void renderizarDetalhes() {

        List<Heroi> herois = contexto.grupo;

        if (herois.isEmpty()) return;

        Heroi heroi = herois.get(cursorHeroi);

        System.out.println("=== DETALHES ===");
        System.out.println("Nome: " + heroi.getNome());
        System.out.println("HP: " + heroi.getHpAtual() + "/" + heroi.getHpMaximo());
        System.out.println("DEF: " + heroi.getDefesa());
        System.out.println("VEL: " + heroi.getVelocidade());

        if (heroi.getArmaEquipada() != null) {
            System.out.println("Arma: " + heroi.getArmaEquipada().getNome());
        }

        System.out.println("\nSkills:");
        for (String skill : heroi.getSkills()) {
            System.out.println("- " + skill);
        }
    }

    private void renderizarInventario() {

        List<Item> itens = contexto.inventario;

        System.out.println("=== INVENTÁRIO ===");

        for (Item item : itens) {
            System.out.println("- " + item.getNome());
        }
    }

    @Override public void show() {}
    @Override public void render(float delta) {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
