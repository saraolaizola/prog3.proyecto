package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import COMUN.clsSinActividad;
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
	private JPanel panel;
	private JPanel panel_1;
	
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
				pSuperior.setLayout(new BorderLayout(0, 0));
				
				lblRegistros = new JLabel("Registros");
				lblRegistros.setHorizontalAlignment(SwingConstants.CENTER);
				lblRegistros.setFont(new Font("Tahoma", Font.PLAIN, 17));
				pSuperior.add(lblRegistros);
				
				panel = new JPanel();
				pSuperior.add(panel, BorderLayout.NORTH);
				
				panel_1 = new JPanel();
				pSuperior.add(panel_1, BorderLayout.SOUTH);
				
				getContentPane().add( pPrincipal );
				pPrincipal.setBackground( Color.white );
				getContentPane().add(pMenu, BorderLayout.SOUTH);
				
				bEntrenar = new JButton();
				bEntrenar.setIcon(new ImageIcon(frLista.class.getResource("/img/registroE.jpg")));
				bEntrenar.setHorizontalAlignment(SwingConstants.CENTER);
				bEntrenar.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
				bEntrenar.setContentAreaFilled(false); // No rellenar el área
				bEntrenar.setBorderPainted(false);     // No pintar el borde
				bEntrenar.setBorder(null); 
				
				pPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
				bCorrer = new JButton();
				bCorrer.setIcon(new ImageIcon(frLista.class.getResource("/img/registroC.jpg")));
				bCorrer.setHorizontalAlignment(SwingConstants.CENTER);
				bCorrer.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
				bCorrer.setContentAreaFilled(false); // No rellenar el área
				bCorrer.setBorderPainted(false);     // No pintar el borde
				bCorrer.setBorder(null); 
				
				pPrincipal.add(bCorrer);
				pPrincipal.add(bEntrenar);
				
				bRegistro = new JButton( "Registros" );
				bRegistro.setBackground(SystemColor.menu);
				bRegistro.setFont(new Font("Tahoma", Font.BOLD, 9));
				bRegistro.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/registro.png")));
				bRegistro.setVerticalTextPosition(SwingConstants.BOTTOM);
				bRegistro.setHorizontalTextPosition(SwingConstants.CENTER);
				bRegistro.setBorderPainted(false);
				
				bActividad = new JButton ("Actividad");
				bActividad.setBackground(new Color(240, 240, 240));
				bActividad.setFont(new Font("Tahoma", Font.PLAIN, 9));
				bActividad.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/actividad.png")));
				bActividad.setVerticalTextPosition(SwingConstants.BOTTOM);
				bActividad.setHorizontalTextPosition(SwingConstants.CENTER);
				bActividad.setBorderPainted(false);
				
				bPerfil = new JButton( "Perfil");
				bPerfil.setBackground(SystemColor.menu);
				bPerfil.setFont(new Font("Tahoma", Font.PLAIN, 9));
				bPerfil.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/perfil.png")));
				bPerfil.setVerticalTextPosition(SwingConstants.BOTTOM);
				bPerfil.setHorizontalTextPosition(SwingConstants.CENTER);
				bPerfil.setBorderPainted(false); 
				
				pMenu.add(bRegistro);
				pMenu.add(bActividad);
				pMenu.add(bPerfil);
				
				pMenu.setLayout(new GridLayout(0, 3, 0, 0));
				
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
						try
						{
							frListaCarrera ventana = new frListaCarrera (user);
							ventana.setVisible(true);
							dispose();
						}
						catch (clsSinActividad a)
						{
							Error(a);
						}
					}
				});
				bEntrenar.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						try
						{
							frListaEntrena ventana = new frListaEntrena (user);
							ventana.setVisible(true);
							dispose();
						}
						catch (clsSinActividad a)
						{
							Error(a);
						}
					}
				});
	}
	
	protected void Error(clsSinActividad a)
	{
		JOptionPane.showMessageDialog(this, a.getMessage());
	}
}
