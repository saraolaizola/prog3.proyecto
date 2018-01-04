package LP;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class pDibujoRegistrosC extends JPanel 
{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage imagen;
	
	public pDibujoRegistrosC(int tamHor, int tamVer) 
	{
		setOpaque(false);
		imagen = new BufferedImage( tamHor, tamVer, BufferedImage.TYPE_INT_ARGB );
//		imagen = new BufferedImage( tamHor, tamVer, BufferedImage.TYPE_INT_RGB );
//		pintaFondo( Color.white );
	}
	
	public void pintaFondo( Color cF ) 
	{
		Graphics2D g2 = getGraphics();
		g2.setColor( cF );
		g2.fillRect( 0, 0, getWidth(), getHeight() );  // Rellenar el fondo
	}

	public Graphics2D getGraphics() {
		return (Graphics2D) imagen.getGraphics();
	}
	
	@Override
	protected void paintComponent(Graphics g) 
	{
		if (imagen!=null) {
			Graphics2D gr2 = (Graphics2D) g;
			gr2.drawImage(imagen, 0, 0, null);
		}
	}
	
	
	// *******************************************************************
	// Datos y métodos de dibujado especializado para el banco de pruebas
	// *******************************************************************
	
	// Atributos propios para guardar los datos que se van dibujando del banco de pruebas
//	String[] fechas;
	
//	ArrayList<Integer>duracion;
	ArrayList<Integer>atributo;
//	ArrayList<Integer>ritmo;
//	ArrayList<Double>km;
	
	ArrayList<String> fechas;
	ArrayList<Color> colores;    // Arraylist de los tamaños
	int numVariable;
	
	long max;   // Valor máximo a dibujar de duracion,cal...
	int total;   // Valor total de las carreras registradas

	Graphics2D g2;  // Objeto gráfico en el que dibujar
	int origenX;  // Origen X
	int origenY;  // Origen Y 
	int ESPACIO_IZQDA_X = 40; // Píxels libres por la izquierda
	int ESPACIO_DCHA_X = 40; // Píxels libres por la derecha
	int ESPACIO_ABAJO_Y = 20; // Píxels libres por abajo
	int RADIO_CIRCULO_PUNTO = 3;  // Píxels de radio de circulito
	int alto;   // Altura de dibujo de cada gráfico (en pixels)
	int ancho;  // Anchura de dibujo de cada gráfico (en píxels)
	int[] filasMarcadas;  // Array de filas marcadas en el dibujo
	static Stroke stroke1 = new BasicStroke(1);
	static Stroke stroke3 = new BasicStroke(3);
	static Stroke stroke5 = new BasicStroke(3);
	
	/**
	 * 
	 * @param total: numero de carreras guardadas
	 * @param fechas
	 */
	@SuppressWarnings("unchecked")
	public void iniciarDibujo( int total,ArrayList<String>fechas ) 
	{
		g2 = (Graphics2D) getGraphics();

		this.fechas=fechas;
		colores = new ArrayList<Color>();
		iniciarColores();
		
		atributo = new ArrayList<Integer>();

		max = 10;  //luego se redimensiona
		this.total = total;
		origenX = ESPACIO_IZQDA_X;
		g2.setFont( new Font("Arial", Font.PLAIN, 10) );
		
		alto = (getHeight() - ESPACIO_ABAJO_Y*2) / 2;
		origenY = getHeight() - ESPACIO_ABAJO_Y*2 - alto;
		ancho = getWidth() - origenX - ESPACIO_DCHA_X;
		redibujaTodo();
		repaint();
	}		
		
	// Se dibuja en orden por tamanyos
	public void dibujarAtributo ( int numVariable, int numCarrera, String fecha, int valor ) 
	{
		if (fechas.size() == numCarrera) fechas.add( fecha );
		
		this.numVariable = numVariable;
		
		atributo.add( valor );
		if (valor > max) 
		{
			max = (int) (valor * 1.1);
			redibujaTodo();
		}
		else 
		{
			dibujarDato( numVariable, numCarrera );
		}
	}
		
		
	// Dibuja un punto y, si procede, un segmento
	/**
	 * 
	 * @param numVariable número de la variable a mostrar
	 * @param numCarrera número de la carrera
	 */
	public void dibujarDato( int numVariable, int numCarrera ) 
	{
		g2.setColor(colores.get(numVariable));
		int x = calcX(numCarrera);
		int y = (int) calcY(atributo.get(numCarrera) );
		if (marcadaFila(numVariable))
			g2.fillOval( x-RADIO_CIRCULO_PUNTO, y-RADIO_CIRCULO_PUNTO, RADIO_CIRCULO_PUNTO*2, RADIO_CIRCULO_PUNTO*2 );
		else
			g2.drawOval( x-RADIO_CIRCULO_PUNTO, y-RADIO_CIRCULO_PUNTO, RADIO_CIRCULO_PUNTO*2, RADIO_CIRCULO_PUNTO*2 );
		if (numCarrera>0) 
		{  
			int xAnt = calcX( numCarrera-1);
			int yAnt = calcY( atributo.get(numCarrera-1) );
			if (marcadaFila(numVariable)) 
			{
				g2.setStroke( stroke5 );
				g2.drawLine( xAnt, yAnt, x, y );
				g2.setStroke( stroke1 );
			} 
			else 
			{
				g2.drawLine( xAnt, yAnt, x, y );
			}
		}
	}
		
	// Dibuja todo el gráfico de nuevo
	public void redibujaTodo() 
	{
		g2.setStroke( stroke1 );
		g2.setColor( Color.white );
		g2.fillRect( 0, 0, getWidth(), getHeight()/2 );
		dibujaEjes();
		// Dibuja primero los tamaños
		for (int numProc=0; numProc<atributo.size(); numProc++) 
		{
			for (int numTam=0; numTam<fechas.size(); numTam++) 
			{
				if (atributo.size() > numTam) 
				{   
					dibujarDato( numProc, numTam );
				}
			}
		}
		repaint();
	}

	
	public Color getColor( int numProc ) {
		return colores.get(numProc);
	}
		
	public void marcaLineas( int[] filasMarcadas ) {  // filas = 0 a n-1 de tiempo, n a 2n-1 de espacio
		this.filasMarcadas = filasMarcadas;
		redibujaTodo();
	}
		
	private boolean marcadaFila( int numFila ) 
	{
		if (filasMarcadas==null) return false;
		for (int i=0; i<filasMarcadas.length; i++)
			if (filasMarcadas[i]==numFila) return true;
		return false;
	}
		
	// Métodos utilitarios
	private static Random r = new Random();
	
	private void iniciarColores() 
	{
		colores.add( Color.blue );
		colores.add( Color.green );
		colores.add( Color.magenta );
		while (colores.size() < numVariable )
		{  // Si hay más de 3 colores se añaden aleatoriamente
			colores.add( new Color( r.nextInt(240), r.nextInt(240), r.nextInt(240) ) );  // Se llega a 240 para no usar por azar el blanco (255,255,255) que se confundiría con el fondo
		}
	}

	private int calcX( long x ) 
	{
		return origenX + (int) Math.round( ancho*x/total );
	}
		
	private int calcY( long y ) 
	{
		return origenY - (int) Math.round( alto*y/max);
	}
		
	private void dibujaEjes() 
	{
		g2.setColor( Color.black );
		g2.setStroke( stroke3 );
		g2.drawLine( calcX(0), calcY(0), calcX(total), calcY(0) );
		g2.drawLine( calcX(0), calcY(0), calcX(0), calcY(max) );
		g2.setStroke( stroke1 );
		dibujaMarcasEjes(total, 8 );
	}
		
	private void dibujaMarcasEjes( int marcasX, int marcasY ) 
	{
		// Marcas horizontales
		long espEntreMarcas = total / marcasX;
		for (int i=0; i<marcasX; i++) 
		{
			long x = espEntreMarcas * (i+1);
			g2.drawLine( calcX(x), origenY-4, calcX(x), origenY+4 );
			String texto = fechas.get(i);
			int anchoTexto = g2.getFontMetrics().stringWidth( texto );
			g2.drawString( texto, calcX(0)-anchoTexto-2, calcY(x)+5 );
		}
		
		//Marcas verticales
		espEntreMarcas = max / marcasY;
		for (int i=0; i<marcasY; i++) 
		{
			long y = espEntreMarcas * (i+1);
			g2.drawLine( origenX-4, calcY(y), origenX+4, calcY(y) );
			g2.drawString( ""+(y), calcX(y)-10, origenY+10 );
		}
	}
}
