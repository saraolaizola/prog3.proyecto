package LN;

import java.sql.Date;

public class clsActividad 
{
	private Date fecha;
	private double duracion;
	private int calorias;
	
	public clsActividad(Date fecha, double duracion, int calorias) 
	{
		super();
		this.fecha = fecha;
		this.duracion = duracion;
		this.calorias = calorias;
	}

	public double getDuracion() 
	{
		return duracion;
	}

	public void setDuracion(double duracion) 
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
}
