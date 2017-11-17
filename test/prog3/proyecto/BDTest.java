package prog3.proyecto;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import LN.clsUsuario;

public class BDTest 
{

	private ArrayList<clsUsuario> miListaUsuarios;
	
	@Before
	public void setUp() throws Exception 
	{
		
		miListaUsuarios = new ArrayList<clsUsuario>();
	//	miListaUsuarios.add( new Usuario( "buzz", "#9abbf", "Buzz", "Lightyear", 101202303, TipoUsuario.Admin, "buzz@gmail.com", "amigo.de.woody@gmail.com" ) );
	}

	@After
	public void tearDown() throws Exception 
	{
		miListaUsuarios.clear();
	}

	
	
	@Test
	public void test() 
	{
		fail("Not yet implemented");
	}

}
