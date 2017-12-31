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

import javax.swing.JButton;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		Double dato;
		
		fecha = carrera.getFecha();
		//poner la fecha en otro formato ej: 31 DIC 2017
		
		duracion = carrera.getDuracion().replace(".", ":");
		//si minutos menor de 10 añadirle un 0
		
		ritmo = carrera.getRitmo().replace(".", "'")+"''";
		//si segundos menor de 10 añadirle un 0
		
		dato=carrera.getKm();
		if (dato<1.0) km = "0"+df.format(carrera.getKm())+" km";
		else km = df.format(carrera.getKm())+" km";
		
		dato = carrera.getCalorias();
		if (dato<1.0) cal = "0"+df.format(carrera.getCalorias())+" cal"; 
		else cal = df.format(carrera.getCalorias())+" cal";
		
		JButton bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);            // Fondo Transparente (los grÃ¡ficos son png transparentes)
		bVolver.setContentAreaFilled(false); // No rellenar el Ã¡rea
		bVolver.setBorderPainted(false);     // No pintar el borde
		bVolver.setBorder(null);    
		pSuperior.add(bVolver, BorderLayout.WEST);
		
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
		
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frListaCarrera ventana = new frListaCarrera (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		
		
		//PANTALLA CON LOS DATOS DE UNA CARRERA DE UN CORREDOR
	}

}
