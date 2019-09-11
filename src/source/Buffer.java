package source;

import java.util.ArrayList;
import java.util.Queue;

/**
 * 
 * @author Juan Pablo Cano y Andres Gonzalez
 *
 */
public class Buffer 
{
	//---------------------------
	// Atributos
	//--------------------------

	/**
	 * Capacidad de buffer
	 */
	private int capacidad;

	/**
	 * Lista de mensajes
	 */
	private ArrayList<Mensaje> mensajes;

	/**
	 * Contador de mensajes
	 */
	private int cont;

	private Object lleno;

	private Object vacio;

	//---------------------------
	// Metodos
	//---------------------------

	/**
	 * Obtiene el contador de mensajes
	 * @return el contador
	 */
	public int getCont()
	{
		return cont;
	}

	/**
	 * Actualiza el contador
	 * @param cont
	 */
	public void setCont(int cont) 
	{
		this.cont = cont;
	}

	//--------------------------
	// Constructores
	//--------------------------

	/**
	 * Clase constructora
	 * @param pCapacidad
	 * @param pNumero
	 */
	public Buffer(int pCapacidad, int pNumero)
	{
		capacidad = pCapacidad;
		mensajes =  new ArrayList<Mensaje>();
		cont = pNumero;
	}

	/**
	 * Obtiene la capacidad del buffer
	 * @return la capacidad
	 */
	public int getCapacidad() 
	{
		return capacidad;
	}

	/**
	 * Actualiza la capacidad del buffer
	 * @param capacidad 
	 */
	public void setCapacidad(int capacidad) 
	{
		this.capacidad = capacidad;
	}

	/**
	 * Obtener la lista de mensajes.
	 * @return
	 */
	public ArrayList<Mensaje> getMensajes() 
	{
		return mensajes;
	}

	/**
	 * Actualiza la lista de mensajes
	 * @param mensajes
	 */
	public void setMensajes(ArrayList<Mensaje> mensajes)
	{
		this.mensajes = mensajes;
	}

	/**
	 * 
	 * @param mensaje
	 */
	public void guardarMensaje(Mensaje mensaje)
	{
		synchronized(lleno) {
			try
			{
				while(mensajes.size()==capacidad)
				{
					// Este wait pone en espera a los que entraron a la sección crítica
					lleno.wait();
				}

			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		synchronized (this) 
		{
			mensajes.add(mensaje);
			mensaje.dormir();
		}
		synchronized (vacio)
		{
			vacio.notify();
		}
	}

	public  Mensaje soltarMensaje()
	{
		synchronized(vacio) 
		{
			while(mensajes.isEmpty())
			{
				try {
					vacio.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Mensaje i;
		synchronized (this) 
		{
			cont--;
			i = mensajes.remove(0);
		}
		
		synchronized (lleno) {
			lleno.notify();
		}
		
		return i;
	}
}
