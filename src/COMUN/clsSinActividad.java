package COMUN;

/**
 * Saltar� un error cuando la lista de carreras o entrenamientos
 * este vac�a; no se haya registrado actividad alguna
 * @author ALUMNO
 */
public class clsSinActividad extends Exception
{
	private static final long serialVersionUID = 1L;
	
	private final String MENSAJE = "No hay ninguna actividad registrada";
	
	public String getMessage()
	{
		return MENSAJE;
	}
}
