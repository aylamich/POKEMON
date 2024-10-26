public abstract class Pokemon {
    protected String nome;
    protected String tipagem;
    protected int nivel;
    protected int vida;
    protected int dano;
    protected int danoEspecial;

    public Pokemon (String nome, String tipagem, int nivel, int vida, int dano, int danoEspecial) {
        this.nome = nome;
        this.tipagem = tipagem;
        this.nivel = 5;
        this.vida = 100;
        this.dano = 25;
        this.danoEspecial = 40;

    }

    public String getNome() {
        return nome;
    }
    public String getTipagem() {
        return tipagem;
    }
    public int getNivel () {
        return nivel;
    }
    public int passarNivel(){
        return nivel + 1;
    }

    public void atacar(){
        System.out.println(nome + "Usou investisda");
    }

    public abstract void exibirInfos();

    public abstract void atacarSpecial();
}
