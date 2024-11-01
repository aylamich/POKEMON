public class TreinadorSemPokemonException extends BatalhaException {
    public TreinadorSemPokemonException(String mensagem) {
        super(mensagem);  // passa a mensagem para a classe Pai Exception
    }
}
