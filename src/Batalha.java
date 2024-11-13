import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

// Classe Batalha
class Batalha {
    Pokemon pokemon1;
    Pokemon pokemon2;
    Treinador treinador; //associação

    public Batalha(Pokemon pokemon1, Pokemon pokemon2, Treinador treinador) {
        this.pokemon1 = pokemon1;
        this.pokemon2 = pokemon2;
        this.treinador = treinador;
    }

    public void batalha(Scanner scanner) throws IOException {
        while (pokemon1.vida > 0 && pokemon2.vida > 0) {
            System.out.println("\nSeu Pokémon: " + pokemon1.nome + " (Vida: " + pokemon1.vida + ")");
            System.out.println("Pokémon Adversário: " + pokemon2.nome + " (Vida: " + pokemon2.vida + ")");
            System.out.println("\nEscolha sua ação:");
            System.out.println("1. Ataque Normal");
            System.out.println("2. Ataque Especial");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            // Ação do jogador
            if (escolha == 1) {
                pokemon1.atacarNormal(pokemon2);
            } else {
                pokemon1.atacarEspecial(pokemon2);
            }

            System.out.println(pokemon1.nome + " (Vida: " + pokemon1.vida + ") | " + pokemon2.nome + " (Vida: " + pokemon2.vida + ")");

            if (pokemon2.vida <= 0) {
                System.out.println("\nA batalha terminou!");
                System.out.println("Você venceu! " + pokemon2.nome + " foi derrotado.");
                treinador.registrarVitoria();
                break;
            }

            // Ação do adversário (ataque aleatório)
            Random random = new Random();
            int ataqueAdversario = random.nextInt(2) + 1; // Escolher aleatoriamente entre 1 e 2 (ataque normal e especial)

            if (ataqueAdversario == 1) {
                pokemon2.atacarNormal(pokemon1);
            } else {
                pokemon2.atacarEspecial(pokemon1);
            }

            System.out.println(pokemon1.nome + " (Vida: " + pokemon1.vida + ") | " + pokemon2.nome + " (Vida: " + pokemon2.vida + ")");

            if (pokemon1.vida <= 0) {
                System.out.println("\nA batalha terminou!");
                System.out.println("Você perdeu! " + pokemon1.nome + " foi derrotado.");
                treinador.registrarDerrota();
                break;
            }
        }
    }
}
