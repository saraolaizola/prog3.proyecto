package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import LN.clsEntrenamiento;
import LN.clsUsuario;

public class frDetalleEntrena extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal;
	private String codigo, fecha, duracion;
	private String calorias;

	public frDetalleEntrena(clsUsuario user, clsEntrenamiento entrenamiento) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pPrincipal);
		
		JPanel pSuperior = new JPanel();
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		fecha = entrenamiento.getFecha();
		duracion = entrenamiento.getDuracion().replace(".", ":");
//		duracion = duracion.charAt(0)+duracion.charAt(1)+":"+duracion.charAt(3)+duracion.charAt(4);
		calorias = Double.toString(entrenamiento.getCalorias());
		codigo = entrenamiento.getCodigo();
		
		JLabel lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/back.png")));
		lblVolver.setHorizontalAlignment(SwingConstants.LEFT);
		pSuperior.add(lblVolver);
		
		JPanel pCentral = new JPanel();
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel pFecha = new JPanel();
		pCentral.add(pFecha, BorderLayout.NORTH);
		
		JLabel lblFecha = new JLabel(fecha);
		lblFecha.setForeground(Color.BLUE);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pFecha.add(lblFecha);
		
		JPanel pDatos = new JPanel();
		pCentral.add(pDatos, BorderLayout.CENTER);
		pDatos.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDuracion = new JLabel(duracion);
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblDuracion, BorderLayout.NORTH);
		
		JLabel lblCalorias = new JLabel(calorias);
		lblCalorias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCalorias.setVerticalAlignment(SwingConstants.TOP);
		pDatos.add(lblCalorias, BorderLayout.WEST);
		
		
		setSize(375,667);
		setResizable(false);
		
		
	}

}
