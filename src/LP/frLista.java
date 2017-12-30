package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import LN.clsUsuario;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * Pantalla registros. El usuario decide que registros quiere ver (carrera o entrenamiento)
 * y le lleva a la pantalla correspondiente
 * @author ALUMNO
 *
 */
public class frLista extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	JPanel pPrincipal, pMenu, pCentral, pEntrena, pCorrer;
	JButton bRegistro,bActividad,bPerfil, bEmpezar, bCorrer, bEntrenar;  
	private JPanel pSuperior;
	private JLabel lblRegistros;
	
	public frLista(clsUsuario user) 
	{
		// Liberaci�n de la ventana por defecto al cerrar
				setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
				setBackground(Color.white);
				
				// Creaci�n contenedores y componentes
				pPrincipal = new JPanel();
				pPrincipal.setBackground(Color.white);
				pMenu = new JPanel();
				pCorrer = new JPanel();
				
				pSuperior = new JPanel();
				getContentPane().add(pSuperior, BorderLayout.NORTH);
				
				lblRegistros = new JLabel("Registros");
				lblRegistros.setFont(new Font("Tahoma", Font.PLAIN, 17));
				pSuperior.add(lblRegistros);
				
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
				setResizable(false);
				
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
