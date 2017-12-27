package LP;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LN.clsOpcEntrenamiento;
import LN.clsUsuario;


public class frEntrena extends JFrame 
{

	private JPanel pPrincipal;


	public frEntrena(clsOpcEntrenamiento entrena, clsUsuario user) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pPrincipal);
		
		setSize(375,667);
		setResizable(false);
		
		
		//incluir el reloj de pantalla correr cuando funcione
		
	}

}
