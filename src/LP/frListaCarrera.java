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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import COMUN.clsSinActividad;
import LD.BD;
import LN.clsCarrera;
import LN.clsUsuario;

import javax.swing.JRadioButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.border.EmptyBorder;

public class frListaCarrera extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pTabla,pSuperior,pDibujo,pBotones, pTexto,pText;
	private JSplitPane spDatos;
	private JLabel lblCarreras,lblNewLabel,lblTuHistorialDe;
	private JButton btnVer,bVolver;
	private JTable table;

	private ArrayList<clsCarrera>lista;
	
	private ChartPanel dibujo;
	private JFreeChart chart;
	private ButtonGroup BG;
	private JRadioButton rdbtnDistancia, rdbtnRitmo,rdbtnDuracion, rdbtnCalorias;
	

	/**
	 * Create the frame.
	 * @throws clsSinActividad 
	 */
	public frListaCarrera(clsUsuario user) throws clsSinActividad 
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
	
		String[] columnNames = {"Fecha","Duraci�n"};
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
		
		JScrollPane scrollPane = new JScrollPane(table);
		pTabla.add(scrollPane);
		
		lista = BD.getMisCarreras(user.getUsuario());
		
		Object [] row = new Object [2];
		try
		{
			for (int i=lista.size()-1;i>-1;i--)
			{
				row[0]=lista.get(i).getFecha();
				
				String minutos = lista.get(i).getDuracion().split("\\.")[0];
				int min = Integer.parseInt(minutos);
				String segundos;
				int seg;
				try
				{
					segundos = lista.get(i).getDuracion().split("\\.")[1];
					seg = Integer.parseInt(segundos);
				}
				catch(ArrayIndexOutOfBoundsException e){
					seg=0;
				}
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
		
		btnVer = new JButton("M�s informaci�n");
		pTabla.add(btnVer, BorderLayout.SOUTH);

		pDibujo.setLayout(new BorderLayout(0, 0));
		
		pBotones = new JPanel();
		BG = new ButtonGroup();
		rdbtnDuracion = new JRadioButton("Duraci\u00F3n (min)");
		rdbtnDistancia = new JRadioButton("Distancia (km)");
		rdbtnRitmo = new JRadioButton("Ritmo");
		rdbtnCalorias = new JRadioButton("Calor\u00EDas");
		
		rdbtnDistancia.setSelected(true);
		
		pTexto = new JPanel();
		pTexto.setBackground(Color.WHITE);
		pTexto.setBorder(new EmptyBorder(5, 5, 5, 5));
		pDibujo.add(pTexto, BorderLayout.NORTH);
		pTexto.setLayout(new BorderLayout(0, 0));
		
		lblTuHistorialDe = new JLabel("Tu historial de carreras:");
		pTexto.add(lblTuHistorialDe, BorderLayout.NORTH);
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
		
		pText = new JPanel();
		pText.setBackground(Color.WHITE);
		pText.setBorder(new EmptyBorder(5, 5, 5, 5));
		pTabla.add(pText, BorderLayout.NORTH);
		pText.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel("Detalles de cada carrera:");
		pText.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		
		pSuperior = new JPanel();
		pSuperior.setBorder(new EmptyBorder(6, 2, 6, 0));
		pSuperior.setBackground(SystemColor.menu);
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);            
		bVolver.setContentAreaFilled(false);
		bVolver.setBorderPainted(false);     
		bVolver.setBorder(null); 
		pSuperior.add(bVolver, BorderLayout.WEST);
		
		lblCarreras = new JLabel("Carreras     ");
		lblCarreras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarreras.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pSuperior.add(lblCarreras, BorderLayout.CENTER);
	
		spDatos.setResizeWeight(.5d);
		
		setSize(375,667);
		setResizable(false);
		
		btnVer.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					int row = table.getSelectedRow();
					String fecha = (String) table.getValueAt(row, 0);
					clsCarrera carrera = BD.getCarrera(fecha);
					
					frDetalleCarrera ventana = new frDetalleCarrera (user, carrera);
					ventana.setVisible(true);
					dispose();
				}
				catch (ArrayIndexOutOfBoundsException a)
				{
					Error();
				}
				
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
		ActionListener listener = new ActionListener() 
		{
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	            JRadioButton btn = (JRadioButton) e.getSource();
	            
	            JPanel viejoDibujo = dibujo;
	            
	            switch (btn.getText())
	            {
	            	case "Distancia (km)":
	            		dibujo=pintarKm();
	            		break;
	            		
	            	case "Ritmo":
	            		dibujo = pintarRitmo();
	            		break;
	            		
	            	case "Duraci\u00F3n (min)":
	            		dibujo=pintarMin();
	            		break;
	            		
	            	case "Calor\u00EDas":
	            		dibujo=pintarCal();
	            		break;
	            }
	            
	            if (viejoDibujo != null) 
        		{
        			pDibujo.remove(viejoDibujo);
        		}
	            pDibujo.add(dibujo,BorderLayout.CENTER);
        		pDibujo.revalidate();
        		pDibujo.repaint();
	        }
	    };
	    
	    rdbtnDuracion.addActionListener(listener);
		rdbtnDistancia.addActionListener(listener);
		rdbtnRitmo.addActionListener(listener);
		rdbtnCalorias.addActionListener(listener);
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
		        
		chart = ChartFactory.createXYLineChart("Distancia","N�carrera","Km",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		dibujo = new ChartPanel(chart);
		
		return dibujo;
	}
	public ChartPanel pintarMin()
	{
		XYSeries series = new XYSeries("Duraci�n");
		for (int i=0; i<lista.size(); i++)
		{
			String minutos = lista.get(i).getDuracion().split("\\.")[0];
			int min = Integer.parseInt(minutos);
			String segundos;
			int seg;
			try
			{
				segundos = lista.get(i).getDuracion().split("\\.")[1];
				seg = Integer.parseInt(segundos);
			}
			catch(ArrayIndexOutOfBoundsException e){
				seg=0;
			}
			double duracion = min + (seg/60);
			series.add(i+1, duracion);
		}
				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		        
		chart = ChartFactory.createXYLineChart("Duraci�n","N�carrera","Min",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		dibujo = new ChartPanel(chart);
		
		return dibujo;
	}
	public ChartPanel pintarCal()
	{
		XYSeries series = new XYSeries("Calor�as");
		for (int i=0; i<lista.size(); i++)
		{
			series.add(i+1, lista.get(i).getCalorias());
		}
				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		        
		chart = ChartFactory.createXYLineChart("Calor�as","N�carrera","Cal",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		dibujo = new ChartPanel(chart);
		
		return dibujo;
	}
	public ChartPanel pintarRitmo()
	{
		XYSeries series = new XYSeries("Ritmo");
		for (int i=0; i<lista.size(); i++)
		{
			String minutos = lista.get(i).getRitmo().split("\\.")[0];
			int min = Integer.parseInt(minutos);
			String segundos;
			int seg;
			try
			{
				segundos = lista.get(i).getRitmo().split("\\.")[1];
				seg = Integer.parseInt(segundos);
			}
			catch(ArrayIndexOutOfBoundsException e){
				seg=0;
			}
			double ritmo = min + (seg/60);
			series.add(i+1, ritmo);
		}
				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		        
		chart = ChartFactory.createXYLineChart("Ritmo","N�carrera","Min/Km",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		dibujo = new ChartPanel(chart);
		
		return dibujo;
	}
	
	protected void Error()
	{
		JOptionPane.showMessageDialog(this,"Error. Selecciona un registro de carrera");
	}
}
