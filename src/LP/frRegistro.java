package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import COMUN.clsUsuarioRepetido;
import LD.BD;
import LN.clsUsuario;

import javax.swing.BoxLayout;

public class frRegistro extends JFrame 
{

	private JPanel pPrincipal;
	private JPanel pCentral;
	private JPanel pInferior;
	
	private JButton bAceptar, bVolver;
	private JLabel lblPeso, lblAltura, lblSexo, lblContrasenya, lblNombre, lblApellido, lblUsuario;
	private JComboBox comboPeso, comboAltura, comboSexo;
	private JTextField txtUsuario, txtNombre, txtContrasenya, txtApellido;  
	private DefaultComboBoxModel dmc1, dmc2;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	
	
	/**
	 * Create the frame.
	 */
	public frRegistro() 
	{
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		pCentral = new JPanel();
		pInferior = new JPanel();
		
		// Formato y layouts
		
		// Añadido de componentes a contenedores
		getContentPane().add( pPrincipal );
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		getContentPane().add (pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		
		bAceptar = new JButton( "Aceptar" );
		bVolver = new JButton( "Volver" );
		
		lblUsuario = new JLabel ("Usuario:");
		txtUsuario = new JTextField();
		lblContrasenya = new JLabel ("Contraseña:");
		txtContrasenya = new JPasswordField();
		lblNombre = new JLabel ("Nombre:");
		txtNombre = new JTextField();
		lblApellido = new JLabel ("Apellido:");
		txtApellido = new JTextField();
		
		lblSexo = new JLabel ("Sexo:");	
		comboPeso = new JComboBox();
		String[] pesos = {"45","46","47","48","49","50","51","52","55","56","57","59","60"};
		dmc1 = new DefaultComboBoxModel(pesos);
		comboPeso = new JComboBox(dmc1);
		
		lblAltura = new JLabel ("Altura:");
		comboAltura = new JComboBox();
		String[] alturas = {"145","146","147","148","149","150","151","152","155","156","157","159","160"};
		dmc2= new DefaultComboBoxModel(alturas);
		comboAltura = new JComboBox(dmc2);
		
		lblSexo = new JLabel ("Sexo:");
		comboSexo = new JComboBox();
		comboSexo.addItem("Femenino");
		comboSexo.addItem("Masculino");
		
		lblPeso = new JLabel ("Peso:");
		lblAltura = new JLabel ("Sexo:");
		lblSexo = new JLabel ("Contraseña:");
		
		pCentral.add(lblUsuario);
		pCentral.add(txtUsuario);
		pCentral.add(lblContrasenya);
		pCentral.add(txtContrasenya);
		pCentral.add(lblNombre);
		pCentral.add(txtNombre);
		pCentral.add(lblApellido);
		pCentral.add(txtApellido);
		pCentral.add(lblSexo);
		pCentral.add(comboSexo);
		pCentral.add(lblAltura);
		pCentral.add(comboAltura);
		pCentral.add(lblPeso);
		pCentral.add(comboPeso);
		
		pInferior.add(bAceptar);
		pInferior.add(bVolver);
		
		// Formato de ventana
		setSize(375,667);
				
		// Escuchadores de botones
		bAceptar.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					String usuario = txtUsuario.getText();
					usuario = usuario.toUpperCase();
					String contrasenya = String.valueOf(((JPasswordField) txtContrasenya).getPassword());
					contrasenya = contrasenya.toUpperCase();
					String nombre = txtNombre.getText();
					nombre = nombre.toUpperCase();
					String apellido = txtApellido.getText();
					apellido = apellido.toUpperCase();
					double peso = Double.parseDouble((String) comboPeso.getSelectedItem()); 
					double altura = Double.parseDouble((String) comboAltura.getSelectedItem());
					String sexo = String.valueOf(comboSexo.getSelectedItem());
					
					BD.altaUsuario(usuario,contrasenya,nombre,apellido,peso,altura,sexo);
					
					clsUsuario user = BD.getUser(usuario);
					
					frPrincipal ventana = new frPrincipal (user);
					ventana.setVisible(true);
					dispose();
				} 
				catch (clsUsuarioRepetido a)
				{
					logger.log( Level.SEVERE, a.getMessage());
					
				} 
				catch (ClassNotFoundException b) 
				{
					logger.log( Level.SEVERE, "Class Nout Found Exception" );
				}
				catch (NullPointerException c)
				{
					logger.log( Level.SEVERE, "Null Pointer Exception" );
					
				}
			}
		});
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frInicio ventana = new frInicio ();
				ventana.setVisible(true);
				dispose();
			}
		});
	}
}
