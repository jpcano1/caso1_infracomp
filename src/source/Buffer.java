package source;

import java.util.ArrayList;

public class Buffer 
{
	private int capacidad;

	private ArrayList<Mensaje> mensajes;
	
	private int cont;
	
	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	public Buffer(int pCapacidad, int pNumero)
	{
		capacidad = pCapacidad;
		mensajes =  new ArrayList<Mensaje>();
		cont = pNumero;
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

	public void guardarMensaje(Mensaje mensaje)
	{
		synchronized(mensaje.getCliente()) {
			try
			{
				while(mensajes.size()>=capacidad)
				{
					mensaje.getCliente().wait();
				}
				mensajes.add(mensaje);
				
				mensaje.esperarRespuesta();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public  Mensaje soltarMensaje(Servidor sever)
	{
		synchronized(sever) 
		{
			
				while(mensajes.isEmpty())
				{	
					sever.yield();
				}
				cont--;
			
			return mensajes.remove(0);
		}
	}



}
