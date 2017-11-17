package LN;

import java.sql.Date;

public class clsCarrera extends clsActividad
{
	private double km;
	private double elevacion;
	private double ritmo;
	

	public clsCarrera (Date fecha, double duracion, int calorias, double km, double elevacion, double ritmo) 
	{
		super(fecha, duracion, calorias);
		this.km = km;
		this.elevacion = elevacion;
		this.ritmo = ritmo;
	}

	public double getKm() {
		return km;
	}

	public void setKm(double km) {
		this.km = km;
	}

	public double getElevacion() {
		return elevacion;
	}

	public void setElevacion(double elevacion) {
		this.elevacion = elevacion;
	}

	public double getRitmo() {
		return ritmo;
	}

	public void setRitmo(double ritmo) {
		this.ritmo = ritmo;
	}
	
	
}
