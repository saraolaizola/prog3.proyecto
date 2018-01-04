package LP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import LD.BD;
import LN.clsEntrenamiento;
import LN.clsOpcEntrenamiento;
import LN.clsUsuario;

import java.awt.GridLayout;


public class frEntrena extends JFrame implements Runnable
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pSuperior, pInferior, pCentral;
	private JButton btnPause, btnFin;
	private JLabel cal,time,nombre; //lblVideo
	private Integer minutos, segundos=59;
	private Double calorias=0.0;
	clsOpcEntrenamiento entrenamiento;

	EmbeddedMediaPlayerComponent mediaPlayerComponent;
	EmbeddedMediaPlayer mediaPlayer;
	public static String path;
	
	boolean cronometroActivo, cronometroPlay;
	Thread hilo;
	private JPanel pDatos;

	public frEntrena(clsOpcEntrenamiento entrena, clsUsuario user) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		entrenamiento = entrena;
		path = entrena.getFile().getAbsolutePath();
		
		String vlcPath = System.getenv().get( "vlc" );
    	if (vlcPath==null)
			// Poner VLC a mano <<
			System.setProperty("jna.library.path", "c:\\Archivos de programa\\videolan\\vlc-2.1.5");
		else
			System.setProperty( "jna.library.path", vlcPath );
		
		pInferior = new JPanel();
		pCentral = new JPanel();
		pSuperior = new JPanel();
		pDatos = new JPanel();
		
		pCentral.setBackground(Color.WHITE);
		pCentral.setLayout(new BorderLayout(0, 0));
		pCentral.add(pDatos, BorderLayout.SOUTH);
		
		getContentPane().add (pCentral, BorderLayout.CENTER);	
		
		pDatos.setBackground(Color.WHITE);
		pDatos.setLayout(new GridLayout(0, 2, 0, 0));
		
		getContentPane().add(pSuperior, BorderLayout.NORTH);
		
		nombre = new JLabel (entrena.getNombre());
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		pSuperior.add(nombre);
		
		time = new JLabel(minutos+":"+segundos);
		time.setFont(new Font("Tahoma", Font.PLAIN, 40));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setIcon(new ImageIcon(frCorrer.class.getResource("/img/time.png")));
		time.setVerticalTextPosition(SwingConstants.BOTTOM);
		time.setHorizontalTextPosition(SwingConstants.CENTER);
		time.setBackground(Color.WHITE);
		pDatos.add(time);
		
		cal = new JLabel(""+calorias);
		cal.setHorizontalAlignment(SwingConstants.CENTER);
		cal.setFont(new Font("Tahoma", Font.PLAIN, 40));
		cal.setIcon(new ImageIcon(frCorrer.class.getResource("/img/cal.png")));
		cal.setVerticalTextPosition(SwingConstants.BOTTOM);
		cal.setHorizontalTextPosition(SwingConstants.CENTER);
		cal.setBackground(Color.WHITE);
		pDatos.add(cal);
		
		minutos = entrena.getDuracion()-1;

		getContentPane().add(pInferior, BorderLayout.SOUTH);
		
		btnPause = new JButton("");
		btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/pause.png")));
		btnPause.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		btnPause.setContentAreaFilled(false); // No rellenar el área
		btnPause.setBorderPainted(false);     // No pintar el borde
		btnPause.setBorder(null);             // No considerar el borde 
		
		btnFin = new JButton("");
		btnFin.setIcon(new ImageIcon(frCorrer.class.getResource("/img/stop.png")));
		btnFin.setOpaque(false);            // Fondo Transparente (los gráficos son png transparentes)
		btnFin.setContentAreaFilled(false); // No rellenar el área
		btnFin.setBorderPainted(false);     // No pintar el borde
		btnFin.setBorder(null);             // No considerar el borde 
		
		pInferior.add(btnPause);
		pInferior.add(btnFin);
		
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent() 
		{
			private static final long serialVersionUID = 1L;

			@Override
            protected FullScreenStrategy onGetFullScreenStrategy() 
			{
                return new Win32FullScreenStrategy(frEntrena.this);
            }
        };
        mediaPlayer = mediaPlayerComponent.getMediaPlayer();
        pCentral.add(mediaPlayerComponent, BorderLayout.CENTER);
        
        setSize(375,667);
		setResizable(false);
		
		setVisible(true);
		cronometroActivo = true;
		cronometroPlay = true;
		hilo = new Thread(this);
		hilo.start();
		
		btnFin.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				 cronometroActivo = false;
				 
				 try 
				 { 
					String tiempo = (entrena.getDuracion()-minutos)+"."+(60-segundos);
					 
					BD.registrarEntrenamiento("datetime('now')",tiempo, calorias, entrenamiento.getCodigo(), user.getUsuario());
					ArrayList <clsEntrenamiento> entrenas = BD.getMisEntrenamientos(user.getUsuario());
					clsEntrenamiento entrena = entrenas.get(entrenas.size()-1);
					
					frDetalleEntrena ventana = new frDetalleEntrena(user,entrena);
					ventana.setVisible(true);
					dispose();
				 } 
				 catch (ClassNotFoundException e1) 
				 {
					e1.printStackTrace();
				 }
				 catch (ArrayIndexOutOfBoundsException e2)
				 {
					 clsEntrenamiento entrena = BD.getMisEntrenamientos(user.getUsuario()).get(0);
					 frDetalleEntrena ventana = new frDetalleEntrena(user,entrena);
					 ventana.setVisible(true);
					 dispose();
				 }
			}
		});
		btnPause.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
		        if (cronometroPlay)
		        {
		        	cronometroPlay = false;
		        	hilo.suspend();
		        	mediaPlayer.pause();
		        	btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/play.png")));
		        }
		        else 
		        {
		        	cronometroPlay = true;
		        	hilo.resume();
		        	mediaPlayer.play();
		        	btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/pause.png")));
		        }
				
			}
		});
	}
	
	

	public void run()
	{
        String min="", seg="",calo="";
        
        try
        {
        	if(mediaPlayer.isPlayable())
        	{
        		mediaPlayer.play();
        	}
        	else
        	{
        		if (mediaPlayer!=null) 
        		{
        			mediaPlayer.playMedia(path);
        		}
        	}
        	
//        	if (mediaPlayer.isPlayable()) 
//        	{
//				if (mediaPlayer.isPlaying())
//					mediaPlayer.pause();
//				else
//					mediaPlayer.play();
//			} 
//        	else 
//        	{
//				lanzaVideo();
//			}
        	
        	
            while( cronometroActivo )
            {
                if (cronometroPlay)
                {
                	Thread.sleep( 1000 );
                	
                	DecimalFormat df = new DecimalFormat("#.0");
                	
                    segundos=segundos-1;
                    calorias=calorias+entrenamiento.getCalxsec();
                       
                    if( segundos == 00 )
                    {
                    	segundos = 59;
                    	minutos=minutos-1;
                    }
                    
                    if(minutos<10) min = "0" + minutos;
                    else min = minutos.toString();
                    
                    if(segundos<10 ) seg = "0" + segundos;
                    else seg = segundos.toString();
                  
                    if (calorias<1.0) calo="0"+df.format(calorias);
                	else calo=df.format(calorias);
                    
                    time.setText(min + ":" + seg);
                    time.repaint();
                    cal.setText(calo);
                    cal.repaint();
                }
            }       
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	System.out.println("problema");
        }
    }
}
