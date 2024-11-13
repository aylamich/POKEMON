public class PokemonNaoEncontradoException extends Exception { // classe que extende de exception do java
    // Construtores para a exceção personalizada
    public PokemonNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PokemonNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
