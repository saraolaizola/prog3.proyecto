package LP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import LN.clsUsuario;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class frCarrera extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pInferior, pMenu, pCentral,panel,panel_1;
	private JButton bVolver, bEmpezar,bMusic,bVolumen;
	private JLabel lblMapa, lblRealizarCarrera;
	private File fichero, cancion;
	private boolean volumen=true;
	private static String path, ficheros;
	private static String ultimaCarpeta = null;
	private Properties misProperties;
	ArrayList<File> ficherosLista= new ArrayList<File>();
	
	public frCarrera(clsUsuario user)
	{
		// Liberaci�n de la ventana por defecto al cerrar
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
				
		// Creaci�n contenedores y componentes
		pInferior = new JPanel();
		pMenu = new JPanel();
		pCentral = new JPanel();
		pCentral.setBackground(Color.WHITE);
		
		getContentPane().add( pInferior, BorderLayout.SOUTH );
		pInferior.setBackground( Color.white );
		pInferior.setLayout(new GridLayout(0, 3, 0, 0));
		
		bVolumen = new JButton("");
		bVolumen.setHorizontalAlignment(SwingConstants.RIGHT);
		bVolumen.setIcon(new ImageIcon(frCarrera.class.getResource("/img/volumenOn.png")));
		pInferior.add(bVolumen);
		bVolumen.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		bVolumen.setContentAreaFilled(false); // No rellenar el área
		bVolumen.setBorderPainted(false);     // No pintar el borde
		bVolumen.setBorder(null);
		
		bEmpezar = new JButton();
		pInferior.add(bEmpezar);
		bEmpezar.setIcon(new ImageIcon(frCarrera.class.getResource("/img/go.png")));
		bEmpezar.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		bEmpezar.setContentAreaFilled(false); // No rellenar el área
		bEmpezar.setBorderPainted(false);     // No pintar el borde
		bEmpezar.setBorder(null);
		
		bMusic = new JButton("");
		bMusic.setHorizontalAlignment(SwingConstants.LEFT);
		bMusic.setIcon(new ImageIcon(frCarrera.class.getResource("/img/music.png")));
		pInferior.add(bMusic);
		bMusic.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		bMusic.setContentAreaFilled(false); // No rellenar el área
		bMusic.setBorderPainted(false);     // No pintar el borde
		bMusic.setBorder(null);

		getContentPane().add (pCentral, BorderLayout.CENTER);
		getContentPane().add(pMenu, BorderLayout.NORTH);
		
		bVolver = new JButton();
		bVolver.setIcon(new ImageIcon(frCarrera.class.getResource("/img/back.png")));
		bVolver.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		bVolver.setContentAreaFilled(false); // No rellenar el área
		bVolver.setBorderPainted(false);     // No pintar el borde
		bVolver.setBorder(null);    
		pCentral.setLayout(new BorderLayout(0, 0));
		 
		lblMapa = new JLabel();
		lblMapa.setIcon(new ImageIcon(frCarrera.class.getResource("/img/mapa.jpg")));
		
		lblMapa.setHorizontalAlignment(SwingConstants.CENTER);
		    	
		pCentral.add(lblMapa);
		
		pMenu.setLayout(new BorderLayout(0, 0));
		pMenu.add(bVolver, BorderLayout.WEST);
		
		lblRealizarCarrera = new JLabel("Realizar Carrera     ");
		lblRealizarCarrera.setHorizontalAlignment(SwingConstants.CENTER);
		lblRealizarCarrera.setFont(new Font("Tahoma", Font.PLAIN, 17));
		pMenu.add(lblRealizarCarrera, BorderLayout.CENTER);
		
		panel = new JPanel();
		pMenu.add(panel, BorderLayout.SOUTH);
		
		panel_1 = new JPanel();
		pMenu.add(panel_1, BorderLayout.NORTH);
		
		// Formato de ventana
		setSize(375,667);
		setResizable(false);
		
		
		bVolver.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				frPrincipal ventana = new frPrincipal (user);
				ventana.setVisible(true);
				dispose();
			}
		});
		bEmpezar.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (!volumen) ficherosLista=null;
				frCorrer ventana = new frCorrer (user,ficherosLista);
				dispose();
			}
		});
		bVolumen.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (volumen)
		        {
		        	volumen = false;
		        	bVolumen.setIcon(new ImageIcon(frCorrer.class.getResource("/img/volumenOff.png")));
		        }
		        else 
		        {
		        	volumen = true;
		        	bVolumen.setIcon(new ImageIcon(frCorrer.class.getResource("/img/volumenOn.png")));
		        }
			}
		});
		bMusic.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				File fichero = pedirCarpeta();
				if (fichero==null) return;
				else
				{
					path = fichero.getAbsolutePath();
					ultimaCarpeta = path;					
					procesaCarpeta(fichero);
				}	
			}
		});		
	}
	
	private static File pedirCarpeta() 
	{
		String carp = ultimaCarpeta;  
		if (ultimaCarpeta==null) carp = System.getProperty("user.dir");
		File dirActual = new File( carp );
		JFileChooser chooser = new JFileChooser( dirActual );
		chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		int returnVal = chooser.showOpenDialog( null );
		if (returnVal == JFileChooser.APPROVE_OPTION) 
		{
			return chooser.getSelectedFile();
		} else 
			return null;
	}
	
	private void procesaCarpeta( File fic) 
	{
		if (fic.isDirectory()) 
		{
			for( File f : fic.listFiles() ) {
				procesaCarpeta(f);
			}
		} 
		else 
		{
			String filtroFicheros = ".*\\.mp3";
			Pattern Pfic = Pattern.compile( filtroFicheros, Pattern.CASE_INSENSITIVE );
			if (Pfic.matcher(fic.getName()).matches() ) 
			{
//				fic.getAbsolutePath().endsWith(".mp3")) ficherosLista.add( fic ); 
				ficherosLista.add(fic);
			}
		}
	}
	
	public void add(String carpetaFicheros) 
	{
		if (carpetaFicheros!=null) 
		{
			try 
			{
				File fInic = new File(carpetaFicheros); 
				procesaCarpeta(fInic);
			} 
			catch (PatternSyntaxException e) 
			{
				System.out.println("Error en patr�n de expresi�n regular ");
			}
		}
	}
	
	@SuppressWarnings("unused")
	private void cargaProperties() 
	{
		misProperties = new Properties();
		try 
		{
			FileInputStream fis = new FileInputStream( new File ( "musicplayer.ini" ));
			misProperties.loadFromXML( fis );
			ultimaCarpeta = misProperties.getProperty( "ultimaCarpeta" );
			fis.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void salvaProperties() 
	{
		PrintStream ps;
		try 
		{
			ps = new PrintStream( new File( "videoplayer.ini" ) );
			if (ultimaCarpeta!=null) misProperties.setProperty( "ultimaCarpeta", ultimaCarpeta );
			misProperties.storeToXML( ps, "Run App Music" );
			ps.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
