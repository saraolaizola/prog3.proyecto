package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import LD.BD;

public class frInicio extends JFrame
{
	JPanel pPrincipal;
	
	public frInicio () 
	{
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		JPanel pCentral = new JPanel();
		
		JTextField txtUsuario = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		
		JLabel lblInicia = new JLabel ("RUN APP");
		JLabel lblUsuario = new JLabel ("Usuario:");
		JLabel lblContrasenya = new JLabel ("Contraseña:");
		
		JButton bIniciar = new JButton( "Iniciar sesión" );
		JButton bRegistrar = new JButton( "Registrarme" );
		
		// Formato y layouts
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		
		// Añadido de componentes a contenedores
		
		add( pPrincipal, BorderLayout.CENTER );
		pCentral.add( lblInicia );
		pCentral.add( lblUsuario );
		pCentral.add( txtUsuario );
		pCentral.add( lblContrasenya );
		pCentral.add( passwordField );
		pCentral.add( bIniciar );
		pCentral.add( bRegistrar );
		add (pCentral, BorderLayout.CENTER);
		
		// Formato de ventana
		setSize( 750, 1334 );
		
		// Escuchadores de botones
		bIniciar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String usuario = txtUsuario.getText();
				usuario = usuario.toUpperCase();
				String contrasenya = String.valueOf(passwordField.getPassword());
				contrasenya = contrasenya.toUpperCase();
				try 
				{
					if (BD.inicioSesion(usuario, contrasenya))
					{
						frPrincipal ventana = new frPrincipal (usuario);
						ventana.setVisible(true);
						dispose();
					}
					else
					{
						//JOptionPane ERROR
					}
				} 
				catch (ClassNotFoundException e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		bRegistrar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frRegistro ventana = new frRegistro();
				ventana.setVisible(true);
				dispose();
			}
		});
	}
	
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					BD.initBD("RUNAPP");
					BD.crearTablaCarrera();
					BD.crearTablaEntrenamiento();
					BD.crearTablaOpcEntrenamiento();
					BD.crearTablaUsuarios();
					
					frInicio frame = new frInicio ();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
}
