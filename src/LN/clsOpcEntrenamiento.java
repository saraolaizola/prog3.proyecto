package LN;

import COMUN.clsOpcEntrenamientoRepetida;
import LD.BD;;

public class clsOpcEntrenamiento 
{
	private String codigo;
	private String nombre;
	private int duracion;
	private double calxmin;	
	public enum Nivel {Principiante, Intermedio, Experto}
	
	
	public clsOpcEntrenamiento(String codigo, String nombre, int duracion,double calxmin) 
	{
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.calxmin = calxmin;
	}

	

	public void meterOpciones()
	{
		try 
		{
			BD.registrarOpcEntrenamiento("0001", "Abdominales", Nivel.Principiante, 5, 10);
			//https://www.youtube.com/watch?v=1919eTCoESo&list=PL6070A835F843D79F
			BD.registrarOpcEntrenamiento("002", "Cardio quema grasas", Nivel.Intermedio, 8, 30);
			//https://www.youtube.com/watch?v=fcN37TxBE_s
			BD.registrarOpcEntrenamiento("003", "Cardio Kick Boxing", Nivel.Experto, 9, 20);
			//https://www.youtube.com/watch?v=Vve4BVTZ0QU
		} 
		catch (clsOpcEntrenamientoRepetida e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	
}
