package source;

/**
 * @author Juan Pablo Cano - Nicolas Esteban Cárdenas - Ryan Bosher
 */
public class Servidor extends Thread
{
	//--------------------
	// Atributos
	//--------------------

	/**
	 * El id con el que se identifica el servidor
	 */
	private int id;

	/**
	 * El buffer de donde el servidor recibe los mensajes
	 */
	private Buffer buffer;

	//--------------------
	// Constructores
	//--------------------

	/**
	 * Constructor
	 * @param pId El id con el que se creará un nuevo
	 * servidor
	 * @param pBuffer
	 */
	public Servidor(int pId, Buffer pBuffer)
	{
		id = pId;
		buffer = pBuffer;
	}

	//--------------------
	// Metodos
	//--------------------

	/**
	 * Entrega el id del servidor
	 * @return el id del servidor
	 */
	public int darId() {
		return id;
	}

	/**
	 * Actualiza el id del servidor
	 * @param id el nuevo id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Metodo que lee el mensaje liberado por el
	 * buffer, lo actualiza y despierta el mensaje
	 */
	public void leerMensaje()
	{
	    Mensaje leyendo = buffer.soltarMensaje();
	    while(leyendo == null)
	    {
	        yield();
	        leyendo = buffer.soltarMensaje();
	    }
		System.out.println("Mensaje leido: " + leyendo.getMensaje() + " por servidor: " + id);
		leyendo.setMensaje(leyendo.getMensaje() + 1);
		System.out.println("Mensaje enviado: " + leyendo.getMensaje() + " por servidor: " + id);
		leyendo.despertar();
	}

	/**
	 * Metodo run del thread
	 */
	public void run()
	{
	    leerMensaje();
		System.err.println("\nEl servidor: " + id + " termino");
	}
}

