package LN;

import java.sql.Date;

public class clsActividad 
{
	private String fecha,duracion;
	private int calorias;
	
	public clsActividad(String fecha, String duracion, int calorias) 
	{
		super();
		this.fecha = fecha;
		this.duracion = duracion;
		this.calorias = calorias;
	}

	public String getDuracion() 
	{
		return duracion;
	}

	public void setDuracion(String duracion) 
	{
		this.duracion = duracion;
	}

	public int getCalorias() 
	{
		return calorias;
	}

	public void setCalorias(int calorias) 
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
