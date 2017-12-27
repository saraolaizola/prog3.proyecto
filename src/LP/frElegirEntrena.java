package LP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import LD.BD;
import LN.clsOpcEntrenamiento;
import javax.swing.JLabel;

public class frElegirEntrena extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal,pTabla;
	private JTable table;
	private JLabel lblEligaSuEntrenamiento;
	
	public frElegirEntrena() 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		pPrincipal = new JPanel();
		getContentPane().add( pPrincipal, BorderLayout.NORTH );
		pPrincipal.setLayout(new BorderLayout(0, 0));
		
		lblEligaSuEntrenamiento = new JLabel("Eliga su entrenamiento:");
		pPrincipal.add(lblEligaSuEntrenamiento);
		
		
		pTabla = new JPanel();
		getContentPane().add(pTabla, BorderLayout.CENTER);
		
		String[] columnNames = {"Código","Nombre","Nivel","CalxMin","Duración"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		table = new JTable(model);
		
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
		catch (NullPointerException e)
		{
		}
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
		
		pTabla.add(table);
		
		setSize(375,667);
	}
		
}
