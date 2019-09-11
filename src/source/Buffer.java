package source;

import java.util.ArrayList;

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
	
	//---------------------------
	// Metodos
	//---------------------------
	
	/**
	 * Obtiene el contador de mensajes
	 * @return el contador
	 */
	public int getCont() {
		return cont;
	}

	/**
	 * Actualiza el contador
	 * @param cont
	 */
	public void setCont(int cont) {
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
