package exceptions;

public class RegistroUsadoException extends Exception{
	public RegistroUsadoException() {
		super("J� existe outra pessoa registrada sobre o mesmo registro");
	}
	public RegistroUsadoException(String message) {
		super(message);
	}
}
