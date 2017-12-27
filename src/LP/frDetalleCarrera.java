package LP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LN.clsCarrera;
import LN.clsUsuario;

public class frDetalleCarrera extends JFrame 
{

	private JPanel pPrincipal;
	
	public frDetalleCarrera(clsUsuario user, clsCarrera carrera) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pPrincipal);
		
		setSize(375,667);
		
		//PANTALLA CON LOS DATOS DE UNA CARRERA DE UN CORREDOR
	}

}
