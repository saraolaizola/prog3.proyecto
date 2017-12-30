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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import LD.BD;
import LN.clsCarrera;
import LN.clsUsuario;

public class frCorrer extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal, pInferior, pCentral, pTime, pRitmo, pCal, pKM;
	private JButton btnPause, btnFin;
	private JLabel lblMapa,lblTime,lblRitmo,lblCal,lblKm,ritmo,cal,km,time;
	private Integer minutos=0, segundos=0,calorias=0, m=0, s=0;
	private double kilometros=0;
	private String fecha;
	private static Date d; 
	
	boolean cronometroActivo, cronometroPlay;
	Thread hilo;
	
	public frCorrer(clsUsuario user) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pPrincipal = new JPanel();
		pInferior = new JPanel();
		pCentral = new JPanel();
		pRitmo = new JPanel();
		pTime = new JPanel();
		pCal = new JPanel();
		pKM = new JPanel();
		
		getContentPane().add( pPrincipal );
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		getContentPane().add (pCentral, BorderLayout.CENTER);
		
		pCentral.setLayout(new BorderLayout(0, 0));
		pCentral.add(pTime, BorderLayout.CENTER);
		pCentral.add(pRitmo, BorderLayout.WEST);
		pCentral.add(pCal, BorderLayout.EAST);
		pCentral.add(pKM, BorderLayout.SOUTH);
		
		km = new JLabel("0");
		km.setFont(new Font("Tahoma", Font.PLAIN, 40));
		km.setHorizontalAlignment(SwingConstants.CENTER);
		pKM.add(km);
		lblKm = new JLabel("KM");
		pKM.add(lblKm);
		
		pRitmo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblRitmo = new JLabel("");
		lblRitmo.setIcon(new ImageIcon(frCorrer.class.getResource("/img/ritmo.png")));
		ritmo = new JLabel(m+"'"+s+"''");
		ritmo.setFont(new Font("Tahoma", Font.PLAIN, 40));
		ritmo.setHorizontalAlignment(SwingConstants.CENTER);
		pRitmo.add(lblRitmo);
		pRitmo.add(ritmo);
		
		lblCal = new JLabel("");
		lblCal.setIcon(new ImageIcon(frCorrer.class.getResource("/img/cal.png")));
		cal = new JLabel("00");
		cal.setFont(new Font("Tahoma", Font.PLAIN, 40));
		pTime.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pCal.add(lblCal);
		pCal.add(cal);
		
		lblTime = new JLabel("");
		lblTime.setIcon(new ImageIcon(frCorrer.class.getResource("/img/time.png")));
		time = new JLabel(minutos+":"+segundos);
		time.setFont(new Font("Tahoma", Font.PLAIN, 40));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		pTime.add(lblTime);
		pTime.add(time);

        d = new Date();
		
		lblMapa = new JLabel();
		lblMapa.setIcon(new ImageIcon(frCorrer.class.getResource("/img/map.jpeg")));
		lblMapa.setHorizontalAlignment(SwingConstants.CENTER);
		pCentral.add(lblMapa, BorderLayout.NORTH);
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
//					 String TIME;
//					 if (minutos==0) TIME = "00.";
//					 else if (minutos<10) TIME = "0"+minutos.toString()+".";
//					 else TIME = minutos.toString()+".";
//					 
//					 if (segundos<10) TIME = TIME+"0"+segundos;
//					 else TIME = TIME + segundos.toString();
//					 
//					 String RITMO;
//					 if (m==0) RITMO = "0.00";
//					 else if (s<10) RITMO = m+".0"+s;
//					 else RITMO = m+"."+s;
					 
					BD.registrarCarrera("datetime('now')",lblTime.getText().replace(":", "."),calorias.intValue(),kilometros,lblRitmo.getText().replace("'", "."),user.getUsuario());
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
        String min="", seg="";
        Random rn = new Random();
        int i=10;
        
        try
        {
            while( cronometroActivo )
            {
                if(cronometroPlay)
                {
                	Thread.sleep(1000);
                    
                	segundos=segundos+1;
                    
                	if(segundos==i)
                	{
                		calorias=calorias+1;
                    	m = rn.nextInt(4)+3;
                    	s = rn.nextInt(99);
                    	kilometros=kilometros+0.2;
                    	i=i+10;
                    	
                    	if (segundos==60)
                    	{
                    		segundos=0;
                        	minutos= minutos+1;
                        	i=10;
                    	}	
                	}

                	if( minutos < 10 ) 
                	{
                		min = "0" + minutos;
                	}
                	else 
                	{
                		min = minutos.toString();
                	}
                    
                	if( segundos < 10 ) 
                	{
               			seg = "0" + segundos;
                	}
                	else 
                	{
                		seg = segundos.toString();
                	}
                    
                	time.setText(min + ":" + seg);
                	time.repaint();
                	ritmo.setText(m +"'"+s);
                	ritmo.repaint();
                	cal.setText(calorias.toString());
                	cal.repaint();
                	km.setText(""+kilometros); 
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
