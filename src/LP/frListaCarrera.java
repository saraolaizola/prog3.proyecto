package LP;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import LD.BD;
import LN.clsCarrera;
import LN.clsUsuario;

import javax.swing.JRadioButton;

import java.awt.Color;

public class frListaCarrera extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pTabla,pSuperior,pDibujo,pBotones;
	private JSplitPane spDatos;
	private JLabel lblSelecciona;
	private JButton btnVer,bVolver;
	private JTable table;

	private ArrayList<String> fechas;
	private ArrayList<clsCarrera>lista;
	private clsUsuario usuario;
	
	private pDibujoRegistrosC Dibujo = new pDibujoRegistrosC(300,400);
	private ButtonGroup BG;
	private JRadioButton rdbtnDistancia, rdbtnRitmo,rdbtnDuracion, rdbtnCalorias;
	
	/**
	 * Create the frame.
	 */
	public frListaCarrera(clsUsuario user) 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		pPrincipal = new JPanel();
		pPrincipal.setBackground(Color.WHITE);
		pTabla = new JPanel();
		pTabla.setBackground(Color.WHITE);
		pDibujo = new JPanel();
		pDibujo.setBackground(Color.WHITE);
		spDatos = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		spDatos.setBackground(Color.WHITE);
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		pPrincipal.setLayout(new BorderLayout(0, 0));
	
		usuario = user;
		
		lblSelecciona = new JLabel("Seleccionar:");
		lblSelecciona.setBackground(Color.WHITE);
		lblSelecciona.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelecciona.setHorizontalAlignment(SwingConstants.CENTER);
		
		pPrincipal.add(lblSelecciona, BorderLayout.NORTH);
		
		String[] columnNames = {"Fecha","Duración"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		
		DefaultTableCellRenderer RendererDerecha = new DefaultTableCellRenderer();
		RendererDerecha.setHorizontalAlignment(JLabel.RIGHT);
		
		pPrincipal.add(spDatos, BorderLayout.CENTER);
		pTabla.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		pTabla.add(new JScrollPane(table));
		
		lista = BD.getMisCarreras(user.getUsuario());
		fechas = new ArrayList<String>();
		
		Object [] row = new Object [2];
		try
		{
			for (int i=0;i<lista.size();i++)
			{
				row[0]=lista.get(i).getFecha();
				row[1]=lista.get(i).getDuracion().replace(".", ":");
				model.addRow(row);
			}	
		}
		catch (NullPointerException e){
		}
		
		table.getColumnModel().getColumn(0).setCellRenderer(RendererDerecha);
		table.getColumnModel().getColumn(1).setCellRenderer(RendererDerecha);
		
		spDatos.setTopComponent(pTabla);
		
		btnVer = new JButton("Más información");
		pTabla.add(btnVer, BorderLayout.SOUTH);
		
		btnVer.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int row = table.getSelectedRow();
				String fecha = (String) table.getValueAt(row, 0);
				clsCarrera carrera = BD.getCarrera(fecha);
				
				frDetalleCarrera ventana = new frDetalleCarrera (user, carrera);
				ventana.setVisible(true);
				dispose();
			}
		});
		spDatos.setBottomComponent(pDibujo);
		pDibujo.setLayout(new BorderLayout(0, 0));
		
		pBotones = new JPanel();
		BG = new ButtonGroup();
		rdbtnDuracion = new JRadioButton("Duraci\u00F3n (min)");
		rdbtnDistancia = new JRadioButton("Distancia (km)");
		rdbtnRitmo = new JRadioButton("Ritmo");
		rdbtnCalorias = new JRadioButton("Calor\u00EDas");
		
		rdbtnCalorias.setSelected(true);
		pAtributo("cal");
		
		BG.add(rdbtnDuracion);
		BG.add(rdbtnDistancia);
		BG.add(rdbtnRitmo);
		BG.add(rdbtnCalorias);
		pBotones.add(rdbtnDuracion);
		pBotones.add(rdbtnDistancia);
		pBotones.add(rdbtnRitmo);
		pBotones.add(rdbtnCalorias);
		pDibujo.add(pBotones, BorderLayout.SOUTH);
		
		pSuperior = new JPanel();
		pSuperior.setBackground(Color.WHITE);
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);            // Fondo Transparente (los grÃ¡ficos son png transparentes)
		bVolver.setContentAreaFilled(false); // No rellenar el Ã¡rea
		bVolver.setBorderPainted(false);     // No pintar el borde
		pSuperior.add(bVolver, BorderLayout.WEST);
		
		setSize(375,667);
		setResizable(false);
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frLista ventana = new frLista (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		rdbtnDuracion.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    
		  }
		});
		rdbtnDistancia.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  pAtributo("km");
		  }
		});
		rdbtnCalorias.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  pAtributo("cal");
		  }
		});
		
		rdbtnRitmo.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    
		  }
		});
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frLista ventana = new frLista (user);
				ventana.setVisible(true);
				dispose();
			}
		});
	}
	
	private void pAtributo(String atributo)
	{	
		try
		{	
			for (int i=0;i<lista.size();i++)
			{
				fechas.add(lista.get(i).getFecha());
			}
			
			Dibujo.iniciarDibujo(lista.size(), fechas);
			
			DibujoAtributo(atributo);
			
			Dibujo.repaint();
		}
		catch(NullPointerException e)
		{
			System.out.println("No hay carreras");
		}
	}
	
	
	public void DibujoAtributo (String atributo)
	{
		switch (atributo)
		{
			case "cal":
				for (int i=0; i<lista.size(); i++) 
				{
					double cal = lista.get(i).getCalorias();
					String fecha = lista.get(i).getFecha();
					Dibujo.dibujarAtributo(1, i, fecha, (int)cal);
					Dibujo.repaint();
				}
				break;
				
			case "km":
				for (int i=0; i<lista.size(); i++) 
				{
					double km = lista.get(i).getKm();
					String fecha = lista.get(i).getFecha();
					Dibujo.dibujarAtributo(1, i, fecha, (int)km);
					Dibujo.repaint();
				}
				break;
		}
	}
}