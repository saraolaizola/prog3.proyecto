package LN;

import java.sql.Date;

public class clsCarrera extends clsActividad
{
	private double km;
	private String ritmo;
	

	public clsCarrera (String fecha, String duracion, int calorias, double km, String ritmo) 
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
	public String getRitmo() {
		return ritmo;
	}
	public void setRitmo(String ritmo) {
		this.ritmo = ritmo;
	}
	
	
}
