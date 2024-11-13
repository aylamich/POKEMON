import java.util.List;
import java.util.Scanner;
import java.io.IOException;

// Classe principal
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {
            executarJogo(); // Executa o jogo
        }
    }

    // Método que encapsula o fluxo principal do jogo
    public static void executarJogo() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        // Criar os Pokémons
        Pokemon squirtle = new Squirtle();
        squirtle.serializarClasse();
        Pokemon bulbasaur = new Bulbasaur();
        bulbasaur.serializarClasse();
        Pokemon charmander = new Charmander();
        charmander.serializarClasse();

        // Criar os Treinadores
        Treinador ash = new Treinador("Ash Ketchum", "Masculino", 0, 0);
        // ash.serializarClasse();
        Treinador misty = new Treinador("Misty", "Feminino", 0, 0);
        //misty.serializarClasse();
        Treinador brock = new Treinador("Brock", "Masculino", 0, 0);
        // brock.serializarClasse();

        // Menu Inicial - Escolher treinador
        Treinador treinadorEscolhido = escolherTreinador(scanner, ash, misty, brock);
        System.out.println("Você escolheu " + treinadorEscolhido.nome + ".");

        // Captura de Pokémon
        escolherPokemon(scanner, treinadorEscolhido, squirtle, bulbasaur, charmander);

        //treinadorEscolhido.registrarVitoria();
        treinadorEscolhido.serializarClasse();

        // Menu de ações
        while (true) {
            System.out.println("\nEscolha o que deseja fazer:");
            System.out.println("1. Ver informações do treinador");
            System.out.println("2. Batalhar");
            System.out.println("3. Ver Pokémons");
            System.out.println("4. Reiniciar");
            System.out.println("5. Sair");

            int escolhaAcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (escolhaAcao) {
                case 1:
                    //mostrarInformacoesTreinador(treinadorEscolhido);
                    // treinadorEscolhido.registrarVitoria();
                    Treinador infos = treinadorEscolhido.desserializarClasse();

                    //System.out.println("Imprimindo o deserealizado");
                    System.out.println(infos);
                    break;

                case 2:
                    try {
                        // Aqui você chama o método batalhar com os pokémons
                        batalhar(scanner, treinadorEscolhido, squirtle, bulbasaur, charmander);
                    } catch (PokemonNaoEncontradoException e) {
                        // Captura a exceção personalizada e exibe a mensagem de erro
                        System.out.println("Erro: " + e.getMessage());
                    } catch (Exception e) {
                        // Captura qualquer outra exceção que possa ocorrer
                        System.out.println("Erro inesperado: ");
                    }
                    break;

                case 3:
                    // mostrarInformacoesPokemon(treinadorEscolhido); isso foi antes da serializacao
                    List<Pokemon> selecionados = treinadorEscolhido.getPokemonsEscolhidos();

                    for (Pokemon pokemon : selecionados) {
                        Pokemon deserializado = pokemon.desserializarClasse();
                        //System.out.println("Imprimindo o deserealizado");
                        System.out.println(deserializado);
                    }
                    break;

                case 4:
                    System.out.println("Reiniciando o jogo...");
                    return; // Sai do método `executarJogo`, reiniciando o jogo

                case 5:
                    System.out.println("Saindo do jogo. Até a próxima!");
                    System.exit(0); // Encerra o programa

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    // Demais métodos permanecem iguais
    private static Treinador escolherTreinador(Scanner scanner, Treinador ash, Treinador misty, Treinador brock) {
        System.out.println("Escolha um treinador:");
        System.out.println("1. Ash Ketchum");
        System.out.println("2. Misty");
        System.out.println("3. Brock");
        int escolhaTreinador = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        switch (escolhaTreinador) {
            case 1: return ash;
            case 2: return misty;
            case 3: return brock;
            default:
                System.out.println("Opção inválida! Escolhendo o Ash Ketchum por padrão.");
                return ash; // Padrão se o usuário errar
        }
    }


    private static void escolherPokemon(Scanner scanner, Treinador treinadorEscolhido, Pokemon... pokemons) throws IOException {
        boolean capturando = true;

        while (capturando) {
            System.out.println("\nEscolha um Pokémon para capturar:");
            for (int i = 0; i < pokemons.length; i++) {
                System.out.println((i + 1) + ". " + pokemons[i].nome + " (" + pokemons[i].tipo + ")");
            }
            System.out.println((pokemons.length + 1) + ". Capturar todos");
            System.out.println((pokemons.length + 2) + ". Finalizar captura");

            int escolhaPokemon = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            if (escolhaPokemon >= 1 && escolhaPokemon <= pokemons.length) {
                treinadorEscolhido.capturarPokemon(pokemons[escolhaPokemon - 1]);
            } else if (escolhaPokemon == pokemons.length + 1) {
                for (Pokemon pokemon : pokemons) {
                    treinadorEscolhido.capturarPokemon(pokemon);
                }
            } else if (escolhaPokemon == pokemons.length + 2) {
                capturando = false;
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }


    private static void mostrarInformacoesTreinador(Treinador treinadorEscolhido) {
        System.out.println("\nInformações do Treinador: " + treinadorEscolhido.nome);
        System.out.println("Gênero: " + treinadorEscolhido.genero);

        // Listar Pokémon capturados
        if (!treinadorEscolhido.getPokemonsEscolhidos().isEmpty()) {
            System.out.println("\nPokémons capturados:");
            for (Pokemon p : treinadorEscolhido.getPokemonsEscolhidos()) {
                System.out.println("- " + p.nome + " (" + p.tipo + ")");
            }
        } else {
            System.out.println("Nenhum Pokémon capturado ainda.");
        }

        // Mostrar estatísticas de batalhas
        treinadorEscolhido.mostrarInformacoes();
    }




    private static void batalhar(Scanner scanner, Treinador treinadorEscolhido, Pokemon... pokemons) throws PokemonNaoEncontradoException, IOException {
        if (treinadorEscolhido.pokemonsEscolhidos.isEmpty()) {
            System.out.println("\nVocê não possui Pokémons capturados! Capture um Pokémon primeiro.");
            return;
        }

        System.out.println("\nEscolha seu Pokémon para a batalha:");
        treinadorEscolhido.listarPokemons();

        int escolhaJogador = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (escolhaJogador < 1 || escolhaJogador > treinadorEscolhido.pokemonsEscolhidos.size()) {
            // Lançando a exceção personalizada se o Pokémon escolhido não for válido
            throw new PokemonNaoEncontradoException("Pokémon escolhido não encontrado. Escolha um Pokémon válido.");
        }

        Pokemon pokemonJogador = treinadorEscolhido.pokemonsEscolhidos.get(escolhaJogador - 1);

        System.out.println("\nEscolha o Pokémon adversário:");
        for (int i = 0; i < pokemons.length; i++) {
            if (!pokemonJogador.equals(pokemons[i])) {
                System.out.println((i + 1) + ". " + pokemons[i].nome + " (" + pokemons[i].tipo + ")");
            }
        }

        int escolhaAdversario = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        Pokemon pokemonAdversario = null;
        if (escolhaAdversario >= 1 && escolhaAdversario <= pokemons.length && !pokemonJogador.equals(pokemons[escolhaAdversario - 1])) {
            pokemonAdversario = pokemons[escolhaAdversario - 1];
        } else {
            System.out.println("Opção inválida! Nenhum Pokémon adversário escolhido.");
            return;
        }

        // Restaurar vida de ambos os Pokémons antes da batalha
        pokemonJogador.restaurarVida();
        pokemonAdversario.restaurarVida();

        // Criar uma batalha com o Pokémon do jogador e o adversário escolhido
        Batalha batalha = new Batalha(pokemonJogador, pokemonAdversario, treinadorEscolhido);
        batalha.batalha(scanner); // Executa a batalha

        System.out.println("\nA batalha terminou!");
    }




    private static void mostrarInformacoesPokemon(Treinador treinadorEscolhido) {
        // Verificar se há Pokémon capturados
        if (!treinadorEscolhido.getPokemonsEscolhidos().isEmpty()) {
            System.out.println("\nTodos os Pokémons capturados:");
            for (Pokemon p : treinadorEscolhido.getPokemonsEscolhidos()) {
                System.out.println("- " + p.nome + " (" + p.tipo + ")");
                p.exibirResumo();
            }
        } else {
            System.out.println("\nNenhum Pokémon capturado ainda.");
        }
    }


}
