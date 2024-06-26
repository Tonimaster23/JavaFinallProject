import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Carta> cartas = new ArrayList<>();
        List<Deck> decks = new ArrayList<>();
        SimuladorBatalha simulador = new SimuladorBatalha();

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir a nova linha

            switch (opcao) {
                case 1:
                    cadastrarCarta(scanner, cartas);
                    break;
                case 2:
                    organizarCartas(scanner, cartas);
                    break;
                case 3:
                    criarDeck(scanner, cartas, decks);
                    break;
                case 4:
                    simularBatalha(scanner, decks, simulador);
                    break;
                case 5:
                    Estatisticas.exibirEstatisticasCartas(cartas);
                    break;
                case 6:
                    salvarDados(cartas, decks);
                    break;
                case 7:
                    carregarDados(cartas, decks);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nSistema de Gerenciamento de Cartas de um Card Game");
        System.out.println("1. Cadastrar Carta");
        System.out.println("2. Organizar Cartas");
        System.out.println("3. Criar Deck");
        System.out.println("4. Simular Batalha");
        System.out.println("5. Exibir Estatísticas de Cartas");
        System.out.println("6. Salvar Dados");
        System.out.println("7. Carregar Dados");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

}