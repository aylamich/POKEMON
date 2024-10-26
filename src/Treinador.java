import java.util.ArrayList;
import java.util.List;

public class Treinador {
    private String nome;
    private String genero;
    private int nivel;
    private List<Pokemon> pokemons;

    public Treinador(String nome, String genero, int nivel){
        this.nome = nome;
        this.genero = genero;
        this.nivel = nivel;
        this.pokemons = new ArrayList<>();
    }

    public void CapturarPokemons(Pokemon pokemon){
        pokemons.add(pokemon);
        System.out.println(nome + " capturou " + pokemon.getNome());

    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public String getNome() {
        return nome;
    }

    public void exibirPokemons() {
        try {
            System.out.println("Pokémon de " + nome + ":");
            if (pokemons.isEmpty()) { // se a pessoa não tiver pokemons
                System.out.println(nome + " ainda não possui nenhum Pokémon.");
                return;
            }
            for (Pokemon pokemon : pokemons) {
                System.out.println("- " + pokemon.getNome() + " (Nível: " + pokemon.getNivel() + ")");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao exibir os Pokémon de " + nome + ": " + e.getMessage());
        }
    }
}