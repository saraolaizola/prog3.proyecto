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

import COMUN.clsOpcEntrenamientoRepetida;
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
	
	/** Inicializa una BD SQLITE y devuelve una conexi�n con ella. Debe llamarse a este 
	 * m�todo antes que ning�n otro, y debe devolver no null para poder seguir trabajando con la BD.
	 * @param nombreBD	Nombre de fichero de la base de datos
	 * @return	Conexi�n con la base de datos indicada. Si hay alg�n error, se devuelve null
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
	
	/** Cierra la conexi�n con la Base de Datos
	 */
	public static void close() {
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/** Devuelve la conexi�n si ha sido establecida previamente (#initBD()).
	 * @return	Conexi�n con la BD, null si no se ha establecido correctamente.
	 */
	public static Connection getConnection() {
		return connection;
	}
	
	/** Devuelve una sentencia para trabajar con la BD,
	 * si la conexi�n si ha sido establecida previamente (#initBD()).
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
				"(usuario string, contrase�a string, nombre string, apellido string, peso real, altura real, sexo string, primary key (usuario))");
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
	 * @param nombre
	 * @param apellido
	 * @param peso
	 * @param altura
	 * @param sexo
	 * @param usuario 
	 * @param contrase�a
	 * @throws ClassNotFoundException
	 * @throws clsUsuarioRepetido
	 */
	public static void altaUsuario (String usuario, String contrase�a, String nombre, String apellido, double peso,double altura, String sexo) throws ClassNotFoundException, clsUsuarioRepetido
	{
		try
		{		    
			statement.executeUpdate("insert into usuarios values('"+usuario+"', '"+contrase�a+"', '"+nombre+"', '"+apellido+"', "+peso+", "+altura+", '"+sexo+"')");
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
			throw new clsUsuarioRepetido();
		} 
	}
	 

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
	  * Comprueba que el usuario y la contrase�a son correctas
	  * @param usuario
	  * @param contrase�a
	  * @return true si es correcto, false si no coinciden
	  * @throws ClassNotFoundException ?�
	  */
	public static boolean inicioSesion (String usuario, String contrase�a) throws ClassNotFoundException
	{
		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from usuarios");
		    	
			while(rs.next())
			{
				if (rs.getString("usuario").equals(usuario))
		       	{
					if (rs.getString("contrase�a").equals(contrase�a))
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
					user.setContrase�a(rs.getString("contrase�a"));
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
	
	
	public static void registrarOpcEntrenamiento (File file, String codigo, String nombre, String nivel, double calxsec, int duracion) throws clsOpcEntrenamientoRepetida
	{
		try
		{	
			statement.executeUpdate("insert into opcionentrenamiento values('"+file.getAbsolutePath()+"','"+codigo+"','"+nombre+"','"+nivel+"',"+calxsec+","+duracion+")");
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
			throw new clsOpcEntrenamientoRepetida();
		} 
	}
	
	public static void meterOpciones()
	{
		try 
		{
			File f = new File ("C:\\Users\\ALUMNO\\workspace\\prog3.proyecto\\videos\\Wildlife.wmv");
			BD.registrarOpcEntrenamiento(f,"001", "Abdominales", "Principiante", 0.01,10);
			//https://www.youtube.com/watch?v=1919eTCoESo&list=PL6070A835F843D79F
			BD.registrarOpcEntrenamiento(f,"002", "Cardio quema grasas", "Intermedio", 0.02, 20);
			//https://www.youtube.com/watch?v=fcN37TxBE_s
			BD.registrarOpcEntrenamiento(f,"003", "Cardio Kick Boxing", "Experto", 0.03, 15);
			//https://www.youtube.com/watch?v=Vve4BVTZ0QU
		} 
		catch (clsOpcEntrenamientoRepetida e){
		}
		
	}
	
	
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
	
	public static ArrayList <clsCarrera> getMisCarreras(String usuario)
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
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return listaCarreras;
	}
	
	public static ArrayList <clsEntrenamiento> getMisEntrenamientos(String usuario)
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
		}	 
		catch(SQLException e)
		{
			logger.log(Level.WARNING, e.getMessage());
		}
		return listaEntrenas;
	}
}