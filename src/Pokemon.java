import java.io.Serializable;
import java.util.Scanner;
import java.io.*;

// Classe base Pokémon
public abstract class Pokemon implements Serializable {
    protected String nome;
    protected String tipo;
    protected int vida;
    protected int danoNormal;
    protected int danoEspecial;
    protected String ataqueEspecialNome;
    private String arqSerialized = "";


    // Construtor
    public Pokemon(String nome, String tipo, int vida, int danoNormal, int danoEspecial, String ataqueEspecialNome) {
        this.nome = nome;
        this.tipo = tipo;
        this.vida = vida;
        this.danoNormal = danoNormal;
        this.danoEspecial = danoEspecial;
        this.ataqueEspecialNome = ataqueEspecialNome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDanoNormal() {
        return danoNormal;
    }

    public void setDanoNormal(int danoNormal) {
        this.danoNormal = danoNormal;
    }

    public int getDanoEspecial() {
        return danoEspecial;
    }

    public void setDanoEspecial(int danoEspecial) {
        this.danoEspecial = danoEspecial;
    }



    // Método abstrato para ataque normal (a ser sobrescrito nas subclasses)
    public abstract void atacarNormal(Pokemon adversario);

    // Método concreto para ataque especial (a ser sobrescrito nas subclasses)
    public  void atacarEspecial(Pokemon adversario) {
        System.out.println(this.nome + " usou " + this.ataqueEspecialNome + "!");
        adversario.vida -= this.danoEspecial;
        System.out.println(adversario.nome + " sofreu " + this.danoEspecial + " de dano.");
    }

    // Método para exibir resumo do Pokémon (pode ser sobrescrito nas subclasses)
    public void exibirResumo() {
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Dano Normal: " + danoNormal);
        System.out.println("Dano Especial: " + danoEspecial);
        System.out.println("Ataque Especial: " + ataqueEspecialNome);
    }

    // Método para restaurar a vida do Pokémon
    public void restaurarVida() {
        this.vida = 100; // ou o valor inicial da vida do Pokémon
    }

    public void serializarClasse() throws IOException {
        FileOutputStream fos = new FileOutputStream(getArqSerialized());
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(this);
        os.close();
        fos.close();
    }

    public Pokemon desserializarClasse() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(getArqSerialized());
        ObjectInputStream is = new ObjectInputStream(fis);
        Pokemon p = (Pokemon) is.readObject(); // Aqui usamos o tipo genérico Pokemon
        is.close();
        fis.close();
        return p; // Retorna o Pokémon desserializado
    }

    public String getArqSerialized() {
        return arqSerialized;
    }

    public void setArqSerialized(String arqSerialized) {
        this.arqSerialized = arqSerialized;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nTipo: " + tipo + "\nvida: " + vida + "\nDano Normal: " + danoNormal + "\nDano Especial: " + danoEspecial + "\nAtaque Especial: " + danoEspecial + "\nNome do Ataque Especial: " + ataqueEspecialNome;
    }
}
