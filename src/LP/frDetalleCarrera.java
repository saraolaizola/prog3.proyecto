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

public class frDetalleCarrera extends JFrame 
{

	private JPanel pPrincipal;
	
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
		
		JLabel lblVolver = new JLabel("");
		lblVolver.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/back.png")));
		lblVolver.setHorizontalAlignment(SwingConstants.LEFT);
		pSuperior.add(lblVolver);
		
		JPanel pCentral = new JPanel();
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BorderLayout(0, 0));
		
		JPanel pFecha = new JPanel();
		pCentral.add(pFecha, BorderLayout.NORTH);
		
		JLabel lblFecha = new JLabel(carrera.getFecha());
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pFecha.add(lblFecha);
		
		JPanel pDatos = new JPanel();
		pCentral.add(pDatos, BorderLayout.CENTER);
		pDatos.setLayout(new BorderLayout(0, 0));
		
		JLabel lblDuracion = new JLabel(carrera.getDuracion());
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblDuracion, BorderLayout.SOUTH);
		
		JLabel lblKm = new JLabel(""+carrera.getKm());
		lblKm.setVerticalAlignment(SwingConstants.BOTTOM);
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblKm, BorderLayout.CENTER);
		
		JLabel lblCalorias = new JLabel(""+carrera.getCalorias());
		lblCalorias.setVerticalAlignment(SwingConstants.BOTTOM);
		pDatos.add(lblCalorias, BorderLayout.WEST);
		
		JLabel lblRitmo = new JLabel(carrera.getRitmo());
		lblRitmo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRitmo.setHorizontalAlignment(SwingConstants.CENTER);
		pDatos.add(lblRitmo, BorderLayout.EAST);
		
		setSize(375,667);
		setResizable(false);
		
		
		
		//PANTALLA CON LOS DATOS DE UNA CARRERA DE UN CORREDOR
	}

}
