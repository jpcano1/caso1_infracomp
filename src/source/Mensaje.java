package source;

/**
 * 
 * @author Juan Pablo Cano - Nicolas Esteban Cárdenas - Ryan Bosher
 *
 */
public class Mensaje
{
	//---------------------
	// Atributos
	//---------------------

	/**
	 * Contenido del mensaje
	 */
	private int mensaje;

	/**
	 * Cliente que envia el mensaje
	 */
	private Cliente cliente;

	//---------------------
	// Constructor
	//---------------------
	
	/**
	 * Constructor
	 * @param pMensaje Contenido del mensaje
	 * @param pCliente Cliente de donde proviene el mensaje
	 */
	public Mensaje(int pMensaje, Cliente pCliente)
	{
		mensaje = pMensaje;
		cliente = pCliente;
	}

	//---------------------
	// Metodos
	//---------------------

	/**
	 * Retorna el contenido del mensaje
	 * @return El mensaje
	 */
	public int getMensaje() 
	{
		return mensaje;
	}

	/**
	 * Actualiza el contenido del mensaje
	 * @param mensaje el mensaje que sera actualizado
	 */
	public void setMensaje(int mensaje) 
	{
		this.mensaje = mensaje;
	}

	/**
	 * Retorna el cliente que envio el mensaje
	 * @return El cliente
	 */
	public Cliente getCliente() 
	{
		return cliente;
	}

	/**
	 * Actualiza el cliente del mensaje
	 * @param cliente el cliente que se actualizara
	 */
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}

	/**
	 * Metodo que duerme el mensaje
	 */
	public synchronized void dormir()
	{
		try
		{
			System.out.println("\nEl mensaje: " + mensaje + " esta en espera.\n");
			wait();
		}
		catch (Exception e)
		{
			System.err.println("\nEste es el error: " + (e.getMessage() != null? e.getMessage(): e) + "\n y viene del mensaje: " + mensaje);
		}
	}
	
	/**
	 * Metodo que despierta el mensaje
	 */
	public synchronized void despertar()
	{
		System.out.println("\nEl mensaje: " + mensaje + " desperto\n");
		notify();
	}
}