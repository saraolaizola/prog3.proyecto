package LD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import COMUN.clsSinActividad;
import COMUN.clsUsuarioRepetido;
import LN.clsCarrera;
import LN.clsEntrenamiento;
import LN.clsOpcEntrenamiento;
import LN.clsUsuario;

public class BD 
{
	private static Logger logger = Logger.getLogger( BD.class.getName() );
	private static Connection connection = null;
	private static Statement statement = null;
	private static ArrayList <clsOpcEntrenamiento> lista;
	private static ArrayList <clsCarrera> listaCarreras;
	
	//init BD y crear tabla juntos 
	
	/** Inicializa una BD SQLITE y devuelve una conexión con ella. Debe llamarse a este 
	 * método antes que ningún otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexión con la base de datos indicada. Si hay algún error, se devuelve null
	 */
	public static Connection initBD( String nombreBD ) 
	{
		try 
		{
		    Class.forName("org.sqlite.JDBC");
		    connection = DriverManager.getConnection("jdbc:sqlite:" + nombreBD );
			statement = connection.createStatement();
			statement.setQueryTimeout(30);  // poner timeout 30 msg
		    return connection;
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
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
				"(usuario string, contraseña string, nombre string, apellido string, peso real, altura real, sexo string, primary key (usuario))");
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
				"(fecha string, duracion string,  calorias real, codigo string, usuario string, primary key (fecha))");
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
				"(fecha string, duracion string, calorias real, km real, ritmo string, usuario string, primary key (fecha))");
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
				"(fichero string, codigo string, nombre string, nivel string, calxsec real, duracion integer, primary key (codigo))");
		} 
		catch (SQLException e) 
		{
			logger.log(Level.SEVERE, e.getMessage());
		}
	}
	
	/**
	 * Guarda los datos del usuario en la tabla Usuarios
	 * @param usuario
	 * @param contraseña
	 * @param nombre
	 * @param apellido
	 * @param peso
	 * @param altura
	 * @param sexo
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
	 * Actualiza los atributos del usuario (nombre y apellido)
	 * usando como identificativo el nombre de usuario
	 * @param usuario
	 * @param nombre
	 * @param apellido
	 */
	public static void editarUsuario (String usuario, String nombre, String apellido)
	{
		try
		{		    
			statement.executeUpdate("update usuarios set nombre = '"+nombre+"', apellido = '"+apellido+"' where (usuario = '"+usuario+"');");
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		} 
	}

	 /**
	  * Comprueba que el usuario y la contraseña son correctas
	  * @param usuario
	  * @param contraseña
	  * @return true si es correcto, false si no coinciden
	  * @throws ClassNotFoundException 
	  */
	public static boolean inicioSesion (String usuario, String contraseña) throws ClassNotFoundException
	{
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from usuarios");
		    	
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
	 * Método que devuelve el objeto usuario con todos sus atributos
	 * identificandolo mediante el nombre de usuario
	 * @param usuario
	 * @return el objeto usuario
	 */
	public static clsUsuario getUser (String usuario)
	{
		clsUsuario user = new clsUsuario();
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from usuarios");
			while(rs.next())
			{
				if (rs.getString("usuario").equals(usuario))
		       	{
					user.setUsuario(usuario);
					user.setContraseña(rs.getString("contraseña"));
					user.setNombre(rs.getString("nombre"));
					user.setApellido(rs.getString("apellido"));
					user.setSexo(rs.getString("sexo"));
					user.setAltura(rs.getDouble("altura"));
					user.setPeso(rs.getDouble("peso"));
		       	}
			}
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return user;
	}
	
	/**
	 * Método para conseguir una opción de entrenamiento con todos sus atributos
	 * identificandolo meidante su código 
	 * @param codigo
	 * @return el objeto entrenamiento
	 */
	public static clsOpcEntrenamiento getEntrena (String codigo)
	{
		clsOpcEntrenamiento entrena = new clsOpcEntrenamiento();
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from opcionentrenamiento");
			while(rs.next())
			{
				if (rs.getString("codigo").equals(codigo))
		       	{
					File f = new File ((rs.getString("fichero")));
					entrena.setFile(f);
					entrena.setCodigo(rs.getString("codigo"));
					entrena.setNombre(rs.getString("nombre"));
					entrena.setNivel(rs.getString("nivel"));
					entrena.setCalxsec(rs.getDouble("calxsec"));
					entrena.setDuracion(rs.getInt("duracion"));
		       	}
			}
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return entrena;
	}
	
	/**
	 * Método para conseguir un de entrenamiento con todos sus atributos
	 * identificandolo meidante la fecha
	 * @param fecha
	 * @return el objeto entrenamiento
	 */
	public static clsEntrenamiento getEntrenamiento (String fecha)
	{
		clsEntrenamiento entrena = new clsEntrenamiento();
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from entrenamiento");
			while(rs.next())
			{
				if (rs.getString("fecha").equals(fecha))
		       	{
					entrena.setCodigo(rs.getString("codigo"));
					entrena.setFecha(rs.getString("fecha"));
					entrena.setDuracion(rs.getString("duracion"));
					entrena.setCalorias(rs.getDouble("calorias"));		
		       	}
			}
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return entrena;
	}
	
	/**
	 * Método para conseguir una carrera con todos sus atributos
	 * identificandolo meidante su fecha 
	 * @param fecha
	 * @return el objeto carrera
	 */
	public static clsCarrera getCarrera (String fecha)
	{
		clsCarrera carrera = new clsCarrera();
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from carrera");
			while(rs.next())
			{
				if (rs.getString("fecha").equals(fecha))
		       	{
					carrera.setFecha(rs.getString("fecha"));
					carrera.setDuracion(rs.getString("duracion"));
					carrera.setCalorias(rs.getDouble("calorias"));
					carrera.setKm(rs.getInt("km"));
					carrera.setRitmo(rs.getString("ritmo"));
		       	}
			}
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return carrera;
	}
	
	/**
	 * Guarda los datos de la carrera en la tabla Carrera
	 * @param fecha
	 * @param duracion
	 * @param calorias
	 * @param km
	 * @param ritmo
	 * @param usuario
	 * @throws ClassNotFoundException
	 */
	public static void registrarCarrera (String fecha, String duracion, double calorias,  double km, String ritmo, String usuario) throws ClassNotFoundException
	{
		try
		{	
			statement.executeUpdate("insert into carrera values("+fecha+", '"+duracion+"', "+calorias+", "+km+", '"+ritmo+"', '"+usuario+"')");
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
	public static void registrarEntrenamiento (String fecha, String duracion, double calorias, String codigo, String usuario) throws ClassNotFoundException
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
	 * Guarda los datos de la opción de entrenamiento en la tabla OpcionEntrenamiento
	 * @param file
	 * @param codigo
	 * @param nombre
	 * @param nivel
	 * @param calxsec
	 * @param duracion
	 */
	public static void registrarOpcEntrenamiento (File file, String codigo, String nombre, String nivel, double calxsec, int duracion) 
	{
		try
		{	
			statement.executeUpdate("insert into opcionentrenamiento values('"+file.getAbsolutePath()+"','"+codigo+"','"+nombre+"','"+nivel+"',"+calxsec+","+duracion+")");
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		} 
	}
	
	/**
	 * Método para obtener todas las opciones de entrenamiento registradas en la 
	 * base de datos OpcionEntrenamiento mediante una lista de objetos clsOpcEntrenamiento
	 * @return ArrayList de todas las opciones de entrenamiento registradas
	 */
	public static ArrayList <clsOpcEntrenamiento> getLista ()
	{
		lista = new ArrayList<clsOpcEntrenamiento>();
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from opcionentrenamiento");
			while(rs.next())
			{	
				File fichero = new File (rs.getString("fichero"));
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				String nivel = rs.getString("nivel");
				int duracion = rs.getInt("duracion");
				double calxsec = rs.getDouble("calxsec");
				clsOpcEntrenamiento entrenamiento = new clsOpcEntrenamiento(fichero,codigo, nombre, nivel, duracion,calxsec);
				lista.add(entrenamiento);
			}
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return lista;
	}
	
	/**
	 * Método para obtener todas lascarreras registradas en la 
	 * base de datos Carreras mediante una lista de objetos clsCarrera
	 * @param usuario
	 * @return ArrayList de objetos carrera
	 * @throws clsSinActividad
	 */
	public static ArrayList <clsCarrera> getMisCarreras (String usuario) throws clsSinActividad
	{
		listaCarreras = new ArrayList<clsCarrera>();
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from carrera where usuario = '"+usuario+"';");
			while(rs.next())
			{	
				String fecha = rs.getString("fecha");
				String duracion = rs.getString("duracion");
				double calorias = rs.getDouble("calorias");
				double km = rs.getDouble("km");
				String ritmo = rs.getString("ritmo");
				clsCarrera carrera = new clsCarrera(fecha, duracion, calorias, km, ritmo);
				listaCarreras.add(carrera);
			}
			if (listaCarreras.size()<1) throw new clsSinActividad();
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return listaCarreras;
	}
	
	/**
	 * Método para obtener todos los entrenamientos registrados en la 
	 * base de datos Entrenamiento mediante una lista de objetos clsEntrenamiento
	 * @param usuario
	 * @return ArrayList de entrenamientos
	 * @throws clsSinActividad
	 */
	public static ArrayList <clsEntrenamiento> getMisEntrenamientos(String usuario) throws clsSinActividad 
	{
		ArrayList <clsEntrenamiento> listaEntrenas = new ArrayList<clsEntrenamiento>();
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from entrenamiento where usuario = '"+usuario+"';");
			while(rs.next())
			{	
				clsEntrenamiento entrena = new clsEntrenamiento ();
				entrena.setCodigo(rs.getString("codigo"));
				entrena.setFecha(rs.getString("fecha"));
				entrena.setDuracion(rs.getString("duracion"));
				entrena.setCalorias(rs.getDouble("calorias"));
				listaEntrenas.add(entrena);
			}
			
			if (listaEntrenas.size()<1) throw new clsSinActividad();
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return listaEntrenas;
	}
}