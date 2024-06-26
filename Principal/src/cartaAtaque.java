public class CartaAtaque extends Carta {
    private int dano;

    public CartaAtaque(String titulo, String descricao, String raridade, int poder, String tipo, int custoMana, int dano) {
        super(titulo, descricao, raridade, poder, tipo, custoMana);
        this.dano = dano;
    }

    public int getDano() { return dano; }

    @Override
    public String toString() {
        return super.toString() + ", dano=" + dano + "]";
    }
}