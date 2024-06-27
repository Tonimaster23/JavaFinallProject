import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static void salvarCartas(List<Carta> cartas, String caminhoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(cartas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Carta> carregarCartas(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                return (List<Carta>) obj;
            } else {
                throw new IOException("O objeto carregado não é uma lista de Cartas.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // ou null, dependendo do tratamento desejado
    }
    

    public static void salvarDeck(Deck deck, String caminhoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(deck);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Deck carregarDeck(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            return (Deck) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
