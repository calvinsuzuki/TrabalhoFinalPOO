package exceptions;

public class UsuarioLogadoInvalidoException extends Exception {
	public UsuarioLogadoInvalidoException() {
		super("O usu�rio logado precisa ser diretor para realizar essa a��o\n");
	}
	public UsuarioLogadoInvalidoException(String message) {
		super(message);
	}
}
