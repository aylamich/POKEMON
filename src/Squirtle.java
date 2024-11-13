// Subclasse Squirtle
class Squirtle extends Pokemon {
    public Squirtle() {
        super("Squirtle", "Água", 100, 20, 40, "Hydro Pump");
        setArqSerialized("squirtle.txt");
    }

    @Override
    public void atacarNormal(Pokemon adversario) {
        // Ataque normal do Squirtle: "Tackle"
        System.out.println(this.nome + " usou Tackle!");
        adversario.vida -= this.danoNormal;
        System.out.println(adversario.nome + " sofreu " + this.danoNormal + " de dano.");
    }

    @Override // sobrescrita de um método não abstrato
    public void atacarEspecial(Pokemon adversario) {
        // Ataque especial do Squirtle: "Hydro Pump"
        if (adversario.tipo.equals("Fogo")) {
            // Água é super eficaz contra Fogo (dano multiplicado por 1.5)
            adversario.vida -= (int)(this.danoEspecial * 1.5);
            System.out.println(this.nome + " usou Hydro Pump (Água) super eficaz!");
        } else {
            super.atacarEspecial(adversario);
        }
    }

    @Override
    public void exibirResumo() {
        super.exibirResumo();
        System.out.println("Squirtle é um Pokémon da região Kanto. Ele é conhecido por sua habilidade de lançar jatos de água.");
    }
}
