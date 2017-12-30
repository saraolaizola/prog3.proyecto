package LN;

import java.sql.Date;

import LD.BD;

public class clsEntrenamiento extends clsActividad
{

	String codigo;
	
	public clsEntrenamiento(String fecha, String duracion, double calorias, String codigo) 
	{
		super(fecha, duracion, calorias);
		this.codigo = codigo;
	}
	
	public clsEntrenamiento()
	{
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



}
