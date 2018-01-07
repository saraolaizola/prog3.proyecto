package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import COMUN.clsSinActividad;
import LD.BD;
import LN.clsEntrenamiento;
import LN.clsOpcEntrenamiento;
import LN.clsUsuario;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;

import javax.swing.border.EmptyBorder;

public class frDetalleEntrena extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal, pSuperior, pDatos;
	private String codigo, duracion, calorias, nivel;
	private JLabel lblFecha, lblDuracion, lblCalorias, lblNombre, lblNivel,lblEntrenamiento;
	private JPanel pNombre,pOtros,pDuracion,pCentral;
	private JButton bVolver;
	private JPanel panel;
	private JButton bBorrar;

	public frDetalleEntrena(clsUsuario user, clsEntrenamiento entrenamiento) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBackground(Color.WHITE);
		pPrincipal.setBorder(null);
		pPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pPrincipal);
		
		String minutos = entrenamiento.getDuracion().split("\\.")[0];
		int min = Integer.parseInt(minutos);
		String segundos;
		int seg;
		try
		{
			segundos = entrenamiento.getDuracion().split("\\.")[1];
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
		
		DecimalFormat df = new DecimalFormat("#.0");
		double cal = entrenamiento.getCalorias();
		if (cal<1) calorias = "0"+df.format(cal);
		else calorias = df.format(cal)+" cal";
		
		codigo = entrenamiento.getCodigo();
		clsOpcEntrenamiento opcEntrena = BD.getEntrena(codigo);

		nivel = opcEntrena.getNivel();
		
		//COMPONENTES
		
		pCentral = new JPanel();
		pCentral.setBackground(Color.WHITE);
		pPrincipal.add(pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BorderLayout(0, 0));
		
		pNombre = new JPanel();
		pCentral.add(pNombre, BorderLayout.CENTER);
		pNombre.setBackground(Color.BLACK);
		pNombre.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblEntrenamiento = new JLabel("Entrenamiento");
		lblEntrenamiento.setVerticalAlignment(SwingConstants.BOTTOM);
		lblEntrenamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenamiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEntrenamiento.setForeground(Color.WHITE);
		pNombre.add(lblEntrenamiento);
		
		lblNombre = new JLabel(opcEntrena.getNombre());
		lblNombre.setVerticalAlignment(SwingConstants.TOP);
		pNombre.add(lblNombre);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBackground(Color.BLACK);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		pDatos = new JPanel();
		pCentral.add(pDatos, BorderLayout.SOUTH);
		pDatos.setBackground(Color.WHITE);
		pDatos.setLayout(new GridLayout(0, 1, 0, 0));
		
		pDuracion = new JPanel();
		pDuracion.setBackground(Color.WHITE);
		pDatos.add(pDuracion);
		pDuracion.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		pDuracion.add(panel);
		
		lblDuracion = new JLabel(duracion);
		lblDuracion.setBackground(Color.WHITE);
		pDuracion.add(lblDuracion);
		lblDuracion.setIcon(new ImageIcon(frDetalleEntrena.class.getResource("/img/time.png")));
		lblDuracion.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblDuracion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		
		pOtros = new JPanel();
		pOtros.setBackground(Color.WHITE);
		pDatos.add(pOtros);
		pOtros.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblCalorias = new JLabel(calorias);
		lblCalorias.setBackground(Color.WHITE);
		lblCalorias.setHorizontalAlignment(SwingConstants.CENTER);
		pOtros.add(lblCalorias);
		lblCalorias.setIcon(new ImageIcon(frDetalleEntrena.class.getResource("/img/cal.png")));
		lblCalorias.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCalorias.setHorizontalTextPosition(SwingConstants.CENTER);
		lblCalorias.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCalorias.setVerticalAlignment(SwingConstants.TOP);
		
		lblNivel = new JLabel(nivel);
		lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
		pOtros.add(lblNivel);
		lblNivel.setIcon(new ImageIcon(frDetalleEntrena.class.getResource("/img/nivel.png")));
		lblNivel.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblNivel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNivel.setVerticalAlignment(SwingConstants.TOP);
		
		pSuperior = new JPanel();
		pSuperior.setBorder(new EmptyBorder(5, 5, 5, 5));
		pSuperior.setBackground(SystemColor.menu);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);            
		bVolver.setContentAreaFilled(false); 
		bVolver.setBorderPainted(false);     
		bVolver.setBorder(null);    
		pSuperior.add(bVolver, BorderLayout.WEST);
		
		lblFecha = new JLabel(entrenamiento.getFecha());
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		pSuperior.add(lblFecha, BorderLayout.CENTER);
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		bBorrar = new JButton("");
		bBorrar.setIcon(new ImageIcon(frDetalleEntrena.class.getResource("/img/basura.png")));
		bBorrar.setOpaque(false);            
		bBorrar.setContentAreaFilled(false);
		bBorrar.setBorderPainted(false);     
		bBorrar.setBorder(null);
		pSuperior.add(bBorrar, BorderLayout.EAST);
		
		setSize(375,667);
		setResizable(false);
		
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frListaEntrena ventana;
				try 
				{
					ventana = new frListaEntrena (user);
					ventana.setVisible(true);
					dispose();
				} 
				catch (clsSinActividad e1){
				}
				
			}
		});
		bBorrar.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frListaEntrena ventana;
				try
				{
					BD.borrarEntrena(entrenamiento.getFecha());
					ventana = new frListaEntrena (user);
					ventana.setVisible(true);
					dispose();
				}
				catch (clsSinActividad a){
				}
			}
		});
	}
}
