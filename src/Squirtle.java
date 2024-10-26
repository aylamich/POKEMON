public class Squirtle extends Pokemon{


    public Squirtle(String nome,String tipagem, int nivel, int vida, int dano, int danoEspecial) {
        super(nome, tipagem, nivel, vida, dano, danoEspecial);

    }

    public void atacarSpecial(){
        System.out.println(nome + " usou Water Gun (Pistola de Água)!");
    }


    public void exibirInfos() {
        System.out.println("Squirtle é um Pokémon do tipo Água da primeira geração. Ele é uma pequena tartaruga azul com uma cauda em espiral. Evolui para Wartortle no nível 16 e depois para Blastoise no nível 36.");
        System.out.println("Squirtle é vulnerável a ataques de Elétrico e Planta e possui habilidades como Torrent, que aumenta seus ataques de água quando está com pouca vida.");

    }
}