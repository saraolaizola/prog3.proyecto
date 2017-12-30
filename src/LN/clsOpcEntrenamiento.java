package LN;
public class clsOpcEntrenamiento 
{
	private String codigo, nombre, nivel;
	private int duracion;
	private double calxsec;	
	public enum Nivel {Principiante, Intermedio, Experto}
	
	
	public clsOpcEntrenamiento(String codigo, String nombre, String nivel, int duracion,double calxsec) 
	{
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.nivel = nivel;
		this.calxsec = calxsec;
	}
	
	public clsOpcEntrenamiento()
	{
		
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

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public double getCalxsec() {
		return calxsec;
	}

	public void setCalxsec(double calxsec) {
		this.calxsec = calxsec;
	}
	
	
}
