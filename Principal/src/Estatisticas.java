import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estatisticas {

    public static void exibirEstatisticasCartas(List<Carta> cartas) {
        int totalCartas = cartas.size();
        Map<String, Integer> raridades = new HashMap<>();
        Map<String, Integer> tipos = new HashMap<>();
        int poderTotal = 0;

        for (Carta carta : cartas) {
            raridades.put(carta.getRaridade(), raridades.getOrDefault(carta.getRaridade(), 0) + 1);
            tipos.put(carta.getTipo(), tipos.getOrDefault(carta.getTipo(), 0) + 1);
            poderTotal += carta.getPoder();
        }

        System.out.println("Total de Cartas: " + totalCartas);
        System.out.println("Distribuição por Raridade: " + raridades);
        System.out.println("Distribuição por Tipo: " + tipos);
        System.out.println("Poder Médio: " + (poderTotal / (double) totalCartas));
    }
}
