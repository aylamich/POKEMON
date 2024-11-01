public class Batalha {

    public static void realizarBatalha(Treinador treinador1, Treinador treinador2) {
        try {
            // ve se algum treinador não possui pokemon para jogar a exception
            if (treinador1.getPokemons().isEmpty() || treinador2.getPokemons().isEmpty()) {
                throw new TreinadorSemPokemonException("Um dos treinadores não tem Pokémon para batalhar!");
                // throw new joga o erro para não continuar a execução normal e passa a mensagem personalizada
                // new TreinadorSemPokemonException(...): cria uma nova instância da exceção personalizada
                // o erro específico é do tipo TreinadorSemPokemonException
            }

            Pokemon pokemon1 = treinador1.getPokemons().get(0);
            Pokemon pokemon2 = treinador2.getPokemons().get(0);
            // seleciona o primeiro pokemon de cada treinador para batalha

            System.out.println(treinador1.getNome() + " escolheu " + pokemon1.getNome() + "!");
            System.out.println(treinador2.getNome() + " escolheu " + pokemon2.getNome() + "!");

            // pra ver quem ganhou dependendo do nivel
            if (pokemon1.getNivel() > pokemon2.getNivel()) {
                System.out.println(pokemon1.getNome() + " venceu a batalha!");
            } else if (pokemon1.getNivel() < pokemon2.getNivel()) {
                System.out.println(pokemon2.getNome() + " venceu a batalha!");
            } else {
                System.out.println("A batalha terminou empatada!");
            }

        } catch (TreinadorSemPokemonException e) {
            // se o treinador nao tem pokemon cai nessa exception e exibe a mensagem personalizada
            System.out.println("Erro: " + e.getMessage());
        //} catch (BatalhaException e) {
            // captura outras exceções relacionadas à batalha
           // System.out.println("Ocorreu um erro de batalha: ");
        }
    }
}