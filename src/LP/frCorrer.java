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
import LN.clsUsuario;

public class frCorrer extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private JPanel pPrincipal, pSuperior, pCentral, pTime, pRitmo, pCal, pKM;
	private JButton btnPause, btnFin;
	private JLabel lblMapa,lblTime,lblRitmo,lblCal,lblKm,ritmo,cal,km,time;
	private Integer minutos=0 , segundos=0, milesimas=0, calorias=0, m=0, s=0;
	private double kilometros=0;
	private String fecha;
	private static Date d; 
	
	boolean cronometroActivo;
	Thread hilo;
	
	public frCorrer(clsUsuario user) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pPrincipal = new JPanel();
		pSuperior = new JPanel();
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
		ritmo = new JLabel("0'00\"");
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
		time = new JLabel("00:00");
		time.setFont(new Font("Tahoma", Font.PLAIN, 40));
		time.setHorizontalAlignment(SwingConstants.CENTER);
		pTime.add(lblTime);
		pTime.add(time);
		
		lblMapa = new JLabel();
		lblMapa.setIcon(new ImageIcon(frCorrer.class.getResource("/img/map.jpeg")));
		lblMapa.setHorizontalAlignment(SwingConstants.CENTER);
		pCentral.add(lblMapa, BorderLayout.NORTH);
		getContentPane().add(pSuperior, BorderLayout.SOUTH);
		
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
		
		pSuperior.add(btnPause);
		pSuperior.add(btnFin);
		
		setSize(375,667);
		
		btnFin.addActionListener( new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				 cronometroActivo = false;
				 
				 SimpleDateFormat f2 = new SimpleDateFormat( "dd/MM/yyyy HH:mm" );
				 fecha = f2.format(d);
				 
				 try 
				 {
					BD.registrarCarrera(fecha, time.getText(),calorias,kilometros,ritmo.getText(),user.getUsuario());
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
				cronometroActivo = true;
		        hilo = new Thread();
		        hilo.start();
		        d = new Date();
			}
		});
	}
	
	

	public void run()
	{
        String min="", seg="";
        Random rn = new Random();
        
        try
        {
            while( cronometroActivo )
            {
                Thread.sleep( 4 );
                milesimas += 4;

                if( milesimas == 1000 )
                {
                    milesimas = 0;
                    segundos += 1;
                   
                    if( segundos == 60 )
                    {
                        segundos = 0;
                        minutos++;
                        calorias=calorias+7;
                        m = rn.nextInt(5)+2;
                        s = rn.nextInt(100);
                        kilometros=minutos/m;
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
                ritmo.setText(m +"'"+s+"''");
                cal.setText(calorias.toString());
                km.setText(Double.toString(kilometros));        
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
