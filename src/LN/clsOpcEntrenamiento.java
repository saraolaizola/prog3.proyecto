package LN;

import COMUN.clsOpcEntrenamientoRepetida;
import LD.BD;;

public class clsOpcEntrenamiento 
{
	private String codigo, nombre, duracion, nivel;
	private double calxmin;	
	public enum Nivel {Principiante, Intermedio, Experto}
	
	
	public clsOpcEntrenamiento(String codigo, String nombre, String nivel, String duracion,double calxmin) 
	{
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.nivel = nivel;
		this.calxmin = calxmin;
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

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public double getCalxmin() {
		return calxmin;
	}

	public void setCalxmin(double calxmin) {
		this.calxmin = calxmin;
	}
	
	
}
