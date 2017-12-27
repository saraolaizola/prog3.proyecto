package LN;
public class clsOpcEntrenamiento 
{
	private String codigo, nombre, nivel;
	private int duracion,calxmin;	
	public enum Nivel {Principiante, Intermedio, Experto}
	
	
	public clsOpcEntrenamiento(String codigo, String nombre, String nivel, int duracion,int calxmin) 
	{
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.nivel = nivel;
		this.calxmin = calxmin;
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

	public int getCalxmin() {
		return calxmin;
	}

	public void setCalxmin(int calxmin) {
		this.calxmin = calxmin;
	}
	
	
}
