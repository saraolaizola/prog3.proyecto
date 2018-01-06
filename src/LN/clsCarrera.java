package LN;

/**
 * Clase hija para guardar la informaci�n de las carreras 
 * Tendr� los siguientes atributos (adem�s de los del padre): km y ritmo 
 * @author ALUMNO
 *
 */
public class clsCarrera extends clsActividad
{
	private double km;
	private String ritmo;	

	public clsCarrera (String fecha, String duracion, double calorias, double km, String ritmo) 
	{
		super(fecha, duracion, calorias);
		this.km = km;
		this.ritmo = ritmo;
	}
	
	public clsCarrera(){
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
