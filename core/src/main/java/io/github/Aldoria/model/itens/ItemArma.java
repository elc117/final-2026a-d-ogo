package io.github.Aldoria.model.itens;

public class ItemArma extends Item {
    private final int bonusAtaque;
    private final String classePermitida;

    public ItemArma(String nome, String descricao, int bonusAtaque, String classePermitida) {
        super(nome, descricao, TipoItem.ARMA);
        this.bonusAtaque = bonusAtaque;
        this.classePermitida = classePermitida;
    }

    public int getBonusAtaque() {return bonusAtaque;}

    public String getClassePermitida() {
        return classePermitida;
    }

    public static ItemArma espadaFerro() {
        return new ItemArma("Espada de Ferro", "+15 ATK", 15, "WARRIOR");
    }

    public static ItemArma cajadoMagico() {
        return new ItemArma("Cajado Mágico", "+20 ATK mágico", 20, "MAGE");
    }

    public static ItemArma adagaSombra() {
        return new ItemArma("Adaga das Sombras", "+18 ATK + velocidade", 18, "ROGUE");
    }

    public static ItemArma macaSagrada() {
        return new ItemArma("Maça Sagrada", "+12 ATK + cura bônus", 12, "HEALER");
    }

}
