package LD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import COMUN.clsOpcEntrenamientoRepetida;
import COMUN.clsUsuarioRepetido;

public class BD 
{
	private static Logger logger = Logger.getLogger( BD.class.getName() );
	private static Connection connection = null;
	private static Statement statement = null;
	
	//init BD y crear tabla juntos 
	
	/** Inicializa una BD SQLITE y devuelve una conexión con ella. Debe llamarse a este 
	 * método antes que ningún otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) 
	{
		try {
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
	
	/** Cierra la conexión con la Base de Datos
	 */
	public static void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Devuelve la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Conexión con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la BD,
	 * si la conexión si ha sido establecida previamente (#initBD()).
	 * @return	Sentencia de trabajo con la BD, null si no se ha establecido correctamente.
	 */
	public static Statement getStatement() {
		return statement;
	}
	
	/**
	 * Crea la tabla usuarios
	 */
	public static void crearTablaUsuarios ()
	{
		if (statement==null) return;
		try 
		{
			statement.executeUpdate("create table if not exists usuarios " +
				"(usuario string, contraseña string, nombre string, apellido string, peso double, altura double, sexo string, primary key (usuario))");
		} 
		catch (SQLException e) 
		{
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * Crea la tabla entrenamiento
	 */
	public static void crearTablaEntrenamiento ()
	{
		if (statement==null) return;
		try 
		{
			statement.executeUpdate("create table if not exists entrenamiento " +
				"(fecha date, durancion double,  calorias integer, codigo string, usuario string, primary key (fecha))");
		} 
		catch (SQLException e) 
		{
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * Crea la tabla Carrera
	 */
	public static void crearTablaCarrera ()
	{
		if (statement==null) return;
		try 
		{
			statement.executeUpdate("create table if not exists carrera " +
				"(fecha date, duracion double, calorias integer, km double, ritmo double, usuario string, primary key (fecha))");
		} 
		catch (SQLException e) 
		{
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * Crea la tabla de las opciones de entrenamientos
	 */
	public static void crearTablaOpcEntrenamiento ()
	{
		if (statement==null) return;
		try 
		{
			statement.executeUpdate("create table if not exists opcionentrenamiento " +
				"(codigo string, nombre string, calxmin double, primary key (codigo))");
		} 
		catch (SQLException e) 
		{
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * Guarda los datos del usuario en la tabla Usuarios
	 * @param nombre
	 * @param apellido
	 * @param peso
	 * @param altura
	 * @param sexo
	 * @param usuario 
	 * @param contraseña
	 * @throws ClassNotFoundException
	 * @throws clsUsuarioRepetido
	 */
	public static void altaUsuario (String usuario, String contraseña, String nombre, String apellido, double peso,double altura, String sexo) throws ClassNotFoundException, clsUsuarioRepetido
	{
		try
		{		    
			statement.executeUpdate("insert into usuarios values('"+usuario+"', '"+contraseña+"', '"+nombre+"', '"+apellido+"', "+peso+", "+altura+", '"+sexo+"')");
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
			throw new clsUsuarioRepetido();
		} 
	}
	 

	 /**
	  * Comprueba que el usuario y la contraseña son correctas
	  * @param usuario
	  * @param contraseña
	  * @return true si es correcto, false si no coinciden
	  * @throws ClassNotFoundException ?¿
	  */
	public static boolean inicioSesion (String usuario, String contraseña) throws ClassNotFoundException
	{
		try
		{
			ResultSet rs = statement.executeQuery("select * from usuario");
		    	
			while(rs.next())
			{
				if (rs.getString("usuario").equals(usuario))
		       	{
					if (rs.getString("contraseña").equals(contraseña))
		       		{
		       			return true;
		       		}
		       	}
			}
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return false;
	}
	 
	/**
	 * Guarda los datos de la carrera en la tabla Carrera
	 * @param fecha
	 * @param duracion
	 * @param calorias
	 * @param km
	 * @param elevacion
	 * @param ritmo
	 * @param usuario
	 * @throws ClassNotFoundException
	 */
	public static void registrarCarrera (Date fecha, double duracion, int calorias,  double km, double ritmo, String usuario) throws ClassNotFoundException
	{
		try
		{	
			statement.executeUpdate("insert into carrera values("+fecha+", "+duracion+", "+calorias+", "+km+", "+ritmo+", '"+usuario+"')");
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		} 
	}
	
	/**
	 * Guarda los datos del entrenamiento en la tabla Entrenamiento
	 * @param fecha
	 * @param duracion
	 * @param calorias
	 * @param codigo
	 * @param usuario
	 * @throws ClassNotFoundException
	 */
	public static void registrarEntrenamiento (Date fecha, double duracion, int calorias, String codigo, String usuario) throws ClassNotFoundException
	{
		try
		{	
			statement.executeUpdate("insert into entrenamiento values("+fecha+", "+duracion+", "+calorias+", '"+codigo+"', '"+usuario+"')");
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		} 
	}
	
	/**
	 * Guarda los datos de las opciones de entrenamiento en la tabla OpcEntrenamiento
	 * @param codigo
	 * @param nombre
	 * @param calxmin
	 * @throws clsOpcEntrenamientoRepetida 
	 */
	public static void registrarOpcEntrenamiento (String codigo, String nombre, double calxmin) throws clsOpcEntrenamientoRepetida
	{
		try
		{	
			statement.executeUpdate("insert into opcionentrenamiento values('"+codigo+"','"+nombre+"',"+calxmin+")");
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
			throw new clsOpcEntrenamientoRepetida();
		} 
	}
	
}