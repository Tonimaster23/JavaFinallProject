public class CartaDefesa extends Carta {
    private int defesa;

    public CartaDefesa(String titulo, String descricao, String raridade, int poder, String tipo, int custoMana, int defesa) {
        super(titulo, descricao, raridade, poder, tipo, custoMana);
        this.defesa = defesa;
    }

    public int getDefesa() { return defesa; }

    @Override
    public String toString() {
        return super.toString() + ", defesa=" + defesa + "]";
    }
}
