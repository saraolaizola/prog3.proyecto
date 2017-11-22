package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class frPrincipal extends JFrame 
{
	JPanel pPrincipal;   
	
	public frPrincipal(String usuario) 
	{
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		JPanel pMenu = new JPanel();
		JButton bRegistro = new JButton( "Registro" );
		JButton bActividad = new JButton( "Actividad" );
		JButton bPerfil = new JButton( "Perfil" );
		
		// Formato y layouts
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		
		// Añadido de componentes a contenedores
		add( pPrincipal, BorderLayout.CENTER );
		pMenu.add( bRegistro );
		pMenu.add( bActividad );
		pMenu.add( bPerfil );
		add( pMenu, BorderLayout.SOUTH );
		
		// Formato de ventana
		setSize( 750, 1334 );
		setVisible(true);
		
		// Escuchadores de botones
		bRegistro.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		bActividad.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		bPerfil.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
	}
}