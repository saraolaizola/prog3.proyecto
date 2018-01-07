package LP;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import COMUN.clsSinActividad;
import LD.BD;
import LN.clsCarrera;
import LN.clsUsuario;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.border.EmptyBorder;

public class frDetalleCarrera extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pSuperior,pCentral,pFoto,pCalorias,pDatos,pRitmo,pDuracion,pDistancia;
	private JLabel lblCal,lCal,lblRitmo,lRitmo,lblDuracion,lDuracion,lblKm,lKm,lblFecha,lblRecorrido;
	private JButton bVolver,bBorrar;
	private String fecha, duracion, ritmo, km,cal,minutos,segundos;
	private int min,seg;

	public frDetalleCarrera(clsUsuario user, clsCarrera carrera) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.black);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBackground(SystemColor.menu);
		pPrincipal.setBorder(null);
		pPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pPrincipal);
		
		pSuperior = new JPanel();
		pSuperior.setBorder(new EmptyBorder(5, 5, 5, 5));
		pSuperior.setBackground(SystemColor.menu);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		fecha = carrera.getFecha();
		
		minutos = carrera.getDuracion().split("\\.")[0];
		min = Integer.parseInt(minutos);
		try
		{
			segundos = carrera.getDuracion().split("\\.")[1];
			seg = Integer.parseInt(segundos);
		}
		catch(ArrayIndexOutOfBoundsException e){
			seg=0;
		}
		if (min<10) minutos = "0"+min;
		else minutos = ""+min;
		if (seg<10) segundos = "0"+seg;
		else segundos = ""+seg;
		duracion = minutos+":"+segundos;

		minutos = carrera.getRitmo().split("\\.")[0];
		try
		{
			segundos = carrera.getRitmo().split("\\.")[1];
			seg = Integer.parseInt(segundos);
		}
		catch(ArrayIndexOutOfBoundsException e){
			seg=0;
		}
		if (seg<10) segundos = "0"+seg;
		else segundos = ""+seg;
		ritmo = minutos+"'"+segundos+"''";
		
		DecimalFormat df = new DecimalFormat("#.##");
		carrera.getKm();
		km = df.format(carrera.getKm());
		
		carrera.getCalorias();
		cal = df.format(carrera.getCalorias());
		
		bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);            
		bVolver.setContentAreaFilled(false); 
		bVolver.setBorderPainted(false);     
		bVolver.setBorder(null);    
		pSuperior.add(bVolver, BorderLayout.WEST);
		
		lblFecha = new JLabel(fecha);
		pSuperior.add(lblFecha, BorderLayout.CENTER);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(new Color(0, 0, 0));
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		bBorrar = new JButton("");
		bBorrar.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/basura.png")));
		bBorrar.setOpaque(false);            
		bBorrar.setContentAreaFilled(false); 
		bBorrar.setBorderPainted(false);     
		bBorrar.setBorder(null);    
		pSuperior.add(bBorrar, BorderLayout.EAST);
		
		pCentral = new JPanel();
		pCentral.setBackground(Color.WHITE);
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BorderLayout(0, 0));
		
		pFoto = new JPanel();
		pFoto.setBackground(Color.WHITE);
		pCentral.add(pFoto, BorderLayout.NORTH);
		pFoto.setLayout(new BorderLayout(0, 0));
		
		lblRecorrido = new JLabel("");
		lblRecorrido.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/carrera.jpg")));
		pFoto.add(lblRecorrido, BorderLayout.SOUTH);
		
		pDatos = new JPanel();
		pDatos.setBackground(Color.WHITE);
		pCentral.add(pDatos, BorderLayout.SOUTH);
		pDatos.setLayout(new GridLayout(0, 3, 0, 0));
		
		pCalorias = new JPanel();
		pCalorias.setBackground(Color.WHITE);
		pDatos.add(pCalorias);
		pCalorias.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblCal = new JLabel("Cal");
		lblCal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCal.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/cal.png")));
		lblCal.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCal.setHorizontalTextPosition(SwingConstants.CENTER);
		pCalorias.add(lblCal);
		
		lCal = new JLabel(cal);
		lCal.setVerticalAlignment(SwingConstants.TOP);
		pCalorias.add(lCal);
		lCal.setHorizontalAlignment(SwingConstants.CENTER);
		lCal.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		pDistancia = new JPanel();
		pDistancia.setBackground(Color.WHITE);
		pDatos.add(pDistancia);
		pDistancia.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblKm = new JLabel("Km");
		lblKm.setVerticalAlignment(SwingConstants.BOTTOM);
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		lblKm.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/distance.png")));
		lblKm.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblKm.setHorizontalTextPosition(SwingConstants.CENTER);
		pDistancia.add(lblKm);
		
		lKm = new JLabel(km);
		lKm.setVerticalAlignment(SwingConstants.TOP);
		pDistancia.add(lKm);
		lKm.setBackground(Color.WHITE);
		lKm.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lKm.setHorizontalAlignment(SwingConstants.CENTER);
		
		pRitmo = new JPanel();
		pRitmo.setBackground(Color.WHITE);
		pDatos.add(pRitmo);
		pRitmo.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblRitmo = new JLabel("Ritmo");
		lblRitmo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRitmo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRitmo.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/ritmo.png")));
		lblRitmo.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblRitmo.setHorizontalTextPosition(SwingConstants.CENTER);
		pRitmo.add(lblRitmo);
		
		lRitmo = new JLabel(ritmo);
		lRitmo.setVerticalAlignment(SwingConstants.TOP);
		pRitmo.add(lRitmo);
		lRitmo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lRitmo.setHorizontalAlignment(SwingConstants.CENTER);
		
		pDuracion = new JPanel();
		pCentral.add(pDuracion, BorderLayout.CENTER);
		pDuracion.setBackground(Color.WHITE);
		pDuracion.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblDuracion = new JLabel("Duraci\u00F3n");
		lblDuracion.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		lblDuracion.setIcon(new ImageIcon(frDetalleCarrera.class.getResource("/img/time.png")));
		lblDuracion.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblDuracion.setHorizontalTextPosition(SwingConstants.CENTER);
		pDuracion.add(lblDuracion);
		
		lDuracion = new JLabel(duracion);
		lDuracion.setVerticalAlignment(SwingConstants.TOP);
		pDuracion.add(lDuracion);
		lDuracion.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		
		setSize(375,667);
		setResizable(false);
		
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					frListaCarrera ventana = new frListaCarrera (user);
					ventana.setVisible(true);
					dispose();
				}
				catch (clsSinActividad a){
				}
			}
		});
		bBorrar.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					BD.borrarCarrera(carrera.getFecha());
					frListaCarrera ventana = new frListaCarrera (user);
					ventana.setVisible(true);
					dispose();
				}
				catch (clsSinActividad a){
				}
			}
		});
	}
}
