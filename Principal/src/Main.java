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

    private static void cadastrarCarta(Scanner scanner, List<Carta> cartas) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Raridade (comum, rara, épica, lendária): ");
        String raridade = scanner.nextLine();
        System.out.print("Poder: ");
        int poder = scanner.nextInt();
        System.out.print("Tipo (ataque, defesa, suporte): ");
        String tipo = scanner.next();
        System.out.print("Custo de Mana: ");
        int custoMana = scanner.nextInt();
        scanner.nextLine();

        try {
            if (tipo.equalsIgnoreCase("ataque")) {
                System.out.print("Dano: ");
                int dano = scanner.nextInt();
                scanner.nextLine(); 
                cartas.add(new CartaAtaque(titulo, descricao, raridade, poder, tipo, custoMana, dano));
            } else if (tipo.equalsIgnoreCase("defesa")) {
                System.out.print("Defesa: ");
                int defesa = scanner.nextInt();
                scanner.nextLine(); 
                cartas.add(new CartaDefesa(titulo, descricao, raridade, poder, tipo, custoMana, defesa));
            } else if (tipo.equalsIgnoreCase("suporte")) {
                cartas.add(new CartaSuporte(titulo, descricao, raridade, poder, tipo, custoMana));
            } else {
                throw new CadastroInvalidoException("Tipo de carta inválido.");
            }

            if (contarCopias(titulo, cartas) > 3) {
                throw new CadastroInvalidoException("Não é permitido mais de três cópias da mesma carta.");
            }

            System.out.println("Carta cadastrada com sucesso!");
        } catch (CadastroInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int contarCopias(String titulo, List<Carta> cartas) {
        int count = 0;
        for (Carta carta : cartas) {
            if (carta.getTitulo().equalsIgnoreCase(titulo)) {
                count++;
            }
        }
        return count;
    }

    private static void organizarCartas(Scanner scanner, List<Carta> cartas) {
        System.out.println("Organizar cartas por:");
        System.out.println("1. Raridade");
        System.out.println("2. Tipo");
        System.out.println("3. Poder");
        System.out.println("4. Custo de Mana");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                cartas.sort((c1, c2) -> c1.getRaridade().compareTo(c2.getRaridade()));
                break;
            case 2:
                cartas.sort((c1, c2) -> c1.getTipo().compareTo(c2.getTipo()));
                break;
            case 3:
                cartas.sort((c1, c2) -> Integer.compare(c1.getPoder(), c2.getPoder()));
                break;
            case 4:
                cartas.sort((c1, c2) -> Integer.compare(c1.getCustoMana(), c2.getCustoMana()));
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }
        System.out.println("Cartas organizadas com sucesso!");
        cartas.forEach(System.out::println);

    }




}



