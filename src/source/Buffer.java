package source;

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
	private Queue<Mensaje> mensajes;

	/**
	 * Contador de clientes
	 */
	private int numClientes;

	/**
	 * Son lo objectos sobre los cuales se implementa el 
	 * semaforo
	 */
	private Object lleno, vacio;

	//--------------------------
	// Constructores
	//--------------------------

	/**
	 * Clase constructora
	 * @param pCapacidad capacidad del buffer
	 * @param pNumeroClientes numero de clientes totales
	 */
	public Buffer(int pCapacidad, int pNumeroClientes)
	{
		capacidad = pCapacidad;
		mensajes = new Queue<Mensaje>();
		numClientes = pNumeroClientes;
		lleno = new Object();
		vacio = new Object();
	}

	//---------------------------
	// Metodos
	//---------------------------

	/**
	 * Obtiene el contador de mensajes
	 * @return el contador
	 */
	public int getCont()
	{
		return numClientes;
	}

	/**
	 * Actualiza el contador
	 * @param cont
	 */
	public void setCont(int cont)
	{
		this.numClientes = cont;
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
	 * Obtener la cola de mensajes.
	 * @return la cola de mensajes
	 */
	public Queue<Mensaje> getMensajes()
	{
		return mensajes;
	}

	/**
	 * Actualiza la cola de mensajes
	 * @param mensajes la cola de mensajes
	 */
	public void setMensajes(Queue<Mensaje> mensajes)
	{
		this.mensajes = mensajes;
	}

	/**
	 * Metodo que guarda un mensaje en el buffer,
	 * se esta implementando un semaforo
	 * @param mensaje el mensaje a almacenar
	 */
	public void guardarMensaje(Mensaje mensaje)
	{
		synchronized(lleno)
		{
			while(mensajes.size() == capacidad)
			{
				// Este wait pone en espera al objeto usado en la seccion critica
				try
				{
					lleno.wait();
				}
				catch (Exception e)
				{
					System.err.println("Este es el error: " + (e.getMessage() != null? e.getMessage(): e));
				}
			}
		}
		synchronized (vacio)
		{
			mensajes.enqueue(mensaje);
			vacio.notify();
		}
		mensaje.dormir();
	}

	/**
	 * Retorna el primer mensaje de la cola mientras no este vacio
	 * @return un mensaje que representa el primero de la cola
	 */
	public  Mensaje soltarMensaje()
	{
		synchronized(vacio)
		{
			while(mensajes.isEmpty())
			{
				try
				{
					vacio.wait();
				}
				catch (Exception e)
				{
					System.out.println("Este es el error: " + (e.getMessage() != null? e.getMessage(): e) + " en el buffer");
				}
			}
		}
		Mensaje i;
		synchronized (this)
		{
			i = mensajes.dequeue();
			if(i.getCliente().getMensajes().isEmpty())
			{
				numClientes--;
				System.err.println("\nEl cliente: " + i.getCliente().getId() + " no tiene más consultas\n");
			}
		}
		synchronized (lleno)
		{
			lleno.notify();
		}
		return i;
	}
}
