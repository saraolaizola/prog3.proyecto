package LP;
	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import LD.BD;
import LN.clsCarrera;
import LN.clsEntrenamiento;
import LN.clsOpcEntrenamiento;
import LN.clsUsuario;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;


public class frEntrena extends JFrame implements Runnable
{

	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal, pInferior, pCentral, pTime, pCal;
	private JButton btnPause, btnFin;
	private JLabel lblVideo,lblTime,lblCal,cal,time;
	private Integer minutos, segundos=59;
	private Double calorias=0.0;
	private String fecha;
	private static Date d; 
	clsOpcEntrenamiento entrenamiento;

	boolean cronometroActivo, cronometroPlay;
	Thread hilo;

	public frEntrena(clsOpcEntrenamiento entrena, clsUsuario user) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		entrenamiento = entrena;
		
		pPrincipal = new JPanel();
		pInferior = new JPanel();
		pCentral = new JPanel();
		
		getContentPane().add( pPrincipal );
		pPrincipal.setLayout( null );
		pPrincipal.setBackground( Color.white );
		getContentPane().add (pCentral, BorderLayout.CENTER);
		pCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblVideo = new JLabel();
		lblVideo.setIcon(new ImageIcon(frCorrer.class.getResource("/img/mapa.jpeg")));
		lblVideo.setHorizontalAlignment(SwingConstants.CENTER);
		pCentral.add(lblVideo);
		pTime = new JPanel();
		pCentral.add(pTime);
		
		minutos = entrena.getDuracion()-1;
		
		lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setIcon(new ImageIcon(frCorrer.class.getResource("/img/time.png")));
		time = new JLabel(minutos+":"+segundos);
		time.setFont(new Font("Tahoma", Font.PLAIN, 40));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		pTime.setLayout(new BorderLayout(0, 0));
		pTime.add(lblTime, BorderLayout.NORTH);
		pTime.add(time, BorderLayout.SOUTH);
		pCal = new JPanel();
		pCentral.add(pCal);
		
		lblCal = new JLabel("");
		lblCal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCal.setIcon(new ImageIcon(frCorrer.class.getResource("/img/cal.png")));
		cal = new JLabel(""+calorias);
		cal.setHorizontalAlignment(SwingConstants.CENTER);
		cal.setFont(new Font("Tahoma", Font.PLAIN, 40));
		pCal.setLayout(new BorderLayout(0, 0));
		pCal.add(lblCal, BorderLayout.NORTH);
		pCal.add(cal, BorderLayout.SOUTH);

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
				 
				 SimpleDateFormat f2 = new SimpleDateFormat( "dd/MM/yyyy HH:mm:ss" );
				 fecha = f2.format(d);
				 
				 try 
				 { 
					BD.registrarEntrenamiento("datetime('now')", time.getText().replace(":", "."), calorias, entrenamiento.getCodigo(), user.getUsuario());
					clsEntrenamiento entrena = BD.getEntrenamiento(fecha); 
					
					frDetalleEntrena ventana = new frDetalleEntrena(user,entrena);
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
        String min="", seg="",calo="";
        
        try
        {
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
        	time.setText( "00:00" );
        	cal.setText("0.0");
        }
    }
}
