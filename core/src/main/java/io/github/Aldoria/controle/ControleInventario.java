package io.github.Aldoria.controle;

import io.github.Aldoria.model.itens.Item;

import java.util.ArrayList;
import java.util.List;

public class ControleInventario {

    private final List<Item> items = new ArrayList<>();

    public void adicionar(Item item) {
        items.add(item);
    }

    public List<Item> getAll() {
        return items;
    }

    public Item get(int index) {
        return items.get(index);
    }
}
