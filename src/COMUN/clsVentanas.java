package COMUN;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.tree.DefaultTreeModel;

import LN.clsUsuario;
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
	
	public clsVentanas(clsUsuario user)
	{
		this.usuario=usuario;
		carrera = new frCarrera(user);
//		entrena = new frEntrena(user);
		elegir = new frElegirEntrena(user);
		lista = new frLista(user);
		perfil = new frPerfil(user);
		principal = new frPrincipal(user);
		listaC = new frListaCarrera(user);
		listaE = new frListaEntrena(user);
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
//				detalleC = new frDetalleCarrera(variable);
//				detalleC.setVisible(true);
				break;
			case "frListaEntrena":
//				detalleE = new frDetalleEntrena(variable);
//				detalleE.setVisible(true);
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
	
	
	public void OpcionMenu (JFrame ventanaActual,int opcion)
	{
		switch (opcion)
		{
			case 1:
				lista.setVisible(true);
				break;
			case 2:
				carrera.setVisible(true);
				break;
			case 3:
				entrena.setVisible(true);
				break;
			case 4:
				perfil.setVisible(true);
				break;
		}
		ventanaActual.dispose();
	}
}
