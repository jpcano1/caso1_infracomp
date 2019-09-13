package source;

/**
 * 
 * @author Juan Pablo Cano - Andres Gonzalez
 *
 */
public class Cliente extends Thread
{
	//-------------------
	// Atributos
	//-------------------
	
	/**
	 * 
	 */
	private int id;

	/**
	 * 
	 */
	private Mensaje[] mensajes;

	/**
	 * 
	 */
	private Buffer buffer;

	//-------------------
	// Contructores
	//-------------------
	
	/**
	 * 
	 * @param pId
	 * @param numeroConsultas
	 * @param pBuffer
	 */
	public Cliente(int pId, int numeroConsultas, Buffer pBuffer)
	{
		id = pId;
		mensajes = new Mensaje[numeroConsultas];
		buffer = pBuffer;
	}

	/**
	 * 
	 * @return
	 */
	public int darId() 
	{
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public Mensaje[] getConsultas()
	{
		return mensajes;
	}

	/**
	 * 
	 * @param consultas
	 */
	public void setConsultas(Mensaje[] consultas) {
		this.mensajes = consultas;
	}

	/**
	 * 
	 */
	public void EscribirMensajes()
	{
		for (int i = 0; i < mensajes.length; i++) {
			mensajes[i] = new Mensaje(id+(i+1), this);
		}
	}

	/**
	 * 
	 * @param mensaje
	 */
	public void enviarMensaje(Mensaje mensaje)
	{
		System.out.println("Se envio el mensaje: " + mensaje.getMensaje() + " por el cliente: " + id);
		buffer.guardarMensaje(mensaje);
	}

	/**
	 * 
	 */
	public void run()
	{
		EscribirMensajes();
		for (int i = 0; i < mensajes.length; i++)
		{
			enviarMensaje(mensajes[i]);
		}
		System.err.print("acabe Att: " + id );
	}
}