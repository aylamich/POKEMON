public class Bulbasaur extends Pokemon{


    public Bulbasaur(String nome,String tipagem, int nivel, int vida, int dano, int danoEspecial) {
        super(nome, tipagem, nivel, vida, dano, danoEspecial);

    }

    public void atacarSpecial() {
        System.out.println(nome + "usou chicote de vinha");
    }


    public void exibirInfos(){
        System.out.println("Bulbasaur é um Pokémon do tipo Planta e Venenoso da primeira geração, com uma semente nas costas que cresce conforme ele evolui. Ele evolui para Ivysaur no nível 16 e para Venusaur no nível 32. Suas habilidades incluem Overgrow, que aumenta o poder de movimentos de Planta quando sua vida está baixa. É vulnerável a ataques dos tipos Fogo, Voador, Gelo e Psíquico e pode usar ataques como Vine Whip e Razor Leaf");
    }
}