import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class Treinador implements Serializable {
    String nome;
    String genero;
    Pokemon pokemonEscolhido;
    int batalhasVencidas;
    int batalhasTotais;
    public List<Pokemon> pokemonsEscolhidos = new ArrayList<>();
    private String arqSerialized = "";

    public Treinador(String nome, String genero, int batalhasVencidas, int batalhasTotais) {
        this.nome = nome;
        this.genero = genero;
        this.batalhasVencidas = 0;
        this.batalhasTotais = 0;
        setArqSerialized("treinador.txt");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }



    public void escolherPokemon(Pokemon pokemon) {
        this.pokemonEscolhido = pokemon;
    }

    public void registrarVitoria() throws IOException {
        batalhasVencidas++;
        batalhasTotais++;
        serializarClasse();

    }

    // Método para registrar uma derrota
    public void registrarDerrota() throws IOException {
        batalhasTotais++;
        serializarClasse();
    }

    // Exibir as informações do treinador
    public void mostrarInformacoes() {
        System.out.println("Batalhas Vencidas: " + batalhasVencidas);
        System.out.println("Batalhas Totais: " + batalhasTotais);
    }

    public List<Pokemon> getPokemonsEscolhidos() {
        return pokemonsEscolhidos;
    }

    public void adicionarPokemon() {
        pokemonsEscolhidos.add(pokemonEscolhido);
    }

    public void listarPokemons() {
        if (pokemonsEscolhidos.isEmpty()) {
            System.out.println(nome + " ainda não capturou nenhum Pokémon.");
        } else {
            System.out.println("Pokémons capturados por " + nome + ":");
            for (int i = 0; i < pokemonsEscolhidos.size(); i++) {
                System.out.println((i + 1) + ". " + pokemonsEscolhidos.get(i).nome);
            }
        }
    }

    public void capturarPokemon(Pokemon pokemon) throws IOException {
        pokemon.serializarClasse();
        if (pokemonsEscolhidos.contains(pokemon)) {
            System.out.println("Você já capturou o " + pokemon.nome + "!");
        } else {
            pokemonsEscolhidos.add(pokemon); // Adiciona o Pokémon à lista
            if (pokemonEscolhido == null) {
                pokemonEscolhido = pokemon; // Define como o Pokémon escolhido, se ainda não houver um
            }
            System.out.println("Você capturou o " + pokemon.nome + "!");
        }
    }

    public void serializarClasse() throws IOException {
        FileOutputStream fos = new FileOutputStream(getArqSerialized());
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(this);
        os.close();
        fos.close();
    }

    public Treinador desserializarClasse() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(getArqSerialized());
        ObjectInputStream is = new ObjectInputStream(fis);
        Treinador t = (Treinador) is.readObject(); // Aqui usamos o tipo genérico Pokemon

        is.close();
        fis.close();
        return t; // Retorna o Pokémon desserializado
    }

    public String getArqSerialized() {
        return arqSerialized;
    }

    public void setArqSerialized(String arqSerialized) {
        this.arqSerialized = arqSerialized;
    }

    @Override
    public String toString() {
        String pok = "";
        for(Pokemon pk : pokemonsEscolhidos){
            pok += " | Nome: " + pk.getNome() + ", Tipo: " + pk.getTipo() +" | ";
        }
        return "Nome: " + nome + "\nGenero: " + genero + "\nBatalhas Vencidas: " + batalhasVencidas + "\nBatalhas Totais: " + batalhasTotais + "\nPokemons Escolhidos: " + pok;
    }

}

