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

public class frListaCarrera extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pTabla,pSuperior,pDibujo,pBotones;
	private JSplitPane spDatos;
	private JLabel lblSelecciona;
	private JButton btnVer,btnVolver;
	private JTable table;

	private ArrayList<String> fechas;
	private ArrayList<clsCarrera>lista;
	private clsUsuario usuario;
	
	private pDibujoRegistrosC Dibujo = new pDibujoRegistrosC(300,200);
	private ButtonGroup BG;
	private JRadioButton rdbtnDistancia, rdbtnRitmo,rdbtnDuracion, rdbtnCalorias;
	
	/**
	 * Create the frame.
	 */
	public frListaCarrera(clsUsuario user) 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		pPrincipal = new JPanel();
		pTabla = new JPanel();
		pDibujo = new JPanel();
		spDatos = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		pPrincipal.setLayout(new BorderLayout(0, 0));
	
		usuario = user;
		
		lblSelecciona = new JLabel("Seleccionar:");
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
				row[1]=lista.get(i).getDuracion();
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
		pCalorias();
		
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
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		btnVolver = new JButton("");
		btnVolver.setHorizontalAlignment(SwingConstants.LEFT);
		btnVolver.setIcon(new ImageIcon(frElegirEntrena.class.getResource("/img/back.png")));
		btnVolver.setBorderPainted( false );
		pSuperior.add(btnVolver);
		
		setSize(375,667);
		setResizable(false);
		btnVolver.addActionListener( new ActionListener() 
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
		    
		  }
		});
		rdbtnCalorias.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  pCalorias();
		  }
		});
		
		rdbtnRitmo.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    
		  }
		});
	}
	
	private void pCalorias()
	{	
		try
		{
			int tamMax=0;
			
			for (int i=0;i<lista.size();i++)
			{
				fechas.add(lista.get(i).getFecha());
			}
			
			Dibujo.iniciarDibujo(lista.size(), fechas);
			
			for (int i=0; i<lista.size(); i++) 
			{
				// Realizar prueba
				int cal = lista.get(i).getCalorias();
				String fecha = lista.get(i).getFecha();
				
				// Añadir dato a dibujo
				Dibujo.dibujarCal(1, i, fecha, cal);
				Dibujo.repaint();
				// Cancelar el hilo si se interrumpe desde fuera
				if (Thread.interrupted()) return;  
			}
			
			Dibujo.repaint();
		}
		catch(NullPointerException e)
		{
			System.out.println("No hay carreras");
		}
	}
}