package LN;

import java.sql.Date;

public class clsCarrera extends clsActividad
{
	private double km;
	private double ritmo;
	

	public clsCarrera (Date fecha, double duracion, int calorias, double km, double ritmo) 
	{
		super(fecha, duracion, calorias);
		this.km = km;
		this.ritmo = ritmo;
	}
	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}
	public double getRitmo() {
		return ritmo;
	}
	public void setRitmo(double ritmo) {
		this.ritmo = ritmo;
	}
	
	
}
