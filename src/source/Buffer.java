package source;

import java.util.ArrayList;

public class Buffer 
{
	private int capacidad;

	private ArrayList<Mensaje> mensajes;

	public Buffer(int pCapacidad)
	{
		capacidad = pCapacidad;
		mensajes =  new ArrayList<Mensaje>();

	}

	public int getCapacidad() 
	{
		return capacidad;
	}

	public void setCapacidad(int capacidad) 
	{
		this.capacidad = capacidad;
	}

	public ArrayList<Mensaje> getMensajes() 
	{
		return mensajes;
	}

	public void setMensajes(ArrayList<Mensaje> mensajes)
	{
		this.mensajes = mensajes;
	}

	public  synchronized  void guardarMensaje(Mensaje mensaje)
	{
		try
		{
			while(mensajes.size()>=capacidad)
			{

				mensaje.getCliente().wait();
			}
			mensajes.add(mensaje);
			mensaje.esperarRespuesta();
			notify();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized Mensaje soltarMensaje()
	{
		try 
		{
			while(mensajes.isEmpty())
			{	
				wait();
			}
			
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mensajes.remove(0);
	}
	
	

}
