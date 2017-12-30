package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import LD.BD;
import LN.clsUsuario;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class frInicio extends JFrame
{
	JPanel pPrincipal, pBotonera, pLogo, pDatos;
	JLabel foto;
	
	public frInicio () 
	{
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		JPanel pCentral = new JPanel();
		
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		getContentPane().add( pPrincipal);
		getContentPane().add (pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		
		pBotonera = new JPanel();
		pBotonera.setBackground(Color.BLACK);
		getContentPane().add(pBotonera, BorderLayout.SOUTH);
		pBotonera.setLayout(new FlowLayout());
		
		pLogo = new JPanel();
		pLogo.setBackground(Color.BLACK);
		getContentPane().add(pLogo, BorderLayout.NORTH);
		
		pDatos = new JPanel();
		pDatos.setBackground(Color.BLACK);
		pCentral.add(pDatos, BorderLayout.SOUTH);
		
		
		JTextField txtUsuario = new JTextField();
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setColumns(30);
		JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(30);
		
		JLabel lblInicia = new JLabel ("RUNTRAINING");
		lblInicia.setVerticalAlignment(SwingConstants.BOTTOM);
		lblInicia.setFont(new Font("Levenim MT", Font.PLAIN, 25));
		lblInicia.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicia.setForeground(new Color(0, 102, 255));
		JLabel lblUsuario = new JLabel ("Usuario:");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblContrasenya = new JLabel ("Contraseña:");
		lblContrasenya.setForeground(Color.WHITE);
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblError = new JLabel("*Usuario y/o contraseña incorrecta*");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setVisible(false);
		lblError.setForeground(Color.red);
		
		foto = new JLabel("");
		foto.setHorizontalAlignment(SwingConstants.CENTER);
		foto.setFont(new Font("Tahoma", Font.PLAIN, 25));
		foto.setIcon(new ImageIcon(frPerfil.class.getResource("/img/logo.png")));
		
		
		JButton bIniciar = new JButton( "Iniciar Sesi\u00F3n" );
		bIniciar.setForeground(SystemColor.text);
		bIniciar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bIniciar.setBackground(SystemColor.desktop);
		JButton bRegistrar = new JButton( "Registrarme" );
		bRegistrar.setForeground(SystemColor.text);
		bRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bRegistrar.setBackground(Color.DARK_GRAY);
		pLogo.setLayout(new BorderLayout(0, 0));
	
		// Añadido de componentes a contenedores
		
		pLogo.add( lblInicia, BorderLayout.CENTER );
		pLogo.add(foto, BorderLayout.SOUTH);
		
		JLabel label = new JLabel("   ");
		pLogo.add(label, BorderLayout.NORTH);
		pDatos.add( lblUsuario );
		pDatos.add( txtUsuario );
		pDatos.add( lblContrasenya );
		pDatos.add( passwordField );
		pDatos.add(lblError);
		pBotonera.add( bIniciar );
		pBotonera.add( bRegistrar );
		
		// Formato de ventana
		setSize(375,667);
		setResizable(false);
		
		setVisible(true);
		
		// Escuchadores de botones
		bIniciar.addActionListener( new ActionListener() 
		{
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
						lblError.setVisible(false);
						clsUsuario user = BD.getUser(usuario);
						frPrincipal ventana = new frPrincipal (user);
						ventana.setVisible(true);
						dispose();
					}
					else
					{
						lblError.setVisible(true);
						txtUsuario.setText("");
						passwordField.setText("");
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
					BD.meterOpciones();
					
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
