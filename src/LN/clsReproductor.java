package LN;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Clase con el hilo del reproductor de música
 * @author ALUMNO
 *
 */
public class clsReproductor implements Runnable 
{
	
	private Player apl;
	private ArrayList<File>list;
	
	public clsReproductor(ArrayList<File>lista)
	{
		list = lista;
	}
	
	
	@Override
	public void run() 
	{
		try 
		{
			for (int i=0 ; i<list.size() ; i++)
			{
				apl = new Player(new FileInputStream(list.get(i).getAbsolutePath()));
				apl.play();
				
				System.out.println("hola");
				
				i++;
			}
		} 
		catch (FileNotFoundException | JavaLayerException | NullPointerException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void play()
	{
		try 
		{
			apl.play();
		} 
		catch (JavaLayerException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void pause()
	{
		try 
		{
			apl.wait();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
