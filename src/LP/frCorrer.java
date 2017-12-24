package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Font;

public class frCorrer extends JFrame 
{
	private JPanel pPrincipal, pSuperior, pCentral;
	private JButton btnPause, btnFin;
	private JLabel lblMapa, lblRitmo,lblKm,lblCalorias, lblTiempo;
	
	public frCorrer() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pPrincipal = new JPanel();
		pSuperior = new JPanel();
		pCentral = new JPanel();
		
		getContentPane().add( pPrincipal );
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		getContentPane().add (pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BorderLayout(0, 0));
		
		lblMapa = new JLabel();
		lblMapa.setIcon(new ImageIcon(frCorrer.class.getResource("/img/map.jpeg")));
		lblMapa.setHorizontalAlignment(SwingConstants.CENTER);
		pCentral.add(lblMapa, BorderLayout.NORTH);
		
		lblRitmo = new JLabel("0'00\"");
		lblRitmo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblRitmo.setHorizontalAlignment(SwingConstants.CENTER);
		pCentral.add(lblRitmo, BorderLayout.WEST);
		
		lblKm = new JLabel("0");
		lblKm.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		pCentral.add(lblKm, BorderLayout.CENTER);
		
		lblCalorias = new JLabel("0");
		lblCalorias.setFont(new Font("Tahoma", Font.PLAIN, 40));
		pCentral.add(lblCalorias, BorderLayout.EAST);
		
		lblTiempo = new JLabel("0");
		lblTiempo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
		pCentral.add(lblTiempo, BorderLayout.SOUTH);
		getContentPane().add(pSuperior, BorderLayout.SOUTH);
		
		btnPause = new JButton("");
		btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/pause.png")));
		btnPause.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		btnPause.setContentAreaFilled(false); // No rellenar el área
		btnPause.setBorderPainted(false);     // No pintar el borde
		btnPause.setBorder(null);             // No considerar el borde 
		pSuperior.add(btnPause);
		
		
		btnFin = new JButton("");
		btnFin.setIcon(new ImageIcon(frCorrer.class.getResource("/img/stop.png")));
		btnFin.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		btnFin.setContentAreaFilled(false); // No rellenar el área
		btnFin.setBorderPainted(false);     // No pintar el borde
		btnFin.setBorder(null);             // No considerar el borde 
		pSuperior.add(btnFin);
		
		setSize(375,667);
		
	}

}
