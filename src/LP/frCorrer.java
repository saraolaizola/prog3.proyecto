package LP;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;

import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import LD.BD;
import LN.clsCarrera;
import LN.clsReproductor;
import LN.clsUsuario;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class frCorrer extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal, pInferior, pCentral, pTime, pRitmo, pCal, pKM,pOtros,panel;
	private JButton btnPause, btnFin;
	private JLabel lblMapa,lblTime,lblRitmo,lblCal,lblKm,ritmo,cal,km,time;
	private Integer minutos=0, segundos=0, m=0, s=0;
	private double kilometros=0.0,calorias=0.0;
	
	Thread hilo;
	Thread hiloMusica;
	boolean cronometroActivo, cronometroPlay;
	
	private ArrayList<File>lista;
//	private int cancionEnCurso=0;
	EmbeddedMediaPlayerComponent mediaPlayerComponent;
	EmbeddedMediaPlayer mediaPlayer;
	public static String path;
	File cancion;

	private clsReproductor reproductor;
	
	
	public frCorrer(clsUsuario user,ArrayList<File>list) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lista = list;
		
//		cancion = song;
		
		String vlcPath = System.getenv().get( "vlc" );
		if (vlcPath==null)
			// Poner VLC a mano <<
			System.setProperty("jna.library.path", "c:\\Archivos de programa\\videolan\\vlc-2.1.5");
		else
			System.setProperty( "jna.library.path", vlcPath );
		
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent() 
		{
			private static final long serialVersionUID = 1L;

			@Override
            protected FullScreenStrategy onGetFullScreenStrategy() 
			{
                return new Win32FullScreenStrategy(frCorrer.this);
            }
        };
        mediaPlayer = mediaPlayerComponent.getMediaPlayer();
		
		pPrincipal = new JPanel();
		pInferior = new JPanel();
		pInferior.setBackground(Color.YELLOW);
		pCentral = new JPanel();
		pCentral.setBackground(Color.YELLOW);
		
		getContentPane().add( pPrincipal, BorderLayout.NORTH );
		pPrincipal.setBackground( Color.white );
		pPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblMapa = new JLabel();
		pPrincipal.add(lblMapa);
		lblMapa.setIcon(new ImageIcon(frCorrer.class.getResource("/img/map.jpeg")));
		lblMapa.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add (pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new BorderLayout(0, 0));
		pTime = new JPanel();
		pTime.setBackground(Color.YELLOW);
		pCentral.add(pTime, BorderLayout.CENTER);
		
		lblTime = new JLabel("DURACI\u00D3N");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		pTime.setLayout(new GridLayout(0, 1, 0, 0));
		
		panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		pTime.add(panel);
		
		time = new JLabel("00:00");
		time.setVerticalTextPosition(SwingConstants.TOP);
		time.setFont(new Font("Tahoma", Font.PLAIN, 70));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setHorizontalTextPosition(SwingConstants.CENTER);
		pTime.add(time);
		pTime.add(lblTime);
		
		pOtros = new JPanel();
		pOtros.setBackground(Color.YELLOW);
		pCentral.add(pOtros, BorderLayout.SOUTH);
		pOtros.setLayout(new GridLayout(0, 3, 0, 0));
		pCal = new JPanel();
		pOtros.add(pCal);
		pCal.setBackground(Color.YELLOW);
		
		lblCal = new JLabel("CAL");
		lblCal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCal.setIcon(new ImageIcon(frCorrer.class.getResource("/img/cal.png")));
		lblCal.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCal.setHorizontalTextPosition(SwingConstants.CENTER);
		
		cal = new JLabel("0.0");
		cal.setHorizontalAlignment(SwingConstants.CENTER);
		cal.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		pCal.setLayout(new BorderLayout(0, 0));
		pCal.add(lblCal, BorderLayout.CENTER);
		pCal.add(cal, BorderLayout.SOUTH);
		
		pKM = new JPanel();
		pOtros.add(pKM);
		pKM.setBackground(Color.YELLOW);
		pKM.setLayout(new BorderLayout(0, 0));
		
		lblKm = new JLabel("KM");
		lblKm.setVerticalAlignment(SwingConstants.BOTTOM);
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		lblKm.setIcon(new ImageIcon(frCorrer.class.getResource("/img/distance.png")));
		lblKm.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblKm.setHorizontalTextPosition(SwingConstants.CENTER);
		
		pKM.add(lblKm, BorderLayout.CENTER);
		
		km = new JLabel("0.0");
		km.setFont(new Font("Tahoma", Font.PLAIN, 40));
		km.setHorizontalAlignment(SwingConstants.CENTER);
		pKM.add(km, BorderLayout.SOUTH);
		pRitmo = new JPanel();
		pOtros.add(pRitmo);
		pRitmo.setBackground(Color.YELLOW);
		
		lblRitmo = new JLabel("RITMO");
		lblRitmo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblRitmo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRitmo.setIcon(new ImageIcon(frCorrer.class.getResource("/img/ritmo.png")));
		lblRitmo.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblRitmo.setHorizontalTextPosition(SwingConstants.CENTER);
		
		ritmo = new JLabel(m+"'"+s+"''");
		ritmo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		ritmo.setHorizontalAlignment(SwingConstants.CENTER);
		pRitmo.setLayout(new BorderLayout(0, 0));
		pRitmo.add(lblRitmo, BorderLayout.CENTER);
		pRitmo.add(ritmo, BorderLayout.SOUTH);

		getContentPane().add(pInferior, BorderLayout.SOUTH);
		pInferior.setLayout(new GridLayout(0, 2, 0, 0));
		
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
		
		setSize(375,667);
		setResizable(false);
		
		cronometroActivo = true;
		cronometroPlay = true;
		hilo = new Thread(this);
		hilo.start();
		
		setVisible(true);
		
