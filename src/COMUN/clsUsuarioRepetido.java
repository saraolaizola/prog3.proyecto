package COMUN;

public class clsUsuarioRepetido extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String MENSAJE = "Ese nombre de usuario ya est� en uso. Prueba con otro.";
	
	public String getMessage()
	{
		return MENSAJE;
	}
}
