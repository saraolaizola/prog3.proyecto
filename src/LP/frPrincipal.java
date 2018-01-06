package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import LN.clsUsuario;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;

/**
 * Pantalla principal de la applicación. 
 * El usuario decide que actividad quiere hacer (entrenar o correr) y le lleva
 * a lapantalla correspondiente
 * @author ALUMNO
 *
 */
public class frPrincipal extends JFrame 
{
	JPanel pPrincipal, pMenu;
	JButton bRegistro,bActividad,bPerfil, bEmpezar, bCorrer, bEntrenar;
	private JPanel pSuperior;
	private JLabel lblActividad;
	private JPanel panel;
	private JPanel panel_1;
	
	public frPrincipal(clsUsuario user) 
	{
		// Liberación de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setBackground(Color.white);
		
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		pPrincipal.setBackground(Color.white);
		pMenu = new JPanel();
		
		pSuperior = new JPanel();
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		pSuperior.setLayout(new BorderLayout(0, 0));
		
		lblActividad = new JLabel("Actividad");
		lblActividad.setHorizontalAlignment(SwingConstants.CENTER);
		lblActividad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pSuperior.add(lblActividad);
		
		panel = new JPanel();
		pSuperior.add(panel, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		pSuperior.add(panel_1, BorderLayout.SOUTH);
		
		getContentPane().add( pPrincipal );
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		getContentPane().add(pMenu, BorderLayout.SOUTH);
		
		bEntrenar = new JButton();
		bEntrenar.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/entrenar.jpg")));
		bEntrenar.setHorizontalAlignment(SwingConstants.CENTER);
		bEntrenar.setOpaque(false);            // Fondo Transparente (los grÃ¡ficos son png transparentes)
		bEntrenar.setContentAreaFilled(false); // No rellenar el Ã¡rea
		bEntrenar.setBorderPainted(false);     // No pintar el borde
		bEntrenar.setBorder(null);  
		
		bCorrer = new JButton();
		bCorrer.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/correr.jpg")));
		bCorrer.setHorizontalAlignment(SwingConstants.CENTER);
		bCorrer.setOpaque(false);            // Fondo Transparente (los grÃ¡ficos son png transparentes)
		bCorrer.setContentAreaFilled(false); // No rellenar el Ã¡rea
		bCorrer.setBorderPainted(false);     // No pintar el borde
		bCorrer.setBorder(null);  
		
		pPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pPrincipal.add(bCorrer);
		pPrincipal.add(bEntrenar);
		
		bRegistro = new JButton( "Registros" );
		bRegistro.setBackground(SystemColor.menu);
		bRegistro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		bRegistro.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/registro.png")));
		bRegistro.setVerticalTextPosition(SwingConstants.BOTTOM);
		bRegistro.setHorizontalTextPosition(SwingConstants.CENTER);
		bRegistro.setBorderPainted(false);
		
		bActividad = new JButton ("Actividad");
		bActividad.setBackground(new Color(240, 240, 240));
		bActividad.setFont(new Font("Tahoma", Font.BOLD, 9));
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
		
		pMenu.setLayout(new GridLayout(0, 3, 0, 0));
	
		// Añadido de componentes a contenedores
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
				frCarrera ventana = new frCarrera (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		bEntrenar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frElegirEntrena ventana = new frElegirEntrena (user);
				ventana.setVisible(true);
				dispose();
			}
		});
	}
}

