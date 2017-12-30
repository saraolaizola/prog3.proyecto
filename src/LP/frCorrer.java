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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import LD.BD;
import LN.clsCarrera;
import LN.clsUsuario;
import java.awt.GridLayout;

public class frCorrer extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal, pInferior, pCentral, pTime, pRitmo, pCal, pKM;
	private JButton btnPause, btnFin;
	private JLabel lblMapa,lblTime,lblRitmo,lblCal,lblKm,ritmo,cal,km,time;
	private Integer minutos=0, segundos=0, m=0, s=0;
	private double kilometros=0.0,calorias=0.0;
	private String fecha;
	private static Date d; 
	
	boolean cronometroActivo, cronometroPlay;
	Thread hilo;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_3;
	
	public frCorrer(clsUsuario user) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pPrincipal = new JPanel();
		pInferior = new JPanel();
		pInferior.setBackground(Color.WHITE);
		pCentral = new JPanel();
		pCentral.setBackground(Color.WHITE);
		
		getContentPane().add( pPrincipal, BorderLayout.NORTH );
		pPrincipal.setBackground( Color.white );
		pPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblMapa = new JLabel();
		pPrincipal.add(lblMapa);
		lblMapa.setIcon(new ImageIcon(frCorrer.class.getResource("/img/mapa.jpeg")));
		lblMapa.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add (pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new GridLayout(0, 3, 0, 0));
		
		label = new JLabel("");
		pCentral.add(label);
		pTime = new JPanel();
		pTime.setBackground(Color.WHITE);
		pCentral.add(pTime);
		
		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setIcon(new ImageIcon(frCorrer.class.getResource("/img/time.png")));
		time = new JLabel("00:00");
		time.setFont(new Font("Tahoma", Font.PLAIN, 40));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		pTime.setLayout(new BorderLayout(0, 0));
		pTime.add(lblTime, BorderLayout.NORTH);
		pTime.add(time, BorderLayout.CENTER);
		
		label_1 = new JLabel("");
		pCentral.add(label_1);
		pCal = new JPanel();
		pCal.setBackground(Color.WHITE);
		pCentral.add(pCal);
		
		lblCal = new JLabel("");
		lblCal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCal.setIcon(new ImageIcon(frCorrer.class.getResource("/img/cal.png")));
		cal = new JLabel("0.0");
		cal.setHorizontalAlignment(SwingConstants.CENTER);
		cal.setFont(new Font("Tahoma", Font.PLAIN, 40));
		pCal.setLayout(new BorderLayout(0, 0));
		pCal.add(lblCal, BorderLayout.CENTER);
		pCal.add(cal, BorderLayout.SOUTH);
		pKM = new JPanel();
		pKM.setBackground(Color.WHITE);
		pCentral.add(pKM);
		pKM.setLayout(new BorderLayout(0, 0));
		lblKm = new JLabel("");
		lblKm.setHorizontalAlignment(SwingConstants.CENTER);
		lblKm.setIcon(new ImageIcon(frCorrer.class.getResource("/img/distance.png")));
		pKM.add(lblKm, BorderLayout.CENTER);
		
		km = new JLabel("0.0");
		km.setFont(new Font("Tahoma", Font.PLAIN, 40));
		km.setHorizontalAlignment(SwingConstants.CENTER);
		pKM.add(km, BorderLayout.SOUTH);
		pRitmo = new JPanel();
		pRitmo.setBackground(Color.WHITE);
		pCentral.add(pRitmo);
		lblRitmo = new JLabel("");
		lblRitmo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRitmo.setIcon(new ImageIcon(frCorrer.class.getResource("/img/ritmo.png")));
		ritmo = new JLabel(m+"'"+s+"''");
		ritmo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		ritmo.setHorizontalAlignment(SwingConstants.CENTER);
		pRitmo.setLayout(new BorderLayout(0, 0));
		pRitmo.add(lblRitmo, BorderLayout.CENTER);
		pRitmo.add(ritmo, BorderLayout.SOUTH);
		
		label_3 = new JLabel("");
		pCentral.add(label_3);

        d = new Date();
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
		pInferior.setLayout(new GridLayout(0, 2, 0, 0));
		
		pInferior.add(btnPause);
		pInferior.add(btnFin);
		
		setSize(375,667);
		setResizable(false);
		
		cronometroActivo = true;
		cronometroPlay = true;
		hilo = new Thread(this);
		hilo.start();
		setVisible(true);
		
		btnFin.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				 cronometroActivo = false;
				 
				 SimpleDateFormat f2 = new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" );
				 fecha = f2.format(d);
	
				 try 
				 { 
					BD.registrarCarrera("datetime('now')",minutos+"."+segundos,calorias,kilometros,m+"."+s,user.getUsuario());
					System.out.println(minutos+"."+segundos);
					System.out.println(m+"."+s);
					clsCarrera carrera = BD.getCarrera(fecha); 
					
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
		        }
		        else 
		        {
		        	cronometroPlay = true;
		        	hilo.resume();
		        	btnPause.setIcon(new ImageIcon(frCorrer.class.getResource("/img/pause.png")));
		        }
				
			}
		});
	}

	public void run()
	{
        String min="", seg="",calori="",kilom="";
        Random rn = new Random();
        int i=10;
        
        try
        {
            while( cronometroActivo )
            {
                if(cronometroPlay)
                {
                	Thread.sleep(1000);
                    
                	DecimalFormat df = new DecimalFormat("#.0");
                	
                	segundos=segundos+1;
                    calorias=calorias+0.1;
                	if(segundos==i)
                	{
                    	m = rn.nextInt(4)+3;
                    	s = rn.nextInt(99);
                    	kilometros=kilometros+0.2;
                    	kilom=df.format(kilometros);
                    	i=i+10;
                    	
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
        	time.setText( "00:00" );
        	ritmo.setText("0'00''");
        	cal.setText("0");
        	km.setText("0");
        }
    }
}
