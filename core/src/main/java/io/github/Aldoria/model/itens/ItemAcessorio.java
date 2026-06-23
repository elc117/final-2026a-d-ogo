package io.github.Aldoria.model.itens;

public class ItemAcessorio extends ItemEquipavel {

    public ItemAcessorio(

        String nome,

        String descricao,

        int hp,

        int mp

    ) {

        super(

            nome,

            descricao,

            0,

            0,

            hp,

            mp

        );

    }

    public static ItemAcessorio anelVida() {

        return new ItemAcessorio(

            "Anel da Vida",

            "Aumenta HP",

            50,

            0

        );

    }

    public static ItemAcessorio amuletoMana() {

        return new ItemAcessorio(

            "Amuleto Arcano",

            "Aumenta MP",

            0,

            50

        );

    }

}
