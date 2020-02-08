package source;

/**
 *
 * @author Juan Pablo Cano - Nicolas Esteban Cárdenas - Ryan Bosher
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

	public boolean full()
	{
		return mensajes.size() == capacidad;
	}

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
	public boolean guardarMensaje(Mensaje mensaje)
	{
		boolean permiso = true;
		synchronized(lleno)
		{
			if(mensajes.size() == capacidad)
			{
				System.out.println("No hay espacio para almacenar mensajes");
				permiso =  false;
			}
		}
		synchronized (this)
		{
			mensajes.enqueue(mensaje);
			System.out.println("Almacenado");
		}
		return permiso;
	}

	/**
	 * Retorna el primer mensaje de la cola mientras no este vacio
	 * @return un mensaje que representa el primero de la cola
	 */
	public Mensaje soltarMensaje()
	{
		Mensaje i = null;
//		synchronized(vacio)
//		{
//			if(mensajes.isEmpty())
//			{
//				i = null ;
//			}
//		}
		synchronized(this)
		{
			if(!mensajes.isEmpty())
			{
				i = mensajes.dequeue();
				if(i.getCliente().getNumMensajes() == 0)
				{
					numClientes--;
					System.err.println("El cliente: " + i.getCliente().getId() + " no tiene mas consultas");
					if(numClientes <= 0)
					{
						System.err.println("No hay clientes");
					}
				}
				System.out.println("Mensaje enviado al servidor");
			}
		}
		return i;
	}
}