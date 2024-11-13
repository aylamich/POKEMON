// Subclasse Charmander
class Charmander extends Pokemon {
    public Charmander() {
        super("Charmander", "Fogo", 100, 18, 35, "Flamethrower");
        setArqSerialized("charmander.txt");
    }

    @Override
    public void atacarNormal(Pokemon adversario) {
        // Ataque normal do Charmander: "Scratch"
        System.out.println(this.nome + " usou Scratch!");
        adversario.vida -= this.danoNormal;
        System.out.println(adversario.nome + " sofreu " + this.danoNormal + " de dano.");
    }

    @Override
    public void atacarEspecial(Pokemon adversario) {
        // Ataque especial do Charmander: "Flamethrower"
        if (adversario.tipo.equals("Planta")) {
            // Fogo é super eficaz contra Planta (dano multiplicado por 1.5)
            adversario.vida -= this.danoEspecial * 1.5;
            System.out.println(this.nome + " usou Flamethrower (Fogo) super eficaz!");
        } else {
            super.atacarEspecial(adversario);
        }
    }

    @Override
    public void exibirResumo() {
        super.exibirResumo();
        System.out.println("Charmander é um Pokémon da região Kanto. Ele carrega uma chama na ponta de sua cauda.");
    }
}