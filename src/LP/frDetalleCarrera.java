package LP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Date;
import java.text.DecimalFormat;

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
import java.awt.SystemColor;
import java.awt.GridLayout;

public class frDetalleCarrera extends JFrame 
{

	private JPanel pPrincipal;
	private String fecha, duracion, ritmo, km,cal;
	
	public frDetalleCarrera(clsUsuario user, clsCarrera carrera) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBackground(Color.WHITE);
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pPrincipal);
		
		JPanel pSuperior = new JPanel();
		pSuperior.setBackground(Color.WHITE);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		DecimalFormat df = new DecimalFormat("#.0");
		
		String texto;
		
		fecha = carrera.getFecha();
		
		duracion = carrera.getDuracion().replace(".", ":");
		
		ritmo = carrera.getRitmo().replace(".", "'");
		
		km = df.format(carrera.getKm());
		
		cal =df.format(carrera.getCalorias());
		
		
		JLabel lblVolver = new JLabel("");
		lblVolver.setBackground(Color.WHITE);
		lblVolver.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/back.png")));
		lblVolver.setHorizontalAlignment(SwingConstants.LEFT);
		pSuperior.add(lblVolver, BorderLayout.WEST);
		
		JLabel lblFecha = new JLabel(fecha);
		pSuperior.add(lblFecha, BorderLayout.CENTER);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(new Color(0, 0, 0));
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JPanel pCentral = new JPanel();
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel pFecha = new JPanel();
		pFecha.setBackground(Color.WHITE);
		pCentral.add(pFecha, BorderLayout.NORTH);
		pFecha.setLayout(new BorderLayout(0, 0));
		
		JLabel lblRecorrido = new JLabel("");
		lblRecorrido.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/recorrido.png")));
		pFecha.add(lblRecorrido, BorderLayout.SOUTH);
		
		JPanel pDatos = new JPanel();
		pDatos.setBackground(Color.WHITE);
		pCentral.add(pDatos, BorderLayout.CENTER);
		pDatos.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel label = new JLabel("");
		pDatos.add(label);
		
		JLabel lblDuracion = new JLabel(duracion);
		lblDuracion.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/time.png")));
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblDuracion);
		
		JLabel label_1 = new JLabel("");
		pDatos.add(label_1);
		
		JLabel lblCalorias = new JLabel(cal);
		lblCalorias.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/cal.png")));
		lblCalorias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCalorias.setVerticalAlignment(SwingConstants.TOP);
		pDatos.add(lblCalorias);
		
		JLabel lblKm = new JLabel(km);
		lblKm.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/distance.png")));
		lblKm.setBackground(Color.WHITE);
		lblKm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblKm.setVerticalAlignment(SwingConstants.TOP);
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblKm);
		
		JLabel lblRitmo = new JLabel(ritmo);
		lblRitmo.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/ritmo.png")));
		lblRitmo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRitmo.setVerticalAlignment(SwingConstants.TOP);
		lblRitmo.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblRitmo);
		
		setSize(375,667);
		setResizable(false);
		
		
		
		//PANTALLA CON LOS DATOS DE UNA CARRERA DE UN CORREDOR
	}

}
