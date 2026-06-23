package io.github.Aldoria.model.itens;

public abstract class ItemEquipavel extends Item {

    protected int bonusAtaque;
    protected int bonusDefesa;
    protected int bonusHp;
    protected int bonusMp;

    public ItemEquipavel(
        String nome,
        String descricao,
        int bonusAtaque,
        int bonusDefesa,
        int bonusHp,
        int bonusMp
    ) {

        super(
            nome,
            descricao
        );

        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
        this.bonusHp = bonusHp;
        this.bonusMp = bonusMp;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public int getBonusHp() {
        return bonusHp;
    }

    public int getBonusMp() {
        return bonusMp;
    }

}
