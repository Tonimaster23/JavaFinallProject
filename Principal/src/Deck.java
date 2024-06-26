import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Deck implements Serializable {
    private String nome;
    private List<Carta> cartas;

    public Deck(String nome) {
        this.nome = nome;
        this.cartas = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public List<Carta> getCartas() { return cartas; }

    public void adicionarCarta(Carta carta) {
        if (cartas.size() < 20) {
            cartas.add(carta);
        } else {
            System.out.println("O deck já possui 20 cartas.");
        }
    }

    public void removerCarta(Carta carta) {
        cartas.remove(carta);
    }

    public void exibirCartas() {
        for (Carta carta : cartas) {
            System.out.println(carta);
        }
    }

    @Override
    public String toString() {
        return "Deck [nome=" + nome + ", cartas=" + cartas + "]";
    }
}
