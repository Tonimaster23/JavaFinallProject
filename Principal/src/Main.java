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
    private static void criarDeck(Scanner scanner, List<Carta> cartas, List<Deck> decks) {
        System.out.print("Nome do Deck: ");
        String nome = scanner.nextLine();
        Deck deck = new Deck(nome);

        while (deck.getCartas().size() < 20) {
            System.out.println("Selecione uma carta pelo título (ou 'sair' para finalizar): ");
            String titulo = scanner.nextLine();

            if (titulo.equalsIgnoreCase("sair")) {
                break;
            }

            Carta cartaSelecionada = cartas.stream()
                .filter(c -> c.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);

            if (cartaSelecionada != null) {
                deck.adicionarCarta(cartaSelecionada);
            } else {
                System.out.println("Carta não encontrada.");
            }
        }

        decks.add(deck);
        System.out.println("Deck criado com sucesso!");
    }

    private static void simularBatalha(Scanner scanner, List<Deck> decks, SimuladorBatalha simulador) {
        System.out.println("Selecione o primeiro deck pelo nome: ");
        String nomeDeck1 = scanner.nextLine();
        Deck deck1 = decks.stream()
            .filter(d -> d.getNome().equalsIgnoreCase(nomeDeck1))
            .findFirst()
            .orElse(null);

        System.out.println("Selecione o segundo deck pelo nome: ");
        String nomeDeck2 = scanner.nextLine();
        Deck deck2 = decks.stream()
            .filter(d -> d.getNome().equalsIgnoreCase(nomeDeck2))
            .findFirst()
            .orElse(null);

        if (deck1 != null && deck2 != null) {
            simulador.simularBatalha(deck1, deck2);
        } else {
            System.out.println("Um ou ambos os decks não foram encontrados.");
        }
    }

    private static void salvarDados(List<Carta> cartas, List<Deck> decks) {
        Persistencia.salvarCartas(cartas, "cartas.dat");
        for (Deck deck : decks) {
            Persistencia.salvarDeck(deck, deck.getNome() + ".dat");
        }
        System.out.println("Dados salvos com sucesso!");
    }

    private static void carregarDados(List<Carta> cartas, List<Deck> decks) {
        List<Carta> cartasCarregadas = Persistencia.carregarCartas("cartas.dat");
        if (cartasCarregadas != null) {
            cartas.clear();
            cartas.addAll(cartasCarregadas);
            System.out.println("Cartas carregadas com sucesso!");
        } else {
            System.out.println("Falha ao carregar cartas.");
        }

        String[] nomesDecks = {"Deck1", "Deck2"}; // Exemplos de nomes de decks
    decks.clear();
    for (String nomeDeck : nomesDecks) {
        Deck deck = Persistencia.carregarDeck(nomeDeck + ".dat");
        if (deck != null) {
            decks.add(deck);
        }
    }
    System.out.println("Decks carregados com sucesso!");
}


}

//# TrabalhoProgramacaoJava
//Foi solicitado para fazer um CRUD em JAVA com a classe escolhida
//Fizemos um programa de Jogo de Cartas
//Inicialmente com as Classes: Carta, CartaAtaque, CartaDefesa, CartaSuporte, Deck, Estatisticas, Organizavel, Persistencia, SimuladorBatalha, CadastroInvalidoException e o Programa que é o main.
//Criando as classes inicialmente em listas, fizemos uma adição de dados por default para popular as classes.

//Programa Criado por:
//Luis Gabriel;
//Adriel 
//joao pedro de carvalho ziegler
//Arthut cesar de moraes
//Na segunda etapa fizemos melhorias no código, incluindo validações e funcionalidades adicionais.

//Atividade Passada pelo professor Jason.

