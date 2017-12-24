package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import LN.clsUsuario;

public class frCarrera extends JFrame 
{
	JPanel pPrincipal, pMenu, pCentral;
	JButton bVolver, bEmpezar;
	JLabel lblMapa;
	
	public frCarrera(clsUsuario user)
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
				getContentPane().add(pMenu, BorderLayout.NORTH);
				
				bVolver = new JButton();
				bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
				bVolver.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
				bVolver.setContentAreaFilled(false); // No rellenar el área
				bVolver.setBorderPainted(false);     // No pintar el borde
				bVolver.setBorder(null);    
				
				bEmpezar = new JButton();
				bEmpezar.setIcon(new ImageIcon(frCarrera.class.getResource("/img/start.png")));
				bEmpezar.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
				bEmpezar.setContentAreaFilled(false); // No rellenar el área
				bEmpezar.setBorderPainted(false);     // No pintar el borde
				bEmpezar.setBorder(null);             // No considerar el borde 
				 
				lblMapa = new JLabel();
				lblMapa.setIcon(new ImageIcon(frCarrera.class.getResource("/img/map.jpeg")));
				
				lblMapa.setHorizontalAlignment(SwingConstants.CENTER);
				    	
				pCentral.add(lblMapa, BorderLayout.NORTH);
				pCentral.add(bEmpezar, BorderLayout.SOUTH); 
				
				pMenu.setLayout(new BorderLayout(0, 0));
				pMenu.add(bVolver, BorderLayout.WEST);
				
				// Formato de ventana
				setSize(375,667);
				
				
				bVolver.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frPrincipal ventana = new frPrincipal (user);
						ventana.setVisible(true);
						dispose();
					}
				});
				bEmpezar.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						frCorrer ventana = new frCorrer ();
						ventana.setVisible(true);
						dispose();
					}
				});
	}
	
	

}
