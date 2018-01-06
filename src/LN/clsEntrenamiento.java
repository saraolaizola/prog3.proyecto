package LN;

/**
 * Clase hija para guardar la informaci�n de los entrenamietos
 * Tendr� el siguiente atributos (adem�s de los del padre): c�digo
 * @author ALUMNO
 *
 */
public class clsEntrenamiento extends clsActividad
{

	String codigo;
	
	public clsEntrenamiento(String fecha, String duracion, double calorias, String codigo) 
	{
		super(fecha, duracion, calorias);
		this.codigo = codigo;
	}
	
	public clsEntrenamiento(){
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



}
