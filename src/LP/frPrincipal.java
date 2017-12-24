package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

import LN.clsUsuario;

public class frPrincipal extends JFrame 
{
	JPanel pPrincipal, pMenu, pCentral;
	JButton bRegistro,bActividad,bPerfil, bEmpezar, bCorrer, bEntrenar;
	
	public frPrincipal(clsUsuario user) 
	{
		// Liberaci�n de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Creaci�n contenedores y componentes
		pPrincipal = new JPanel();
		pMenu = new JPanel();
		pCentral = new JPanel();
		
		getContentPane().add( pPrincipal );
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		getContentPane().add (pCentral, BorderLayout.CENTER);
		getContentPane().add(pMenu, BorderLayout.SOUTH);
		
		JSplitPane spCentro = new JSplitPane( JSplitPane.VERTICAL_SPLIT );
		
		bEntrenar = new JButton();
		bEntrenar.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/entrenar.png")));
		bEntrenar.setHorizontalAlignment(SwingConstants.CENTER);
		bEntrenar.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		bEntrenar.setContentAreaFilled(false); // No rellenar el área
		bEntrenar.setBorderPainted(false);     // No pintar el borde
		bEntrenar.setBorder(null);  
		
		bCorrer = new JButton();
		bCorrer.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/correr.png")));
		bCorrer.setHorizontalAlignment(SwingConstants.CENTER);
		bCorrer.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		bCorrer.setContentAreaFilled(false); // No rellenar el área
		bCorrer.setBorderPainted(false);     // No pintar el borde
		bCorrer.setBorder(null);  
		
		spCentro.setTopComponent(bCorrer);
		spCentro.setBottomComponent(bEntrenar);
		
		pCentral.add(spCentro);
		
		bRegistro = new JButton( "Registro" );
		bActividad = new JButton ("Actividad");
		bPerfil = new JButton( "Perfil");
		
		
		// A�adido de componentes a contenedores
		pMenu.add(bRegistro);
		pMenu.add(bActividad);
		pMenu.add(bPerfil);
		
		// Formato de ventana
		setSize(375,667);
		
		// Escuchadores de botones
		bRegistro.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frLista ventana = new frLista (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		bActividad.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frPrincipal ventana = new frPrincipal (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		bPerfil.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frPerfil ventana = new frPerfil (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		bCorrer.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frCarrera ventana = new frCarrera (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		bEntrenar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frElegirEntrena ventana = new frElegirEntrena ();
				ventana.setVisible(true);
				dispose();
			}
		});
	}
}