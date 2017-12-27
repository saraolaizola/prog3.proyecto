package LP;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import LD.BD;
import LN.clsOpcEntrenamiento;
import LN.clsUsuario;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class frElegirEntrena extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pTabla,pSuperior;
	private JLabel lblSelecciona,lblVolver;
	private JButton btnEmpezar;
	private JTable table;
	
	public frElegirEntrena(clsUsuario user) 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		pPrincipal = new JPanel();
		pTabla = new JPanel();
		getContentPane().add( pPrincipal, BorderLayout.CENTER );
		pPrincipal.setLayout(new BorderLayout(0, 0));
		
		lblSelecciona = new JLabel("Seleccionar:");
		lblSelecciona.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelecciona.setHorizontalAlignment(SwingConstants.CENTER);
		
		pPrincipal.add(lblSelecciona, BorderLayout.NORTH);
		
		String[] columnNames = {"Código","Nombre","Nivel","CalxMin","Duración"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		
		pPrincipal.add(pTabla, BorderLayout.CENTER);
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		pTabla.add(new JScrollPane(table));
		pTabla.add(table);
		
		ArrayList <clsOpcEntrenamiento> lista = BD.getLista();
		Object [] row = new Object [5];
		try
		{
			for (int i=0;i<lista.size();i++)
			{
				row[0]=lista.get(i).getCodigo();
				row[1]=lista.get(i).getNombre();
				row[2]=lista.get(i).getNivel();
				row[3]=lista.get(i).getCalxmin();
				row[4]=lista.get(i).getDuracion();
				model.addRow(row);
			}	
		}
		catch (NullPointerException e){
		}
		
		btnEmpezar = new JButton("Empezar");
		getContentPane().add(btnEmpezar, BorderLayout.SOUTH);
		
		pSuperior = new JPanel();
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		lblVolver = new JLabel("");
		lblVolver.setHorizontalAlignment(SwingConstants.LEFT);
		lblVolver.setIcon(new ImageIcon(frElegirEntrena.class.getResource("/img/back.png")));
		pSuperior.add(lblVolver);
		
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
	}	
}

