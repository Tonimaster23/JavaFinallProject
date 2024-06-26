import java.io.Serializable;

public abstract class Carta implements Serializable {
    private String titulo;
    private String descricao;
    private String raridade;
    private int poder;
    private String tipo;
    private int custoMana;

    public Carta(String titulo, String descricao, String raridade, int poder, String tipo, int custoMana) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.raridade = raridade;
        this.poder = poder;
        this.tipo = tipo;
        this.custoMana = custoMana;
    }

    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getRaridade() { return raridade; }
    public int getPoder() { return poder; }
    public String getTipo() { return tipo; }
    public int getCustoMana() { return custoMana; }

    @Override
    public String toString() {
        return "Carta [titulo=" + titulo + ", descricao=" + descricao + ", raridade=" + raridade + 
               ", poder=" + poder + ", tipo=" + tipo + ", custoMana=" + custoMana + "]";
    }
}
