package LN;

import java.sql.Date;

public class clsActividad 
{
	private String fecha,duracion;
	private double calorias;
	
	public clsActividad(String fecha, String duracion, double calorias) 
	{
		super();
		this.fecha = fecha;
		this.duracion = duracion;
		this.calorias = calorias;
	}

	public clsActividad()
	{
		
	}
	
	public String getDuracion() 
	{
		return duracion;
	}

	public void setDuracion(String duracion) 
	{
		this.duracion = duracion;
	}

	public double getCalorias() 
	{
		return calorias;
	}

	public void setCalorias(double calorias) 
	{
		this.calorias = calorias;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
