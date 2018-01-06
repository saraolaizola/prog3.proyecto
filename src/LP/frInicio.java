package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class frInicio extends JFrame
{
	JPanel pPrincipal;
	JLabel foto,lblInicia,lblUsuario,lblContrasenya,lblError;
	JButton bIniciar,bRegistrar;
	JTextField txtUsuario;
	JPasswordField passwordField;
	
	public frInicio () 
	{
		getContentPane().setBackground(Color.BLACK);
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		pPrincipal = new JPanel();
		pPrincipal.setBackground(Color.BLACK);
		getContentPane().add(pPrincipal, BorderLayout.CENTER);
		
		foto = new JLabel("");
		foto.setHorizontalAlignment(SwingConstants.CENTER);
		foto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		foto.setIcon(new ImageIcon(frInicio.class.getResource("/img/logo.png")));
		
		lblInicia = new JLabel ("RUN APP");
		lblInicia.setVerticalAlignment(SwingConstants.BOTTOM);
		lblInicia.setFont(new Font("Arial Black", Font.BOLD, 25));
		lblInicia.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicia.setForeground(Color.WHITE);
		
		lblUsuario = new JLabel ("Usuario:");
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsuario.setForeground(Color.BLACK);
		txtUsuario.setColumns(25);
		txtUsuario.setBorder(null);
		
		lblContrasenya = new JLabel ("Contrase\u00F1a:");
		lblContrasenya.setForeground(Color.WHITE);
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setForeground(Color.BLACK);
		passwordField.setColumns(25);
		passwordField.setBorder(null);
		
		lblError = new JLabel("*Usuario y/o contraseña incorrecta*");
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setVisible(false);
		lblError.setForeground(Color.red);
		
		bIniciar = new JButton( "Iniciar Sesi\u00F3n" );
		bIniciar.setForeground(SystemColor.text);
		bIniciar.setFont(new Font("Tahoma", Font.BOLD, 15));
		bIniciar.setBackground(SystemColor.desktop);
		bIniciar.setBorder(null);
		
		bRegistrar = new JButton( "Registrarte" );
		bRegistrar.setForeground(SystemColor.text);
		bRegistrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		bRegistrar.setBackground(Color.DARK_GRAY);
		bRegistrar.setBorder(null);
		
		getRootPane().setDefaultButton(bIniciar);
		bIniciar.requestFocus();
		
		GroupLayout gl_pPrincipal = new GroupLayout(pPrincipal);
		gl_pPrincipal.setHorizontalGroup(
			gl_pPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pPrincipal.createSequentialGroup()
					.addGroup(gl_pPrincipal.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInicia, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pPrincipal.createSequentialGroup()
							.addGap(70)
							.addComponent(lblError))
						.addGroup(gl_pPrincipal.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_pPrincipal.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblContrasenya, Alignment.LEADING)
								.addComponent(lblUsuario, Alignment.LEADING)
								.addComponent(passwordField, Alignment.LEADING)
								.addComponent(txtUsuario, Alignment.LEADING)
								.addComponent(bRegistrar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(bIniciar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(foto, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pPrincipal.setVerticalGroup(
			gl_pPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pPrincipal.createSequentialGroup()
					.addGap(78)
					.addComponent(foto)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInicia)
					.addGap(40)
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblContrasenya)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblError)
					.addGap(64)
					.addComponent(bIniciar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(bRegistrar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(161, Short.MAX_VALUE))
		);
		pPrincipal.setLayout(gl_pPrincipal);
		
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
		
		// Formato de ventana
		setSize(375,667);
		setResizable(false);
		
		setVisible(true);
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
					meterOpciones();
					
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
	
	public static void meterOpciones()
	{
		File f = new File ("C:\\Users\\ALUMNO\\workspace\\prog3.proyecto\\videos\\Abdominales.mp4");
		BD.registrarOpcEntrenamiento(f,"001", "Abdominales", "Principiante", 0.01,10);
		f = new File ("C:\\Users\\ALUMNO\\workspace\\prog3.proyecto\\videos\\Cardio.mp4");
		BD.registrarOpcEntrenamiento(f,"002", "Cardio quema grasas", "Intermedio", 0.02, 20);
		f = new File ("C:\\Users\\ALUMNO\\workspace\\prog3.proyecto\\videos\\Kickboxing.mp4");
		BD.registrarOpcEntrenamiento(f,"003", "Cardio Kick Boxing", "Experto", 0.03, 15);
	} 
	
}
