package LN;

/**
 * Clase para guardar la informaci�n de los usuarios 
 * Tendr� los siguientes atributos: usuario y contrase�a,
 * nombre, apellido, peso, altura y sexo 
 * @author ALUMNO
 *
 */
public class clsUsuario 
{
	private String usuario;
	private String contrase�a;
	
	private String nombre;
	private String apellido;
	private double peso;
	private double altura;
	private String sexo;

	public clsUsuario(String usuario, String contrase�a, String nombre, String apellido, double peso, double altura, String sexo) 
	{
		super();
		this.usuario = usuario;
		this.contrase�a = contrase�a;
		this.nombre = nombre;
		this.apellido = apellido;
		this.peso = peso;
		this.altura = altura;
		this.sexo = sexo;
	}

	public clsUsuario()
	{
	
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	
	
	
}
