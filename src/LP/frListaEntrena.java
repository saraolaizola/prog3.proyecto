package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import COMUN.clsSinActividad;
import LD.BD;
import LN.clsEntrenamiento;
import LN.clsUsuario;

import java.awt.Font;

import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.SystemColor;

public class frListaEntrena extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pTabla,pSuperior,pDibujo;
	private JSplitPane spDatos;
	private JButton btnVer,bVolver;
	private JTable table;

	private ArrayList<clsEntrenamiento> lista = new ArrayList<clsEntrenamiento>();
	
	private ChartPanel dibujo;
	private JLabel lblEntrenas;

	/**
	 * Create the frame.
	 * @throws clsSinActividad 
	 */
	public frListaEntrena(clsUsuario user) throws clsSinActividad 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		pPrincipal = new JPanel();
		pPrincipal.setBackground(Color.WHITE);
		
		pTabla = new JPanel();
		pTabla.setBackground(Color.WHITE);
		
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
		
		pTabla.add(new JScrollPane(table));
		
		lista = BD.getMisEntrenamientos(user.getUsuario());
		
		Object [] row = new Object [2];
		
		int totalMin=0;
		
		try
		{
			for (int i=0;i<lista.size();i++)
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
				catch(NullPointerException e){
					seg=0;
				}
				totalMin=totalMin+min+(seg/60);
				
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

		pDibujo = new JPanel();
		pDibujo.setBackground(Color.WHITE);
		pDibujo.setLayout(new BorderLayout(0, 0));
		dibujo = pintar();
		pDibujo.add(dibujo,BorderLayout.CENTER);
		
		spDatos.setTopComponent(pDibujo);
		spDatos.setBottomComponent(pTabla);
		
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
		
		lblEntrenas = new JLabel("Entrenamientos       ");
		lblEntrenas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenas.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pSuperior.add(lblEntrenas, BorderLayout.CENTER);
		
		spDatos.setResizeWeight(.5d);
		
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
		btnVer.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					int row = table.getSelectedRow();
					String fecha = (String) table.getValueAt(row, 0);
					clsEntrenamiento entrena = BD.getEntrenamiento(fecha);
							
					frDetalleEntrena ventana = new frDetalleEntrena (user, entrena);
					ventana.setVisible(true);
					dispose();
				}
				catch (ArrayIndexOutOfBoundsException a)
				{
					Error();
				}
			}
		});
	}
	public ChartPanel pintar()
	{
		XYSeries series = new XYSeries("Duraci�n");
		for (int i=0; i<lista.size(); i++)
		{
			int min,seg;
			
			String minutos = lista.get(i).getDuracion().split("\\.")[0];
			min = Integer.parseInt(minutos);
			try
			{
				String segundos = lista.get(i).getDuracion().split("\\.")[1];
				seg = Integer.parseInt(segundos);
			}
			catch(NullPointerException e){
				seg=0;
			}
			double duracion = min + (seg/60);
			series.add(i+1, duracion);
		}
				
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		        
		JFreeChart chart = ChartFactory.createXYLineChart("","N�carrera","Min",  dataset, PlotOrientation.VERTICAL, true, false,false);       		
		
		ChartPanel dibujo = new ChartPanel(chart);
		return dibujo;
	}
	protected void Error()
	{
		JOptionPane.showMessageDialog(this,"Error. Selecciona un registro de carrera");
	}
}
