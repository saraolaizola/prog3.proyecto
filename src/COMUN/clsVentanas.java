package COMUN;

import java.util.ArrayList;

import javax.swing.JFrame;

import LP.frCarrera;
import LP.frDetalleCarrera;
import LP.frDetalleEntrena;
import LP.frElegirEntrena;
import LP.frEntrena;
import LP.frLista;
import LP.frListaCarrera;
import LP.frListaEntrena;
import LP.frPerfil;
import LP.frPrincipal;

public class clsVentanas 
{
	String usuario;
	JFrame ventana;
	
	frCarrera carrera;
	frEntrena entrena;
	frElegirEntrena elegir;
	frLista lista;
	frPerfil perfil;
	frPrincipal principal;
	frListaCarrera listaC;
	frListaEntrena listaE;
	frDetalleCarrera detalleC;
	frDetalleEntrena detalleE;
	
	public clsVentanas(String usuario)
	{
		this.usuario=usuario;
		carrera = new frCarrera();
		entrena = new frEntrena();
		elegir = new frElegirEntrena();
		lista = new frLista(usuario);
		perfil = new frPerfil();
		principal = new frPrincipal(usuario);
		listaC = new frListaCarrera();
		listaE = new frListaEntrena();
	}
	
	
	
	public void VentanaSiguiente(JFrame ventanaActual,int num, String variable)
	{
		switch (ventanaActual.getName())
		{
			case "frPrincipal":
				if (num==1)
				{	
					elegir.setVisible(true); break; 
				}
				else
				{
					carrera.setVisible(true); break;
				}
			case "frElegirEntrena":
				entrena.setVisible(true);
				break;
			case "frEntrena":
				lista.setVisible(true);
				break;
			case "frCarrera":
				lista.setVisible(true);
				break;
			case "frLista":
				if (num==1)
				{
					listaE.setVisible(true); break;
				}
				else
				{
					listaC.setVisible(true); break;
				}
			case "frListaCarrera":
				detalleC = new frDetalleCarrera(variable);
				detalleC.setVisible(true);
				break;
			case "frListaEntrena":
				detalleE = new frDetalleEntrena(variable);
				detalleE.setVisible(true);
				break;
		}
		ventanaActual.dispose();
	}
	
	public void VentanaAnterior(JFrame ventanaActual)
	{
		switch (ventanaActual.getName())
		{
			case "frElegirEntrena":
				principal.setVisible(true);
				break;
			case "frEntrena":
				elegir.setVisible(true);
				break;
			case "frCarrera":
				principal.setVisible(true);
				break;
			case "frListaEntrena":
				lista.setVisible(true);
				break;
			case "frListaCarrera":
				lista.setVisible(true);
				break;
			case "detalleEntrena":
				listaE.setVisible(true);
				break;
			case "detalleCarrera":
				listaC.setVisible(true);
				break;
		}
		ventanaActual.dispose();
	}
	
	
}
