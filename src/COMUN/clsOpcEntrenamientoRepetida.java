package COMUN;

public class clsOpcEntrenamientoRepetida extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	private final String MENSAJE = "El código de la opción de entrenamiento ya está en uso. Prueba con otro.";
	
	public String getMessage()
	{
		return MENSAJE;
	}
}
