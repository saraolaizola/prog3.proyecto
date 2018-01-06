package COMUN;

/**
 * Saltará un error cuando un usuario intente registrarse 
 * con un nombre de usuario ya registrado en la base de datos
 * @author ALUMNO
 */
public class clsUsuarioRepetido extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	private final String MENSAJE = "Ese nombre de usuario ya está en uso. Prueba con otro.";
	
	public String getMessage()
	{
		return MENSAJE;
	}
}
