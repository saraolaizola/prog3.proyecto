package LP;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Font;
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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class frCorrer extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pInferior, pTime, pRitmo, pCal, pKM;
	private JButton btnPause, btnFin;
	private JLabel lblTime,lblRitmo,lblCal,lblKm,ritmo,cal,km,time;
	private Integer minutos=0, segundos=0, m=0, s=0;
	private double kilometros=0,calorias=0;
	
	Thread hilo,musica;
	boolean cronometroActivo, cronometroPlay;
	
	private ArrayList<File>lista;
	public static String path;
	File cancion;
	private JPanel pDatos;
	private JLabel label_2;
	
	public frCorrer(clsUsuario user,ArrayList<File>list) 
	{
		getContentPane().setBackground(Color.YELLOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lista = list;
//		cancion = song;
		
		String vlcPath = System.getenv().get( "vlc" );
		if (vlcPath==null)
			// Poner VLC a mano <<
			System.setProperty("jna.library.path", "c:\\Archivos de programa\\videolan\\vlc-2.1.5");
		else
			System.setProperty( "jna.library.path", vlcPath );
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		label_2 = new JLabel("");
		label_2.setBackground(Color.YELLOW);
		getContentPane().add(label_2);
		pTime = new JPanel();
		getContentPane().add(pTime);
		pTime.setBackground(Color.YELLOW);
		
		lblTime = new JLabel("DURACI\u00D3N");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		pTime.setLayout(new BorderLayout(0, 0));
		
		time = new JLabel("00:00");
		time.setVerticalAlignment(SwingConstants.TOP);
		time.setVerticalTextPosition(SwingConstants.TOP);
		time.setFont(new Font("Tahoma", Font.BOLD, 90));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		time.setHorizontalTextPosition(SwingConstants.CENTER);
		pTime.add(time, BorderLayout.CENTER);
		pTime.add(lblTime, BorderLayout.NORTH);
		
		pDatos = new JPanel();
		getContentPane().add(pDatos);
		pDatos.setLayout(new GridLayout(1, 0, 0, 0));
		pCal = new JPanel();
		pDatos.add(pCal);
		pCal.setBackground(Color.YELLOW);
		
		lblCal = new JLabel("CAL");
		lblCal.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCal.setIcon(new ImageIcon(frCorrer.class.getResource("/img/cal.png")));
		lblCal.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblCal.setHorizontalTextPosition(SwingConstants.CENTER);
		
		cal = new JLabel("0");
		cal.setHorizontalAlignment(SwingConstants.CENTER);
		cal.setFont(new Font("Tahoma", Font.PLAIN, 40));
		pCal.setLayout(new GridLayout(0, 1, 0, 0));
		pCal.add(lblCal);
		pCal.add(cal);
		
		pKM = new JPanel();
		pDatos.add(pKM);
		pKM.setBackground(Color.YELLOW);
		pKM.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblKm = new JLabel("KM");
		lblKm.setVerticalAlignment(SwingConstants.BOTTOM);
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		lblKm.setIcon(new ImageIcon(frCorrer.class.getResource("/img/distance.png")));
		lblKm.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblKm.setHorizontalTextPosition(SwingConstants.CENTER);
		
		pKM.add(lblKm);
		
		km = new JLabel("0");
		km.setFont(new Font("Tahoma", Font.PLAIN, 40));
		km.setHorizontalAlignment(SwingConstants.CENTER);
		pKM.add(km);
		pRitmo = new JPanel();
		pDatos.add(pRitmo);
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
		pRitmo.setLayout(new GridLayout(0, 1, 0, 0));
		pRitmo.add(lblRitmo);
		pRitmo.add(ritmo);
		pInferior = new JPanel();
		getContentPane().add(pInferior);
		pInferior.setBackground(Color.YELLOW);
		
		btnPause = new JButton("");
		btnPause.setHorizontalAlignment(SwingConstants.RIGHT);
		btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/pause2.png")));
		btnPause.setOpaque(false);            
		btnPause.setContentAreaFilled(false); 
		btnPause.setBorderPainted(false);     
		btnPause.setBorder(null);             
		
		btnFin = new JButton("");
		btnFin.setHorizontalAlignment(SwingConstants.LEFT);
		btnFin.setIcon(new ImageIcon(frCorrer.class.getResource("/img/stop2.png")));
		btnFin.setOpaque(false);           
		btnFin.setContentAreaFilled(false); 
		btnFin.setBorderPainted(false);     
		btnFin.setBorder(null);
		
		GroupLayout gl_pInferior = new GroupLayout(pInferior);
		gl_pInferior.setHorizontalGroup(
			gl_pInferior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pInferior.createSequentialGroup()
					.addGap(94)
					.addComponent(btnPause, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(56)
					.addComponent(btnFin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(97))
		);
		gl_pInferior.setVerticalGroup(
			gl_pInferior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pInferior.createSequentialGroup()
					.addGap(36)
					.addGroup(gl_pInferior.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFin)
						.addComponent(btnPause))
					.addContainerGap(62, Short.MAX_VALUE))
		);
		pInferior.setLayout(gl_pInferior);

		setSize(375,667);
		setResizable(false);
		
		cronometroActivo = true;
		cronometroPlay = true;
		
		hilo = new Thread(this);
		hilo.start();
		
		clsReproductor reproductor = new clsReproductor(lista);
		musica = new Thread(reproductor);
		musica.start();
		
		setVisible(true);
		
		btnPause.addActionListener( new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) 
			{
		        if (cronometroPlay)
		        {
		        	cronometroPlay = false;
		        	hilo.suspend();
		        	musica.suspend();
		        	btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/play2.png")));
		        }
		        else 
		        {
		        	cronometroPlay = true;
		        	hilo.resume();
		        	musica.resume();
		        	btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/pause2.png")));
		        }
				
			}
		});
		btnFin.addActionListener( new ActionListener() 
		{
			@SuppressWarnings("deprecation")
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
				
					hilo.stop();
					musica.stop();
					
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
                    
                	DecimalFormat df = new DecimalFormat("#.##");
                	
                	segundos=segundos+1;
                    calorias=calorias+0.2;
                    kilometros=kilometros+0.0033;
                    
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
                	
                	calori=df.format(calorias);
                		
                	kilom=df.format(kilometros);   
                	
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
        catch(Exception e){
        }
    }
}
