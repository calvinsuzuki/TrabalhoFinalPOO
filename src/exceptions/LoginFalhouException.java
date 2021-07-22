package exceptions;

public class LoginFalhouException extends Exception{
	public LoginFalhouException() {
		super("Usuário e/ou senha inválidos!");
	}
	public LoginFalhouException(String message) {
		super(message);
	}
}
