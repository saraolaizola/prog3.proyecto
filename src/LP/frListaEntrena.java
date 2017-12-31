package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import LD.BD;
import LN.clsCarrera;
import LN.clsEntrenamiento;
import LN.clsUsuario;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class frListaEntrena extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pTabla,pSuperior,pDatos;
	private JButton btnMasInfo,bVolver;
	private JTable table;

	private ArrayList<clsEntrenamiento>lista;
	private clsUsuario usuario;
	private JLabel lblEntrenamientos;
	private JLabel lblNument;
	private JLabel lblNumTime;
	private JLabel lblDuracion;
	private JLabel lblEntrenamientos_1;

	/**
	 * Create the frame.
	 */
	public frListaEntrena(clsUsuario user) 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		pPrincipal = new JPanel();
		pPrincipal.setBackground(Color.WHITE);
		
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		pPrincipal.setLayout(new BorderLayout(0, 0));
	
		usuario = user;
		
		String[] columnNames = {"Fecha","Duración"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		
		DefaultTableCellRenderer RendererCentro = new DefaultTableCellRenderer();
		RendererCentro.setHorizontalAlignment(JLabel.CENTER);
		
		lista = BD.getMisEntrenamientos(user.getUsuario());
		
		Object [] row = new Object [2];
		
		int totalMin=0;
		
		try
		{
			for (int i=0;i<lista.size();i++)
			{
				row[0]=lista.get(i).getFecha();
				
				String minutos = lista.get(i).getDuracion().split("\\.")[0];
				String segundos = lista.get(i).getDuracion().split("\\.")[1];
				int min = Integer.parseInt(minutos);
				int seg = Integer.parseInt(segundos);
				totalMin=totalMin+min;
				
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
		
		pDatos = new JPanel();
		pPrincipal.add(pDatos, BorderLayout.CENTER);
		pDatos.setBackground(Color.BLACK);
		GridBagLayout gbl_pDatos = new GridBagLayout();
		gbl_pDatos.columnWidths = new int[]{367, 0};
		gbl_pDatos.rowHeights = new int[]{65, 13, 24, 13, 0};
		gbl_pDatos.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_pDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pDatos.setLayout(gbl_pDatos);
		
		lblNument = new JLabel(lista.size()+"");
		lblNument.setForeground(Color.WHITE);
		lblNument.setHorizontalAlignment(SwingConstants.CENTER);
		lblNument.setFont(new Font("Arial", Font.BOLD, 65));
		GridBagConstraints gbc_lblNument = new GridBagConstraints();
		gbc_lblNument.anchor = GridBagConstraints.SOUTH;
		gbc_lblNument.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNument.insets = new Insets(0, 0, 5, 0);
		gbc_lblNument.gridx = 0;
		gbc_lblNument.gridy = 0;
		pDatos.add(lblNument, gbc_lblNument);
		
		lblEntrenamientos = new JLabel("Entrenamientos totales");
		lblEntrenamientos.setForeground(Color.WHITE);
		lblEntrenamientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenamientos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GridBagConstraints gbc_lblEntrenamientos = new GridBagConstraints();
		gbc_lblEntrenamientos.fill = GridBagConstraints.BOTH;
		gbc_lblEntrenamientos.insets = new Insets(0, 0, 5, 0);
		gbc_lblEntrenamientos.gridx = 0;
		gbc_lblEntrenamientos.gridy = 1;
		pDatos.add(lblEntrenamientos, gbc_lblEntrenamientos);
		
		lblNumTime = new JLabel(totalMin+"");
		lblNumTime.setForeground(Color.WHITE);
		lblNumTime.setFont(new Font("Arial", Font.BOLD, 20));
		GridBagConstraints gbc_lblNumTime = new GridBagConstraints();
		gbc_lblNumTime.fill = GridBagConstraints.VERTICAL;
		gbc_lblNumTime.insets = new Insets(0, 0, 5, 0);
		gbc_lblNumTime.gridx = 0;
		gbc_lblNumTime.gridy = 2;
		pDatos.add(lblNumTime, gbc_lblNumTime);
		
		lblDuracion = new JLabel("Minutos totales");
		lblDuracion.setForeground(Color.WHITE);
		lblDuracion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.fill = GridBagConstraints.VERTICAL;
		gbc_lblDuracion.gridx = 0;
		gbc_lblDuracion.gridy = 3;
		pDatos.add(lblDuracion, gbc_lblDuracion);
		
		pTabla = new JPanel();
		pPrincipal.add(pTabla, BorderLayout.SOUTH);
		pTabla.setBackground(Color.WHITE);
		pTabla.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		pTabla.add(new JScrollPane(table));
		
		table.getColumnModel().getColumn(0).setCellRenderer(RendererCentro);
		table.getColumnModel().getColumn(1).setCellRenderer(RendererCentro);
		
		btnMasInfo = new JButton("Más información");
		pTabla.add(btnMasInfo, BorderLayout.SOUTH);
		
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
		
		lblEntrenamientos_1 = new JLabel("Entrenamientos         ");
		lblEntrenamientos_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrenamientos_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pSuperior.add(lblEntrenamientos_1, BorderLayout.CENTER);
		
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
		//Intentar que al clicar ya diriga a detalle carrera sin el botón más información
		btnMasInfo.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int row = table.getSelectedRow();
				String fecha = (String) table.getValueAt(row, 0);
				clsEntrenamiento entrena = BD.getEntrenamiento(fecha);
						
				frDetalleEntrena ventana = new frDetalleEntrena (user, entrena);
				ventana.setVisible(true);
				dispose();
		}
		});
	}
}
