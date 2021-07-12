package exceptions;

public class UsuarioLogadoInvalidoException extends Exception {
	public UsuarioLogadoInvalidoException() {
		super("O usuário logado precisa ser diretor para realizar essa ação\n");
	}
	public UsuarioLogadoInvalidoException(String message) {
		super(message);
	}
}
