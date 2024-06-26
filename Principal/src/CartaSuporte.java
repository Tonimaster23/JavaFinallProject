public class CartaSuporte extends Carta {
    public CartaSuporte(String titulo, String descricao, String raridade, int poder, String tipo, int custoMana) {
        super(titulo, descricao, raridade, poder, tipo, custoMana);
    }

    @Override
    public String toString() {
        return super.toString() + "]";
    }
}
