package LP;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LN.clsEntrenamiento;
import LN.clsUsuario;

public class frDetalleEntrena extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal;

	public frDetalleEntrena(clsUsuario user, clsEntrenamiento entrenamiento) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pPrincipal);
		
		setSize(375,667);
		
		
	}

}
