package exceptions;

public class LoginFalhouException extends Exception{
	public LoginFalhouException() {
		super("Usu�rio e/ou senha inv�lidos!");
	}
	public LoginFalhouException(String message) {
		super(message);
	}
}
