package COMUN;

public class clsOpcEntrenamientoRepetida extends Exception 
{
	private static final long serialVersionUID = 1L;
	
	private final String MENSAJE = "El c�digo de la opci�n de entrenamiento ya est� en uso. Prueba con otro.";
	
	public String getMessage()
	{
		return MENSAJE;
	}
}
