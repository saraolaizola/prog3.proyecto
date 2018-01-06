package LN;

/**
 * Clase hija para guardar la información de los entrenamietos
 * Tendrá el siguiente atributos (además de los del padre): código
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
