package LP;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import LD.BD;
import LN.clsCarrera;
import LN.clsOpcEntrenamiento;
import LN.clsUsuario;

public class frListaCarrera extends JFrame 
{

	private JPanel pPrincipal,pTabla,pSuperior;
	private JLabel lblSelecciona;
	private JButton btnVer,btnVolver;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public frListaCarrera(clsUsuario user) 
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
		
		String[] columnNames = {"Fecha","Duración"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		
		pPrincipal.add(pTabla, BorderLayout.CENTER);
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		pTabla.add(new JScrollPane(table));
		
		ArrayList <clsCarrera> lista = BD.getMisCarreras(user.getUsuario());
		
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
		
		btnVer = new JButton("Más información");
		getContentPane().add(btnVer, BorderLayout.SOUTH);
		
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
	}
}
