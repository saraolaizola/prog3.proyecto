package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;

import COMUN.clsUsuarioRepetido;
import LD.BD;
import LN.clsUsuario;

import javax.swing.BoxLayout;

public class frRegistro extends JFrame 
{
	private static final long serialVersionUID = 1L;

	private JPanel pPrincipal, pSuperior;
	private JPanel pCentral;
	private JPanel pInferior;
	
	private JButton bAceptar, bVolver;
	private JLabel lblPeso, lblAltura, lblSexo, lblContrasenya, lblNombre, lblApellido, lblUsuario;
	private JComboBox  comboSexo;
	private JSpinner spinPeso, spinAltura;
	private JTextField txtUsuario, txtNombre, txtContrasenya, txtApellido;  
	private SpinnerListModel slm1, slm2;
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
		pSuperior= new JPanel();
		
		// Añadido de componentes a contenedores
		getContentPane().add( pPrincipal );
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		getContentPane().add (pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BoxLayout(pCentral, BoxLayout.Y_AXIS));
		getContentPane().add(pInferior, BorderLayout.SOUTH);
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		
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
		
		lblPeso = new JLabel ("Peso:");	
		spinPeso = new JSpinner();
		String[] pesos = {"45","46","47","48","49","50","51","52","55","56","57","59","60",
						  "61","62","63","64","65","66","67","68","69", "70","71","72","73",
						  "74","75","76","77","78","79","80","81","82","83","84","85","86",
						  "87","88","89","90","91","92","93","94","95","96","97","98","99 o más"};
		slm1 = new SpinnerListModel(pesos);
		spinPeso = new JSpinner(slm1);
		spinPeso.setValue("65");
		
		lblAltura = new JLabel ("Altura:");
		spinAltura = new JSpinner();
		String[] alturas = {"145","146","147","148","149","150","151","152","155","156","157","159","160",
							"161","162","163","164","165","166","167","168","169","170","171","172","173",
							"174","175","176","177","178","179","180","181","182","183","184","185","186",
							"187","188","189","190","191","192","193","194","195","196","197","198","199 o más"};
		slm2= new SpinnerListModel(alturas);
		spinAltura = new JSpinner(slm2);
		spinAltura.setValue("170");
		
		lblSexo = new JLabel ("Sexo:");
		comboSexo = new JComboBox();
		comboSexo.addItem("Femenino");
		comboSexo.addItem("Masculino");
		
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
		pCentral.add(spinAltura);
		pCentral.add(lblPeso);
		pCentral.add(spinPeso);
		
		pInferior.add(bAceptar);
		pInferior.add(bVolver);
		
		// Formato de ventana
		setSize(375,667);
		setResizable(false);
				
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
					double peso = Double.parseDouble((String) spinPeso.getValue()); 
					double altura = Double.parseDouble((String) spinAltura.getValue());
					String sexo = String.valueOf(comboSexo.getSelectedItem());
					
					BD.altaUsuario(usuario,contrasenya,nombre,apellido,peso,altura,sexo);
					
					clsUsuario user = BD.getUser(usuario);
					
					frPrincipal ventana = new frPrincipal (user);
					ventana.setVisible(true);
					dispose();
				} 
				catch (clsUsuarioRepetido a)
				{
					errorUsuario(a);
				}
				catch (Exception b)
				{
					error(b);
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
	
	protected void errorUsuario(clsUsuarioRepetido a)
	{
		JOptionPane.showMessageDialog(this, a.getMessage());
	}
	
	protected void error(Exception b)
	{
		JOptionPane.showMessageDialog(this, b.getStackTrace());
	}
}
