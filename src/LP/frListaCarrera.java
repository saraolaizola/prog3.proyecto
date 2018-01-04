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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import java.awt.SystemColor;

public class frListaCarrera extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pTabla,pSuperior,pDibujo,pBotones;
	private JSplitPane spDatos;
	private JButton btnVer,bVolver;
	private JTable table;

	private ArrayList<String> fechas;
	private ArrayList<clsCarrera>lista;
	private clsUsuario usuario;
	private ChartPanel dibujo;
	
	
	//NO FUNCIONA EL DIBUJO
	private pDibujoRegistrosC Dibujo = new pDibujoRegistrosC(300,400);
	
	private ButtonGroup BG;
	private JRadioButton rdbtnDistancia, rdbtnRitmo,rdbtnDuracion, rdbtnCalorias;
	private JLabel lblCarreras;
	private JPanel panel,panel_1;

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
		
		String[] columnNames = {"Fecha","Duración"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}	
		};
		
		DefaultTableCellRenderer RendererCentro = new DefaultTableCellRenderer();
		RendererCentro.setHorizontalAlignment(JLabel.CENTER);
		
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
				
				String minutos = lista.get(i).getDuracion().split("\\.")[0];
				String segundos = lista.get(i).getDuracion().split("\\.")[1];
				int min = Integer.parseInt(minutos);
				int seg = Integer.parseInt(segundos);
				
				if (min<10) minutos = "0"+min;
				else minutos = ""+min;
				if (seg<10) segundos = "0"+seg;
				else segundos = ""+seg;
				
				row[1]=minutos+":"+segundos;
				
				model.addRow(row);
			}	
		}
		catch (NullPointerException e){
		}
		
		table.getColumnModel().getColumn(0).setCellRenderer(RendererCentro);
		table.getColumnModel().getColumn(1).setCellRenderer(RendererCentro);
		
		btnVer = new JButton("Más información");
		pTabla.add(btnVer, BorderLayout.SOUTH);

		pDibujo.setLayout(new BorderLayout(0, 0));
		
		pBotones = new JPanel();
		BG = new ButtonGroup();
		rdbtnDuracion = new JRadioButton("Duraci\u00F3n (min)");
		rdbtnDistancia = new JRadioButton("Distancia (km)");
		rdbtnRitmo = new JRadioButton("Ritmo");
		rdbtnCalorias = new JRadioButton("Calor\u00EDas");
		
		rdbtnDistancia.setSelected(true);
		dibujo = pintarKm();
		pDibujo.add(dibujo,BorderLayout.CENTER);
		
		BG.add(rdbtnDuracion);
		BG.add(rdbtnDistancia);
		BG.add(rdbtnRitmo);
		BG.add(rdbtnCalorias);
		pBotones.add(rdbtnDuracion);
		pBotones.add(rdbtnDistancia);
		pBotones.add(rdbtnRitmo);
		pBotones.add(rdbtnCalorias);
		pDibujo.add(pBotones, BorderLayout.SOUTH);
		
		spDatos.setTopComponent(pDibujo);
		spDatos.setBottomComponent(pTabla);
		
		pSuperior = new JPanel();
		pSuperior.setBackground(Color.WHITE);
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);            
		bVolver.setContentAreaFilled(false);
		bVolver.setBorderPainted(false);     // No pintar el borde
		bVolver.setBorder(null); 
		pSuperior.add(bVolver, BorderLayout.WEST);
		
		lblCarreras = new JLabel("Carreras      ");
		lblCarreras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarreras.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pSuperior.add(lblCarreras, BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		pSuperior.add(panel, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		pSuperior.add(panel_1, BorderLayout.SOUTH);
	
		spDatos.setResizeWeight(.5d);
		
		setSize(375,667);
		setResizable(false);
		
		//Intentar que al clicar ya diriga a detalle carrera sin el botón más información
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
			 //PROBLEMA: no se redibuja
			  dibujo=pintarMin();
			  dibujo.repaint();
		  }
		});
		rdbtnDistancia.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  dibujo=pintarKm();
			  dibujo.repaint();
		  }
		});
		rdbtnCalorias.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  dibujo=pintarCal();
			  dibujo.repaint();
		  }
		});
		
		rdbtnRitmo.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  dibujo=pintarRitmo();
			  dibujo.repaint();
		  }
		});
	}
	public ChartPanel pintarKm()
	{
		XYSeries series = new XYSeries("Distancia");
		for (int i=0; i<lista.size(); i++)
		{
			series.add(i+1, lista.get(i).getKm());
		}
				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		        
		JFreeChart chart = ChartFactory.createXYLineChart("Distancia","Nºcarrera","Km",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		
		ChartPanel dibujo = new ChartPanel(chart);
//		pDibujo.add(dibujo);
//		pDibujo.repaint();
		
		return dibujo;
	}
	public ChartPanel pintarMin()
	{
		XYSeries series = new XYSeries("Duración");
		for (int i=0; i<lista.size(); i++)
		{
			String minutos = lista.get(i).getDuracion().split("\\.")[0];
			String segundos = lista.get(i).getDuracion().split("\\.")[1];
			int min = Integer.parseInt(minutos);
			int seg = Integer.parseInt(segundos);
			double duracion = min + ((seg*100)/60);
			series.add(i+1, duracion);
		}
				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		        
		JFreeChart chart = ChartFactory.createXYLineChart("Duración","Nºcarrera","Min",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		
		ChartPanel dibujo = new ChartPanel(chart);
//		pDibujo.add(dibujo);
//		pDibujo.repaint();
		return dibujo;
	}
	public ChartPanel pintarCal()
	{
		XYSeries series = new XYSeries("Calorías");
		for (int i=0; i<lista.size(); i++)
		{
			series.add(i+1, lista.get(i).getCalorias());
		}
				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		        
		JFreeChart chart = ChartFactory.createXYLineChart("Calorías","Nºcarrera","Cal",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		
		ChartPanel dibujo = new ChartPanel(chart);
//		pDibujo.add(dibujo);
//		pDibujo.repaint();
		return dibujo;
	}
	public ChartPanel pintarRitmo()
	{
		XYSeries series = new XYSeries("Ritmo");
		for (int i=0; i<lista.size(); i++)
		{
			String minutos = lista.get(i).getRitmo().split("\\.")[0];
			String segundos = lista.get(i).getRitmo().split("\\.")[1];
			int min = Integer.parseInt(minutos);
			int seg = Integer.parseInt(segundos);
			double ritmo = min + ((seg*100)/60);
			series.add(i+1, ritmo);
		}
				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		        
		JFreeChart chart = ChartFactory.createXYLineChart("Ritmo","Nºcarrera","Min/Km",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		
		ChartPanel dibujo = new ChartPanel(chart);
//		pDibujo.add(dibujo);
//		pDibujo.repaint();
		return dibujo;
	}


//	private void pAtributo(String atributo)
//	{	
//		try
//		{	
//			for (int i=0;i<lista.size();i++)
//			{
//				fechas.add(lista.get(i).getFecha());
//			}
//			Dibujo.iniciarDibujo(lista.size(), fechas);
//			
//			DibujoAtributo(atributo);
//			
//			Dibujo.repaint();
//		}
//		catch(NullPointerException e)
//		{
//			System.out.println("No hay carreras");
//		}
//	}
	
	
//	public void DibujoAtributo (String atributo)
//	{
//		switch (atributo)
//		{
//			case "cal":
//				for (int i=0; i<lista.size(); i++) 
//				{
//					double cal = lista.get(i).getCalorias();
//					String fecha = lista.get(i).getFecha();
//					Dibujo.dibujarAtributo(1, i, fecha, (int)cal);
//					Dibujo.repaint();
//				}
//				break;
//				
//			case "km":
//				for (int i=0; i<lista.size(); i++) 
//				{
//					double km = lista.get(i).getKm();
//					String fecha = lista.get(i).getFecha();
//					Dibujo.dibujarAtributo(1, i, fecha, (int)km);
//					Dibujo.repaint();
//				}
//				break;
//		}
//	}


}
