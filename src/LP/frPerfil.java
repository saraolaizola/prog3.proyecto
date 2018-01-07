package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import LD.BD;
import LN.clsUsuario;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class frPerfil extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JButton bRegistro, bActividad, bPerfil, bCerrarSesion;
	private JPanel pPrincipal, pCentral, pMenu, pDatos, pFoto,panel, panel_1,panel_2;
	private JLabel lblUsuario,lblPerfil,foto;
	private JTextField txtNombre, txtApellido;
	private clsUsuario usuario;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public frPerfil(clsUsuario user) 
	{
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		usuario = user;
		
		// Creación contenedores y componentes
		pPrincipal = new JPanel();
		pPrincipal.setBackground(SystemColor.menu);
		pCentral = new JPanel();
		pMenu = new JPanel();
		
		getContentPane().add( pPrincipal, BorderLayout.NORTH );
		pPrincipal.setLayout(new BorderLayout(0, 0));
		
		lblPerfil = new JLabel("Perfil");
		lblPerfil.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerfil.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pPrincipal.add(lblPerfil);
		
		panel = new JPanel();
		pPrincipal.add(panel, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		pPrincipal.add(panel_1, BorderLayout.SOUTH);
		getContentPane().add (pCentral, BorderLayout.CENTER);
		getContentPane().add(pMenu, BorderLayout.SOUTH);
		pCentral.setBackground( Color.white );
		
		bRegistro = new JButton( "Registros" );
		bRegistro.setBackground(SystemColor.menu);
		bRegistro.setFont(new Font("Tahoma", Font.PLAIN, 9));
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
		bPerfil.setFont(new Font("Tahoma", Font.BOLD, 9));
		bPerfil.setIcon(new ImageIcon(frPrincipal.class.getResource("/img/perfil.png")));
		bPerfil.setVerticalTextPosition(SwingConstants.BOTTOM);
		bPerfil.setHorizontalTextPosition(SwingConstants.CENTER);
		bPerfil.setBorderPainted(false); 
		
		pMenu.add( bRegistro );
		pMenu.add( bActividad );
		pMenu.add( bPerfil );
		
		pMenu.setLayout(new GridLayout(0, 3, 0, 0));
		pCentral.setLayout(new BorderLayout(0, 0));
		
		pFoto = new JPanel();
		pFoto.setBackground(Color.WHITE);
		pCentral.add(pFoto, BorderLayout.NORTH);
		pFoto.setLayout(new BorderLayout(0, 0));
		
		foto = new JLabel("");
		pFoto.add(foto, BorderLayout.CENTER);
		foto.setHorizontalAlignment(SwingConstants.CENTER);
		foto.setFont(new Font("Tahoma", Font.PLAIN, 35));
		foto.setIcon(new ImageIcon(frPerfil.class.getResource("/img/user.png")));
			
		lblUsuario = new JLabel(user.getUsuario());
		pFoto.add(lblUsuario, BorderLayout.SOUTH);
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		pFoto.add(panel_2, BorderLayout.NORTH);
		
		pDatos = new JPanel();
		pDatos.setBackground(Color.WHITE);
		pCentral.add(pDatos, BorderLayout.CENTER);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(30);
		txtNombre.setText(user.getNombre());
		txtApellido = new JTextField();
		txtApellido.setColumns(30);
		txtApellido.setText(user.getApellido());
		
		bCerrarSesion = new JButton("Cerrar Sesi\u00F3n");
		bCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bCerrarSesion.setForeground(new Color(255, 0, 0));
		bCerrarSesion.setBackground(SystemColor.menu);
		//bCerrarSesion.border RED
		
		GroupLayout gl_pDatos = new GroupLayout(pDatos);
		gl_pDatos.setHorizontalGroup(
			gl_pDatos.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pDatos.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_pDatos.createParallelGroup(Alignment.TRAILING)
						.addComponent(bCerrarSesion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
						.addComponent(txtApellido, Alignment.LEADING, 293, 293, Short.MAX_VALUE)
						.addComponent(txtNombre, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
					.addGap(38))
		);
		gl_pDatos.setVerticalGroup(
			gl_pDatos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pDatos.createSequentialGroup()
					.addGap(23)
					.addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtApellido, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(bCerrarSesion, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(211))
		);
		pDatos.setLayout(gl_pDatos);
		
		// Formato de ventana
		setSize(375,667);
		setResizable(false);
		
		// Escuchadores de botones
		bRegistro.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				guardar();
				frLista ventana = new frLista (usuario);
				ventana.setVisible(true);
				dispose();
			}
		});
		bActividad.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				guardar();
				frPrincipal ventana = new frPrincipal (usuario);
				ventana.setVisible(true);
				dispose();
			}
		});
		bPerfil.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				guardar();
				frPerfil ventana = new frPerfil (usuario);
				ventana.setVisible(true);
				dispose();
			}
		});
		bCerrarSesion.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				guardar();
				frInicio ventana = new frInicio ();
				ventana.setVisible(true);
				dispose();
			}
		});
	}
	
	public void guardar()
	{
		BD.editarUsuario(usuario.getUsuario(),txtNombre.getText().toUpperCase(), txtApellido.getText().toUpperCase());
		usuario=BD.getUser(usuario.getUsuario());
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
