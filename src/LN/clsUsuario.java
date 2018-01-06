package LN;

/**
 * Clase para guardar la información de los usuarios 
 * Tendrá los siguientes atributos: usuario y contraseña,
 * nombre, apellido, peso, altura y sexo 
 * @author ALUMNO
 *
 */
public class clsUsuario 
{
	private String usuario;
	private String contraseña;
	
	private String nombre;
	private String apellido;
	private double peso;
	private double altura;
	private String sexo;

	public clsUsuario(String usuario, String contraseña, String nombre, String apellido, double peso, double altura, String sexo) 
	{
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
	
}
