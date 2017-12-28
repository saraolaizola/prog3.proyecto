package LP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import LN.clsCarrera;
import LN.clsUsuario;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import java.awt.Color;

public class frDetalleCarrera extends JFrame 
{

	private JPanel pPrincipal;
	private String fecha, duracion, ritmo, km,cal;
	
	public frDetalleCarrera(clsUsuario user, clsCarrera carrera) 
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
		
		fecha = carrera.getFecha();
		duracion = carrera.getDuracion();
//		duracion = duracion.charAt(0)+duracion.charAt(1)+":"+duracion.charAt(3)+duracion.charAt(4);
		ritmo = carrera.getRitmo();
//		ritmo = ritmo.charAt(0)+"'"+ritmo.charAt(2)+ritmo.charAt(3)+"''";
		km = ""+carrera.getKm();
		cal =""+carrera.getCalorias();
		
		
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
		
		JLabel lblKm = new JLabel(km);
		lblKm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKm.setVerticalAlignment(SwingConstants.TOP);
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblKm, BorderLayout.CENTER);
		
		JLabel lblCalorias = new JLabel(cal);
		lblCalorias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCalorias.setVerticalAlignment(SwingConstants.TOP);
		pDatos.add(lblCalorias, BorderLayout.WEST);
		
		JLabel lblRitmo = new JLabel(ritmo);
		lblRitmo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRitmo.setVerticalAlignment(SwingConstants.TOP);
		lblRitmo.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblRitmo, BorderLayout.EAST);
		
		setSize(375,667);
		setResizable(false);
		
		
		
		//PANTALLA CON LOS DATOS DE UNA CARRERA DE UN CORREDOR
	}

}
