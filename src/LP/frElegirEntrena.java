package LP;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import LD.BD;
import LN.clsOpcEntrenamiento;
import javax.swing.JLabel;

public class frElegirEntrena extends JFrame 
{
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
		
		String[] columnNames = 
				{"Código",
                "Nombre",
                "Nivel",
                "CalxMin",
                "Duración"};
		
		ArrayList <clsOpcEntrenamiento> lista = BD.getLista();
		
		DefaultTableModel model = new DefaultTableModel();
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
		
		table = new JTable();
		table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
		
		pTabla.add(table);
		
		setSize(375,667);
	}
		
//	public void cargarTableModel()
//	{
//		Vector filas = BD.lista();
//		if(filas == null) JOptionPane.showMessageDialog(this,"No existen personas registradas en el sistema","Resultado de la Operacion",JOptionPane.INFORMATION_MESSAGE);
//		
//		//identificacion tipoid nombre apellido fecha_nacimiento sexo estado_civil barrio direccion telefono celular email foto info_estudios info_laboral
//		Vector columnas = new Vector();
//		columnas.add("Código");
//		columnas.add("Nombres");
//		columnas.add("Nivel");
//		columnas.add("CalxMin");
//		columnas.add("Duracion");
//
//		DefaultTableModel datos = new DefaultTableModel(filas,columnas);
//		this.table.setModel(datos);
//		
//		
//		
//	}
}
