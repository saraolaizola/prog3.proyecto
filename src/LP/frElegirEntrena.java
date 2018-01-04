package LP;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import COMUN.clsOpcEntrenamientoRepetida;
import LD.BD;
import LN.clsOpcEntrenamiento;
import LN.clsUsuario;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.JComboBox;

public class frElegirEntrena extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pTabla,pSuperior,pInferior,pDatos,pTexto,pNombre,pNivel,pCalorias,pFichero,pDuracion,panel,panel_1,panel_2,panel_3;
	private JSplitPane spCentral;
	private JLabel lblElegirEntrenamiento,lblAadirMiEntrenamiento,lblNombre,lblNivel,lblCaloras,lblFicheroMultimedia,lblMin;
	private JButton btnEmpezar,bFichero,btnAñadir,bVolver;
	private JTable table;
	private JTextField txtNombre,txtCal,txtMin;
	private JComboBox cmbNivel;
	private File fichero;
	private ArrayList <clsOpcEntrenamiento> lista;
	
	public frElegirEntrena(clsUsuario user) 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		String vlcPath = System.getenv().get( "vlc" );
		
    	if (vlcPath==null)
			// Poner VLC a mano
			System.setProperty("jna.library.path", "c:\\Archivos de programa\\videolan\\vlc-2.1.5");
		else
			// Poner VLC desde la variable de entorno
			System.setProperty( "jna.library.path", vlcPath );
		
		spCentral = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		spCentral.setBackground(Color.WHITE);
		pTabla = new JPanel();
		pTabla.setBackground(Color.WHITE);
		getContentPane().add( spCentral, BorderLayout.CENTER );
		
		String[] columnNames = {"Nombre","Nivel","CalxMin","Duración"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}	
		};
		pTabla.setLayout(new BorderLayout(0, 0));
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int [] ancho = {128,98,64,64};
		for (int i=0;i<4;i++)
		{
			table.getColumnModel().getColumn(i).setPreferredWidth(ancho[i]);
		}
		
		pTabla.add(new JScrollPane(table), BorderLayout.CENTER);
		
		lista = BD.getLista();
		Object [] row = new Object [4];
		try
		{
			for (int i=0;i<lista.size();i++)
			{
				row[0]=lista.get(i).getNombre();
				row[1]=lista.get(i).getNivel();
				row[2]=lista.get(i).getCalxsec();
				row[3]=lista.get(i).getDuracion();
				model.addRow(row);
			}	
		}
		catch (NullPointerException e)
		{
			
		}
		
		btnEmpezar = new JButton("Empezar");
		btnEmpezar.setForeground(Color.WHITE);
		btnEmpezar.setBackground(new Color(34, 139, 34));
		pTabla.add(btnEmpezar, BorderLayout.SOUTH);
		
		pSuperior = new JPanel();
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);           
		bVolver.setContentAreaFilled(false);
		bVolver.setBorderPainted(false);     
		bVolver.setBorder(null);
		pSuperior.add(bVolver, BorderLayout.WEST);
		
		panel = new JPanel();
		pSuperior.add(panel, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		pSuperior.add(panel_1, BorderLayout.SOUTH);
		
		lblElegirEntrenamiento = new JLabel("Elegir Entrenamiento     ");
		lblElegirEntrenamiento.setHorizontalAlignment(SwingConstants.CENTER);
		lblElegirEntrenamiento.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pSuperior.add(lblElegirEntrenamiento, BorderLayout.CENTER);
		
		pInferior = new JPanel();
		pInferior.setBackground(Color.WHITE);
		pInferior.setLayout(new BorderLayout(0, 0));
		
		pDatos = new JPanel();
		pDatos.setBackground(Color.WHITE);
		pInferior.add(pDatos, BorderLayout.CENTER);
		pDatos.setLayout(new GridLayout(0, 1, 0, 0));
		
		pNombre = new JPanel();
		pDatos.add(pNombre);
		pNombre.setBackground(Color.WHITE);
		lblNombre = new JLabel("Nombre:");
		pNombre.add(lblNombre);
		txtNombre = new JTextField();
		pNombre.add(txtNombre);
		txtNombre.setColumns(20);
		
		pCalorias = new JPanel();
		pDatos.add(pCalorias);
		pCalorias.setBackground(Color.WHITE);
		lblCaloras = new JLabel("Calor\u00EDas (cal/min) :");
		pCalorias.add(lblCaloras);
		
		txtCal = new JTextField();
		pCalorias.add(txtCal);
		txtCal.setColumns(10);
		
		pDuracion = new JPanel();
		pDatos.add(pDuracion);
		pDuracion.setBackground(Color.WHITE);
		
		lblMin = new JLabel("Duraci\u00F3n (min):");
		pDuracion.add(lblMin);
		
		txtMin = new JTextField();
		txtMin.setColumns(10);
		pDuracion.add(txtMin);
		
		pNivel = new JPanel();
		pDatos.add(pNivel);
		pNivel.setBackground(Color.WHITE);
		lblNivel = new JLabel("Nivel:");
		pNivel.add(lblNivel);
		
		cmbNivel = new JComboBox();
		cmbNivel.addItem("Principiante");
		cmbNivel.addItem("Intermedio");
		cmbNivel.addItem("Experto");
		pNivel.add(cmbNivel);
		
		pFichero = new JPanel();
		pDatos.add(pFichero);
		pFichero.setBackground(Color.WHITE);
		lblFicheroMultimedia = new JLabel("V\u00EDdeo");
		pFichero.add(lblFicheroMultimedia);
		
		bFichero = new JButton("+");
		pFichero.add(bFichero);
		
		pTexto = new JPanel();
		pTexto.setBackground(Color.WHITE);
		pInferior.add(pTexto, BorderLayout.NORTH);
		pTexto.setLayout(new BorderLayout(0, 0));
		lblAadirMiEntrenamiento = new JLabel("  A\u00F1adir mi entrenamiento");
		lblAadirMiEntrenamiento.setHorizontalAlignment(SwingConstants.LEFT);
		lblAadirMiEntrenamiento.setFont(new Font("Tahoma", Font.BOLD, 12));
		pTexto.add(lblAadirMiEntrenamiento);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		pTexto.add(panel_2, BorderLayout.SOUTH);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		pTexto.add(panel_3, BorderLayout.NORTH);
		
		btnAñadir = new JButton ("Añadir");
		pInferior.add(btnAñadir, BorderLayout.SOUTH);
		
		spCentral.setTopComponent(pTabla);
		spCentral.setBottomComponent(pInferior);
		spCentral.setResizeWeight(.5d);
		
		setSize(375,667);
		setResizable(false);
		
		btnEmpezar.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int row = table.getSelectedRow();
				String codigo = (String) table.getValueAt(row, 0);
				clsOpcEntrenamiento entrena = BD.getEntrena(codigo);
				
				frEntrena ventana = new frEntrena (entrena, user);
				ventana.setVisible(true);
				dispose();
			}
		});
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frPrincipal ventana = new frPrincipal (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		btnAñadir.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Random rn = new Random();
				
				try
				{
					if (fichero!=null)
					{
						String codigo=rn.nextInt(999)+"";	
						double calxsec = Double.parseDouble(txtCal.getText())/60;
						String nivel = String.valueOf(cmbNivel.getSelectedItem());
						int duracion = Integer.parseInt(txtMin.getText());
						
						BD.registrarOpcEntrenamiento(fichero,codigo,txtNombre.getText(),nivel,calxsec,duracion);
							
						lista = BD.getLista();
						
						int i = lista.size();
						row[0]=lista.get(i-1).getNombre();
						row[1]=lista.get(i-1).getNivel();
						row[2]=lista.get(i-1).getCalxsec();
						row[3]=lista.get(i-1).getDuracion();
						model.addRow(row);
						table.repaint();
						
						txtMin.setText("");
						txtNombre.setText("");
						txtCal.setText("");
						fichero=null;
						
					}
					else
					{
						System.out.println("Error al cargar el fichero");
					}
				}	
				catch (clsOpcEntrenamientoRepetida a)
				{
					btnAñadir.setSelected(true);
				}
			}
		});
		bFichero.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				fichero = pedirFicheroVPD();
			}
		});
	}
	private File pedirFicheroVPD() 
	{
		File dirActual = new File( System.getProperty("user.dir") );
		JFileChooser chooser = new JFileChooser( dirActual );
		chooser.setFileSelectionMode( JFileChooser.FILES_ONLY );
		chooser.setFileFilter( new FileNameExtensionFilter("vpd","wmv"));
		int returnVal = chooser.showOpenDialog( null );
		if (returnVal == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();
		else 
			return null;
	}
}

