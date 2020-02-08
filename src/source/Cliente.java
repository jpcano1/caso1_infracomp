package source;

/**
 * 
 * @author Juan Pablo Cano - Nicolas Esteban Cárdenas - Ryan Bosher
 *
 */
public class Cliente extends Thread
{
	//-------------------
	// Atributos
	//-------------------
	
	/**
	 * Identificador del cliente
	 */
	private int id;

	/**
	 * Cola de mensajes del cliente
	 */
	private Queue<Mensaje> mensajes;

	/**
	 * Numero total de consultas de un cliente
	 */
	private int numMensajes;

	/**
	 * Buffer con el cual el cliente se comunica y envia mensajes
	 */
	private Buffer buffer;

	//-------------------
	// Contructores
	//-------------------
	
	/**
	 * Constructor
	 * @param pId Id con el que se creara el Cliente
	 * @param numeroConsultas numero total de consultas
	 * @param pBuffer buffer de comunicacion
	 */
	public Cliente(int pId, int numeroConsultas, Buffer pBuffer)
	{
		id = pId;
		buffer = pBuffer;
		setMensajes(new Queue<Mensaje>());
		numMensajes = numeroConsultas;
	}

	//-------------
	// Metodos
	//-------------

	/**
	 * Retorna la cola de mensajes
	 * @return la cola de mensajes
	 */
	public Queue<Mensaje> getMensajes()
	{
		return mensajes;
	}

	public int getNumMensajes()
	{
		return mensajes.size();
	}

	/**
	 * Actualiza la cola de mensajes con una nueva cola.
	 * @param mensajes La cola nueva
	 */
	public void setMensajes(Queue<Mensaje> mensajes)
	{
		this.mensajes = mensajes;
	}

	/**
	 * Retorna el identificador del cliente
	 * @return el ID
	 */
	public int darId() 
	{
		return id;
	}

	/**
	 * Actualiza el identificador del cliente
	 * @param id el id nuevo.
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Metodo que crea los mensajes y los almacena en la cola
	 */
	public void EscribirMensajes()
	{
		for(int i = 0; i < numMensajes; i++)
		{
			Mensaje aux = new Mensaje(id+(i+1), this);
			mensajes.enqueue(aux);
		}
	}

	/**
	 * Método que envía el mensaje al servidor a través del buffer
	 * @param mensaje el mensaje que será envíado
	 */
	public void enviarMensaje(Mensaje mensaje)
	{
		boolean permiso;
		while(true)
		{
			System.out.println("Intentado guardar mensaje");
			permiso = buffer.guardarMensaje(mensaje);
			if(permiso)
			{
				System.out.println("Se pueden agregar mensajes");
				break;
			}
			else
			{
				Thread.yield();
			}
		}
		System.out.println("Se envio el mensaje: " + mensaje.getMensaje() + " por el cliente: " + id);
		mensaje.dormir();
	}

	/**
	 * El método run del thread.
	 */
	public void run()
	{
		EscribirMensajes();
		for(int i = 0; i < numMensajes; i++)
		{
			enviarMensaje(this.mensajes.dequeue());
		}
		System.err.print("\nAcabe Att: " + id  + "\n");
	}
}