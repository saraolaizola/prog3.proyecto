package LN;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
	
public class clsReproductor implements Runnable
{	   
	private Player apl;
	private ArrayList<File>list;
	
	public clsReproductor(ArrayList<File>lista) 
	{
		Thread hilo = new Thread (this);
		list = lista;
	}
	@Override
	public void run() 
	{
		try 
		{
			apl = new Player(new FileInputStream(list.get(0).getAbsolutePath()));
			apl.play();
			
		} 
		catch (FileNotFoundException | JavaLayerException | NullPointerException e) 
		{
			e.printStackTrace();
		}
	}
}


