public class Charmander extends Pokemon {


    public Charmander(String nome,String tipagem, int nivel, int vida, int dano, int danoEspecial) {
        super(nome, tipagem, nivel, vida, dano, danoEspecial);

    }

    public void atacar(){
        System.out.println(nome +"Usou arranhão");
    }

    public void atacarSpecial(){
        System.out.println(nome + "usou Flamethrower (Lança-Chamas)");
    }

    public void exibirInfos(){
        System.out.println("Charmander é um Pokémon do tipo Fogo da primeira geração. Ele é um pequeno lagarto laranja com uma chama na cauda, que representa sua energia vital. Evolui para Charmeleon no nível 16 e depois para Charizard no nível 36. Charmander é vulnerável a ataques de Água, Pedra e Terra e possui habilidades como Blaze, que aumenta seus ataques de fogo quando está com pouca vida.");
    }
}
