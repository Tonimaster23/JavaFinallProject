public class SimuladorBatalha {
    public void simularBatalha(Deck deck1, Deck deck2) {
        int poderDeck1 = calcularPoderTotal(deck1);
        int poderDeck2 = calcularPoderTotal(deck2);

        System.out.println("Iniciando batalha entre os decks:");
        System.out.println("Deck 1 - " + deck1.getNome() + " vs. Deck 2 - " + deck2.getNome());
        System.out.println("------------------------------");
        System.out.println("Poder total do Deck 1: " + poderDeck1);
        System.out.println("Poder total do Deck 2: " + poderDeck2);
        System.out.println("------------------------------");

        if (poderDeck1 > poderDeck2) {
            System.out.println("O Deck 1 venceu a batalha!");
        } else if (poderDeck2 > poderDeck1) {
            System.out.println("O Deck 2 venceu a batalha!");
        } else {
            System.out.println("Empate! A batalha terminou sem vencedor.");
        }
    }

    private int calcularPoderTotal(Deck deck) {
        int poderTotal = 0;
        for (Carta carta : deck.getCartas()) {
            poderTotal += carta.getPoder();
        }
        return poderTotal;
    }
}
