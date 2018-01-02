package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import LD.BD;
import LN.clsEntrenamiento;
import LN.clsOpcEntrenamiento;
import LN.clsUsuario;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class frDetalleEntrena extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal, pSuperior, pDatos;
	private String codigo, fecha, duracion, calorias, nombre, nivel;
	private JLabel lblFecha, lblDuracion, lblCalorias, lblNombre, lblNivel,label,label_1,label_2,label_3;
	private JPanel pNombre,pOtros,pDuracion,pCentral, panel,panel_1;
	private JButton bVolver;

	public frDetalleEntrena(clsUsuario user, clsEntrenamiento entrenamiento) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		pPrincipal = new JPanel();
		pPrincipal.setBackground(Color.WHITE);
		pPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		pPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(pPrincipal);
		
		//DATOS
		
		fecha = entrenamiento.getFecha();
		
		String minutos = entrenamiento.getDuracion().split("\\.")[0];
		String segundos = entrenamiento.getDuracion().split("\\.")[1];
		int min = Integer.parseInt(minutos);
		int seg = Integer.parseInt(segundos);
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
		
		nombre = "Entrenamiento '"+opcEntrena.getNombre()+"'";
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
		
		label_1 = new JLabel(" ");
		pNombre.add(label_1);
		
		lblNombre = new JLabel(nombre);
		pNombre.add(lblNombre);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBackground(Color.BLACK);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		label = new JLabel(" ");
		pNombre.add(label);
		
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
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDuracion.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		pDuracion.add(panel_1);
		
		pOtros = new JPanel();
		pOtros.setBackground(Color.WHITE);
		pDatos.add(pOtros);
		pOtros.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblCalorias = new JLabel(calorias);
		lblCalorias.setBackground(Color.WHITE);
		lblCalorias.setHorizontalAlignment(SwingConstants.CENTER);
		pOtros.add(lblCalorias);
		lblCalorias.setIcon(new ImageIcon(frDetalleEntrena.class.getResource("/img/cal.png")));
		lblCalorias.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCalorias.setVerticalAlignment(SwingConstants.TOP);
		
		lblNivel = new JLabel(nivel);
		lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
		pOtros.add(lblNivel);
		lblNivel.setIcon(new ImageIcon(frDetalleEntrena.class.getResource("/img/nivel.png")));
		lblNivel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNivel.setVerticalAlignment(SwingConstants.TOP);
		
		pSuperior = new JPanel();
		pSuperior.setBackground(Color.WHITE);
		pPrincipal.add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		bVolver.setContentAreaFilled(false); // No rellenar el área
		bVolver.setBorderPainted(false);     // No pintar el borde
		bVolver.setBorder(null);    
		pSuperior.add(bVolver, BorderLayout.WEST);
		
		lblFecha = new JLabel(fecha);
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		pSuperior.add(lblFecha, BorderLayout.CENTER);
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		label_2 = new JLabel(" ");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 5));
		pSuperior.add(label_2, BorderLayout.SOUTH);
		
		label_3 = new JLabel("  ");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 5));
		pSuperior.add(label_3, BorderLayout.NORTH);
		
		setSize(375,667);
		setResizable(false);
		
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frListaEntrena ventana = new frListaEntrena (user);
				ventana.setVisible(true);
				dispose();
			}
		});
	}
}
