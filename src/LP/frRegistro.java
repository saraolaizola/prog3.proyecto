package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import COMUN.clsUsuarioRepetido;
import LD.BD;

public class frRegistro extends JFrame 
{

	private JPanel pPrincipal;
	
	/**
	 * Create the frame.
	 */
	public frRegistro() 
	{
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
				
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		JPanel pCentral = new JPanel();
		
		JTextField txtUsuario = new JTextField();
		JPasswordField txtContrasenya = new JPasswordField();
		JTextField txtNombre = new JTextField();
		JTextField txtApellido = new JTextField();
		JComboBox comboPeso = new JComboBox();
		JComboBox comboAltura = new JComboBox();
		//...
		JComboBox comboSexo = new JComboBox();
		comboSexo.addItem("Femenino");
		comboSexo.addItem("Masculino");
		
		JLabel lblUsuario = new JLabel ("Usuario:");
		JLabel lblContrasenya = new JLabel ("Contraseña:");
		JLabel lblNombre = new JLabel ("Nombre:");
		JLabel lblApellido = new JLabel ("Apellido:");
		JLabel lblPeso = new JLabel ("Peso:");
		JLabel lblAltura = new JLabel ("Sexo:");
		
		JLabel lblSexo = new JLabel ("Contraseña:");
		
		JButton bAceptar = new JButton( "Aceptar" );
		JButton bVolver = new JButton( "Volver" );
		
			// Formato y layouts
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		
			// Añadido de componentes a contenedores
		add( pPrincipal, BorderLayout.CENTER );
		pCentral.add( lblUsuario );
		pCentral.add( txtUsuario );
		pCentral.add( lblContrasenya );
		pCentral.add( txtContrasenya );
		pCentral.add( lblNombre );
		pCentral.add( txtNombre );
		pCentral.add( lblApellido );
		//...
		add (pCentral, BorderLayout.CENTER);
				
		// Formato de ventana
		setSize( 750, 1334 );
				
		// Escuchadores de botones
		bAceptar.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String usuario = lblUsuario.getText();
					usuario = usuario.toUpperCase();
					String contrasenya = String.valueOf(txtContrasenya.getPassword());
					contrasenya = contrasenya.toUpperCase();
					String nombre = txtNombre.getText();
					nombre = nombre.toUpperCase();
					String apellido = txtApellido.getText();
					apellido = apellido.toUpperCase();
					double peso = Double.parseDouble((String) comboPeso.getSelectedItem()); 
					double altura = Double.parseDouble((String) comboAltura.getSelectedItem());
					String sexo = String.valueOf(comboSexo.getSelectedItem());
					
					BD.altaUsuario(nombre, apellido, usuario, contrasenya, peso, altura, sexo);
					
					frPrincipal ventana = new frPrincipal (usuario);
					ventana.setVisible(true);
					dispose();
				} 
				catch (clsUsuarioRepetido a)
				{
					//JOptionPane.showMessageDialog(this, a.getMessage());
				} 
				catch (ClassNotFoundException b) 
				{
					//b.printStackTrace();
				}
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
					frRegistro frame = new frRegistro();
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
