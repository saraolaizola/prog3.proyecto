package LN;

import java.io.File;

/**
 * Clase para guardar la información de las opciones de entrenamiento 
 * Tendrá los siguientes atributos: código, nombre, nivel, duración, calorías por segundo y el fichero con el vídeo
 * @author ALUMNO
 *
 */
public class clsOpcEntrenamiento 
{
	private String codigo, nombre, nivel;
	private File file;
	private int duracion;
	private double calxsec;	
	public enum Nivel {Principiante, Intermedio, Experto}
	
	
	public clsOpcEntrenamiento(File file, String codigo, String nombre, String nivel, int duracion,double calxsec) 
	{
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.nivel = nivel;
		this.calxsec = calxsec;
		this.file = file;
	}
	
	public clsOpcEntrenamiento()
	{
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public double getCalxsec() {
		return calxsec;
	}

	public void setCalxsec(double calxsec) {
		this.calxsec = calxsec;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	
}
