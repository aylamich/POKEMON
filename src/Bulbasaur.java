// Subclasse Bulbasaur
class Bulbasaur extends Pokemon {
    public Bulbasaur() {
        super("Bulbasaur", "Planta", 100, 15, 30, "SolarBeam");
        setArqSerialized("bulbasaur.txt");
    }

    @Override
    public void atacarNormal(Pokemon adversario) {
        // Ataque normal do Bulbasaur: "Vine Whip"
        System.out.println(this.nome + " usou Vine Whip!");
        adversario.vida -= this.danoNormal;
        System.out.println(adversario.nome + " sofreu " + this.danoNormal + " de dano.");
    }

    @Override
    public void atacarEspecial(Pokemon adversario) {
        // Ataque especial do Bulbasaur: "SolarBeam"
        if (adversario.tipo.equals("Água")) {
            // Planta é super eficaz contra Água (dano multiplicado por 2)
            adversario.vida -= this.danoEspecial * 2;
            System.out.println(this.nome + " usou SolarBeam (Planta) super eficaz!");
        } else {
            super.atacarEspecial(adversario);
        }
    }

    @Override
    public void exibirResumo() {
        super.exibirResumo();
        System.out.println("Bulbasaur é um Pokémon da região Kanto. Ele tem um bulbo nas costas que se transforma em uma planta.");
    }
}