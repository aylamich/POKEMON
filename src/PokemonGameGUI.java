import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class PokemonGameGUI extends JFrame {
    private Treinador treinadorEscolhido;

    public PokemonGameGUI() {
        setTitle("Jogo de Pokémon");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        iniciarTelaInicial();
    }

    private void iniciarTelaInicial() {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Escolha seu Treinador", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(3, 1, 10, 10));

        JButton ashButton = new JButton("Ash Ketchum");
        JButton mistyButton = new JButton("Misty");
        JButton brockButton = new JButton("Brock");

        ashButton.addActionListener(e -> escolherTreinador(new Treinador("Ash Ketchum", "Masculino", 0, 0)));
        mistyButton.addActionListener(e -> escolherTreinador(new Treinador("Misty", "Feminino", 0, 0)));
        brockButton.addActionListener(e -> escolherTreinador(new Treinador("Brock", "Masculino", 0, 0)));

        botoes.add(ashButton);
        botoes.add(mistyButton);
        botoes.add(brockButton);

        painel.add(botoes, BorderLayout.CENTER);

        setContentPane(painel);
        revalidate();
    }

    private void escolherTreinador(Treinador treinador) {
        this.treinadorEscolhido = treinador;
        JOptionPane.showMessageDialog(this, "Você escolheu " + treinador.nome + "!");
        iniciarMenuPrincipal();
    }

    private void iniciarMenuPrincipal() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton informacoesButton = new JButton("Ver Informações do Treinador");
        JButton informacoesPkButton = new JButton("Ver Informações do Pokemon");
        JButton capturarButton = new JButton("Capturar Pokémon");
        JButton batalharButton = new JButton("Batalhar");
        JButton reiniciarButton = new JButton("Reiniciar");
        JButton sairButton = new JButton("Sair");

        informacoesButton.addActionListener(e -> mostrarInformacoesTreinador());
        capturarButton.addActionListener(e -> {
            try {
                capturarPokemon();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        informacoesPkButton.addActionListener(e -> mostrarInformacoesPokemon());
        batalharButton.addActionListener(e -> batalhar());
        reiniciarButton.addActionListener(e -> iniciarTelaInicial());
        sairButton.addActionListener(e -> System.exit(0));

        painel.add(titulo);
        painel.add(informacoesButton);
        painel.add(capturarButton);
        painel.add(batalharButton);
        painel.add(informacoesPkButton);
        painel.add(reiniciarButton);
        painel.add(sairButton);
        setContentPane(painel);
        revalidate();
    }

    private void mostrarInformacoesTreinador() {
        String nomesPokemons = "";
        for (Pokemon pokemon : treinadorEscolhido.pokemonsEscolhidos) {
            nomesPokemons += pokemon.getNome() + ", ";  // Concatena os nomes dos Pokémons
        }

        if (!nomesPokemons.isEmpty()) {
            nomesPokemons = nomesPokemons.substring(0, nomesPokemons.length() - 2);
        }
        if (treinadorEscolhido != null) {
            JOptionPane.showMessageDialog(this,
                    "Treinador: " + treinadorEscolhido.nome + "\n" +
                            "Gênero: " + treinadorEscolhido.genero + "\n" +
                            "Pokemons: " + nomesPokemons );
        }
    }

    private void mostrarInformacoesPokemon() {
        if (treinadorEscolhido != null) {
            JOptionPane.showMessageDialog(this,
                            "Pokemons: " + treinadorEscolhido.pokemonsEscolhidos );
        }
    }

    private void capturarPokemon() throws IOException {
        String[] opcoes = {"Squirtle", "Bulbasaur", "Charmander"};
        int escolha = JOptionPane.showOptionDialog(
                this,
                "Escolha um Pokémon para capturar:",
                "Capturar Pokémon",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        Pokemon pokemonCapturado = null;

        // Verifique a escolha do jogador e crie o Pokémon correspondente
        if (escolha == 0) {
            pokemonCapturado = new Squirtle(); // Instancia Squirtle
        } else if (escolha == 1) {
            pokemonCapturado = new Bulbasaur(); // Instancia Bulbasaur
        } else if (escolha == 2) {
            pokemonCapturado = new Charmander(); // Instancia Charmander
        }

        // Verifique se o Pokémon foi capturado com sucesso
        if (pokemonCapturado != null) {
            JOptionPane.showMessageDialog(this, "Você capturou " + pokemonCapturado.getNome() + "!");
            treinadorEscolhido.capturarPokemon(pokemonCapturado); // Adiciona o Pokémon ao treinador
        }
    }

    private void batalhar() {
        if (treinadorEscolhido.getPokemonsEscolhidos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Você precisa capturar um Pokémon antes de batalhar!");
            return;
        }

        // Escolher Pokémon do treinador
        Pokemon pokemonTreinador = (Pokemon) JOptionPane.showInputDialog(
                this,
                "Escolha seu Pokémon para batalhar:",
                "Escolha seu Pokémon",
                JOptionPane.QUESTION_MESSAGE,
                null,
                treinadorEscolhido.getPokemonsEscolhidos().toArray(),
                null
        );

        if (pokemonTreinador == null) {
            JOptionPane.showMessageDialog(this, "Batalha cancelada.");
            return;
        }

        // Escolher Pokémon adversário aleatoriamente, mas garantir que não seja o mesmo Pokémon escolhido
        Random random = new Random();
        Pokemon[] pokemonsAdversarios = {new Squirtle(), new Bulbasaur(), new Charmander()};
        Pokemon pokemonAdversario = pokemonsAdversarios[random.nextInt(pokemonsAdversarios.length)];

        // Garantir que o adversário não seja o mesmo que o Pokémon do treinador
        while (pokemonAdversario.getNome().equals(pokemonTreinador.getNome())) {
            pokemonAdversario = pokemonsAdversarios[random.nextInt(pokemonsAdversarios.length)];
        }

        // Criar a interface da batalha
        batalhaInterface(pokemonTreinador, pokemonAdversario);
    }


    private void batalhaInterface(Pokemon pokemonTreinador, Pokemon pokemonAdversario) {
        JPanel painelBatalha = new JPanel();
        painelBatalha.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel infoBatalha = new JLabel(pokemonTreinador.getNome() + " VS " + pokemonAdversario.getNome());
        painelBatalha.add(infoBatalha);

        JLabel infoVida = new JLabel("Vida: " + pokemonTreinador.getVida() + " VS " + pokemonAdversario.getVida());
        painelBatalha.add(infoVida);

        JButton atacarNormalButton = new JButton("Atacar Normal");
        JButton atacarEspecialButton = new JButton("Atacar Especial");

        atacarNormalButton.addActionListener(e -> {
            if (pokemonTreinador.getVida() > 0 && pokemonAdversario.getVida() > 0) {
                // Ataque normal do treinador (usando dano fixo)
                int danoTreinador = pokemonTreinador.getDanoNormal(); // Dano normal do Pokémon treinador
                pokemonAdversario.setVida(pokemonAdversario.getVida() - danoTreinador);

                // Ataque normal do adversário (usando dano fixo)
                int danoAdversario = pokemonAdversario.getDanoNormal(); // Dano normal do Pokémon adversário
                pokemonTreinador.setVida(pokemonTreinador.getVida() - danoAdversario);

                // Atualiza a interface
                infoBatalha.setText(pokemonTreinador.getNome() + " causou " + danoTreinador + " de dano.\n" +
                        pokemonAdversario.getNome() + " causou " + danoAdversario + " de dano.");
                infoVida.setText("Vida: " + pokemonTreinador.getVida() + " VS " + pokemonAdversario.getVida());

                // Verificar se alguém morreu
                if (pokemonTreinador.getVida() <= 0 || pokemonAdversario.getVida() <= 0) {
                    String vencedor = pokemonTreinador.getVida() > 0 ? pokemonTreinador.getNome() : pokemonAdversario.getNome();
                    JOptionPane.showMessageDialog(this, vencedor + " venceu a batalha!");
                    // Voltar para o menu principal
                    iniciarMenuPrincipal();
                }
            }
        });

        atacarEspecialButton.addActionListener(e -> {
            if (pokemonTreinador.getVida() > 0 && pokemonAdversario.getVida() > 0) {
                // Ataque especial do treinador (usando dano fixo)
                int danoTreinador = pokemonTreinador.getDanoEspecial(); // Dano especial do Pokémon treinador
                pokemonAdversario.setVida(pokemonAdversario.getVida() - danoTreinador);

                // Ataque especial do adversário (usando dano fixo)
                int danoAdversario = pokemonAdversario.getDanoEspecial(); // Dano especial do Pokémon adversário
                pokemonTreinador.setVida(pokemonTreinador.getVida() - danoAdversario);

                // Atualiza a interface
                infoBatalha.setText(pokemonTreinador.getNome() + " causou " + danoTreinador + " de dano especial.\n" +
                        pokemonAdversario.getNome() + " causou " + danoAdversario + " de dano especial.");
                infoVida.setText("Vida: " + pokemonTreinador.getVida() + " VS " + pokemonAdversario.getVida());

                // Verificar se alguém morreu
                if (pokemonTreinador.getVida() <= 0 || pokemonAdversario.getVida() <= 0) {
                    String vencedor = pokemonTreinador.getVida() > 0 ? pokemonTreinador.getNome() : pokemonAdversario.getNome();
                    JOptionPane.showMessageDialog(this, vencedor + " venceu a batalha!");
                    // Voltar para o menu principal
                    iniciarMenuPrincipal();
                }
            }

        });

        painelBatalha.add(atacarNormalButton);
        painelBatalha.add(atacarEspecialButton);

        setContentPane(painelBatalha);
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PokemonGameGUI game = new PokemonGameGUI();
            game.setVisible(true);
        });
    }
}
