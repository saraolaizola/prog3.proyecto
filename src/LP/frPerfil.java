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
import java.awt.SystemColor;


public class frPerfil extends JFrame 
{
	private JButton bRegistro, bActividad, bPerfil;
	private JPanel pPrincipal, pCentral, pMenu;
	private JLabel foto,lblUsuario;
	private JLabel foto_1;
	private JTextField txtNombre, txtApellido;
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private JLabel lblPerfil;
	private JPanel pDatos;
	private JPanel pFoto;
	
	public frPerfil(clsUsuario user) 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		pPrincipal.setBackground(SystemColor.menu);
		pCentral = new JPanel();
		pMenu = new JPanel();
		
		getContentPane().add( pPrincipal, BorderLayout.NORTH );
		pPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblPerfil = new JLabel("Perfil");
		lblPerfil.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pPrincipal.add(lblPerfil);
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
		pCentral.setLayout(new BorderLayout(0, 0));
		
		pFoto = new JPanel();
		pFoto.setBackground(Color.WHITE);
		pCentral.add(pFoto, BorderLayout.NORTH);
		
			foto_1 = new JLabel("");
			pFoto.add(foto_1);
			foto_1.setHorizontalAlignment(SwingConstants.CENTER);
			foto_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
			foto_1.setIcon(new ImageIcon(frPerfil.class.getResource("/img/user.png")));
		
		pDatos = new JPanel();
		pDatos.setBackground(Color.WHITE);
		pCentral.add(pDatos, BorderLayout.CENTER);
		
		lblUsuario = new JLabel(user.getUsuario());
		pDatos.add(lblUsuario);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		txtNombre = new JTextField();
		pDatos.add(txtNombre);
		txtNombre.setColumns(30);
		txtNombre.setText(user.getNombre());
		txtApellido = new JTextField();
		pDatos.add(txtApellido);
		txtApellido.setColumns(30);
		txtApellido.setText(user.getApellido());
		
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
