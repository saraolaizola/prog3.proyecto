package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import LN.clsUsuario;
import java.awt.FlowLayout;

/**
 * Pantalla registros. El usuario decide que registros quiere ver (carrera o entrenamiento)
 * y le lleva a la pantalla correspondiente
 * @author ALUMNO
 *
 */
public class frLista extends JFrame 
{
	JPanel pPrincipal, pMenu, pCentral, pEntrena, pCorrer;
	JButton bRegistro,bActividad,bPerfil, bEmpezar, bCorrer, bEntrenar;  
	
	public frLista(clsUsuario user) 
	{
		// Liberaci�n de la ventana por defecto al cerrar
				setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
				
				// Creaci�n contenedores y componentes
				pPrincipal = new JPanel();
				pMenu = new JPanel();
				pCorrer = new JPanel();
				
				getContentPane().add( pPrincipal );
				pPrincipal.setBackground( Color.white );
				getContentPane().add(pMenu, BorderLayout.SOUTH);
				
				bEntrenar = new JButton();
				bEntrenar.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/ientrena.jpg")));
				bEntrenar.setHorizontalAlignment(SwingConstants.CENTER);
				bEntrenar.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
				bEntrenar.setContentAreaFilled(false); // No rellenar el área
				bEntrenar.setBorderPainted(false);     // No pintar el borde
				bEntrenar.setBorder(null); 
				
				pPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				bCorrer = new JButton();
				bCorrer.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/icorrer.jpg")));
				bCorrer.setHorizontalAlignment(SwingConstants.CENTER);
				bCorrer.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
				bCorrer.setContentAreaFilled(false); // No rellenar el área
				bCorrer.setBorderPainted(false);     // No pintar el borde
				bCorrer.setBorder(null); 
				
				pPrincipal.add(bCorrer);
				pPrincipal.add(bEntrenar);
				
				bRegistro = new JButton( "Registros" );
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
						frListaCarrera ventana = new frListaCarrera (user);
						ventana.setVisible(true);
						dispose();
					}
				});
				bEntrenar.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frListaEntrena ventana = new frListaEntrena (user);
						ventana.setVisible(true);
						dispose();
					}
				});
	}

}