//		reproductor = new clsReproductor(lista);		
		
		// PROBLEMA: reproducir y que funcione el reloj a la vez
		// PROBLEMA: cuando acaba una cancion reproducir la siguiente
		
//		Player apl;
//		try {
//			apl = new Player(new FileInputStream(list.get(0).getAbsolutePath()));
//			apl.play();
//		} catch (FileNotFoundException | JavaLayerException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
		
		btnPause.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
		        if (cronometroPlay)
		        {
		        	cronometroPlay = false;
		        	hilo.suspend();
		        	btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/play.png")));
//		        	mediaPlayer.pause();
		        }
		        else 
		        {
		        	cronometroPlay = true;
		        	hilo.resume();
		        	btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/pause.png")));
//		        	mediaPlayer.play();
		        }
				
			}
		});
		btnFin.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				 cronometroActivo = false;
				 
				 try 
				 { 
					Date d = new Date();
					SimpleDateFormat f = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
					String fecha = f.format(d);
					 
					BD.registrarCarrera("datetime('now')",minutos+"."+segundos,calorias,kilometros,m+"."+s,user.getUsuario());
					clsCarrera carrera = new clsCarrera(fecha,minutos+"."+segundos,calorias,kilometros,m+"."+s);
				
					frDetalleCarrera ventana = new frDetalleCarrera(user,carrera);
					ventana.setVisible(true);
					dispose();
				 } 
				 catch (ClassNotFoundException e1) 
				 {
					e1.printStackTrace();
				 }
			}
		});
	}

	public void run()
	{
        String min="", seg="",calori="",kilom="";
        Random rn = new Random();
        int i=5;
        
        try
        {
            while( cronometroActivo )
            {	
            	if(cronometroPlay)
                {
                	Thread.sleep(1000);
                    
                	DecimalFormat df = new DecimalFormat("#.0");
                	
                	segundos=segundos+1;
                    calorias=calorias+0.2;
                    kilometros=kilometros+0.005;
                
                    if(segundos==i)
                	{
                    	m = rn.nextInt(4)+3;
                    	s = rn.nextInt(99);
                    	i=i+5;
                    	
                    	if (segundos==60)
                    	{
                    		segundos=0;
                        	minutos= minutos+1;
                        	i=10;
                    	}	
                	}

                	if( minutos < 10 ) min = "0" + minutos;
                	else min = minutos.toString(); 
                	
                	if( segundos < 10 ) seg = "0" + segundos;
                	else seg = segundos.toString();
                	
                	if (calorias<1.0) calori="0"+df.format(calorias);
                	else calori=df.format(calorias);
                		
                	if (kilometros<1.0) kilom="0"+df.format(kilometros);           		
                	else kilom=df.format(kilometros);   
                	
                	time.setText(min + ":" + seg);
                	time.repaint();
                	
                	ritmo.setText(m +"'"+s);
                	ritmo.repaint();
                	
                	cal.setText(calori);
                	cal.repaint();
                	
                	km.setText(kilom); 
                	km.repaint();
            	}
            }
        }
        catch(Exception e)
        {
        	
        }
    }
}
