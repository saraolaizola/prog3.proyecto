package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import LD.BD;
import LN.clsUsuario;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.SwingConstants;


public class frPerfil extends JFrame 
{
	private JButton bRegistro, bActividad, bPerfil;
	private JPanel pPrincipal, pCentral, pMenu;
	private JLabel foto,lblUsuario;
	private JTextField txtNombre, txtApellido;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public frPerfil(clsUsuario user) 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		pCentral = new JPanel();
		pMenu = new JPanel();
		
		getContentPane().add( pPrincipal );
		pPrincipal.setLayout( null );
		getContentPane().add (pCentral, BorderLayout.CENTER);
		getContentPane().add(pMenu, BorderLayout.SOUTH);
		pCentral.setBackground( Color.white );
		
		bRegistro = new JButton( "Registro" );
		bActividad = new JButton ("Actividad");
		bPerfil = new JButton( "Perfil" );
		
		foto = new JLabel();
		
		pMenu.add( bRegistro );
		pMenu.add( bActividad );
		pMenu.add( bPerfil );
	
		foto = new JLabel("");
		foto.setHorizontalAlignment(SwingConstants.CENTER);
		foto.setFont(new Font("Tahoma", Font.PLAIN, 35));
		foto.setIcon(new ImageIcon(frPerfil.class.getResource("/img/user.png")));
		
		lblUsuario = new JLabel(user.getUsuario());
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		txtNombre = new JTextField();
		txtNombre.setColumns(30);
		txtNombre.setText(user.getNombre());
		txtApellido = new JTextField();
		txtApellido.setColumns(30);
		txtApellido.setText(user.getApellido());
		pCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		pCentral.add(foto);
		pCentral.add(lblUsuario);
		pCentral.add(txtNombre);
		pCentral.add(txtApellido);
		
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
	}
	
	public ImageIcon createImageIcon(String path) 
	{
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) 
		{
			return new ImageIcon(imgURL);
		} 
		else 
		{
			logger.log( Level.SEVERE, "No se ha encontrado el fichero" );
			return null;
		}
	}
}
