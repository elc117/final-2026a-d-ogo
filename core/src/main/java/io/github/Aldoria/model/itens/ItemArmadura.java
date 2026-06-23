package io.github.Aldoria.model.itens;

public class ItemArmadura extends ItemEquipavel {

    public ItemArmadura(
        String nome,
        String descricao,
        int bonusDefesa,
        int bonusHp
    ) {

        super(
            nome,
            descricao,
            0,
            bonusDefesa,
            bonusHp,
            0
        );
    }

    public static ItemArmadura peitoralCouro() {

        return new ItemArmadura(

            "Peitoral de Couro",

            "Armadura simples",

            10,

            30
        );
    }

    public static ItemArmadura armaduraFerro() {

        return new ItemArmadura(

            "Armadura de Ferro",

            "Armadura pesada",

            25,

            80
        );
    }

}
