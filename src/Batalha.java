public class Batalha {

    public static void realizarBatalha(Treinador treinador1, Treinador treinador2) {
        try {
            if (treinador1.getPokemons().isEmpty() || treinador2.getPokemons().isEmpty()) {
                System.out.println("Um dos treinadores não tem Pokémon para batalhar!");
                return;
            }

            Pokemon pokemon1 = treinador1.getPokemons().get(0);
            Pokemon pokemon2 = treinador2.getPokemons().get(0);

            System.out.println(treinador1.getNome() + " escolheu " + pokemon1.getNome() + "!");
            System.out.println(treinador2.getNome() + " escolheu " + pokemon2.getNome() + "!");

            if (pokemon1.getNivel() > pokemon2.getNivel()) {
                System.out.println(pokemon1.getNome() + " venceu a batalha!");
            } else if (pokemon1.getNivel() < pokemon2.getNivel()) {
                System.out.println(pokemon2.getNome() + " venceu a batalha!");
            } else {
                System.out.println("A batalha terminou empatada!");
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao realizar a batalha: " + e.getMessage());
        }
    }
}