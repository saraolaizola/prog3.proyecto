package LN;

import java.sql.Date;

import LD.BD;

public class clsEntrenamiento extends clsActividad
{

	public clsEntrenamiento(Date fecha, double duracion, int calorias) 
	{
		super(fecha, duracion, calorias);
	}
	
	
	public int getCal (double duracion, int calxmin) 
	{
		return (int) (duracion*calxmin);
	}
	
	
}
