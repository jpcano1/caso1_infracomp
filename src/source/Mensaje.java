package source;

/**
 * 
 * @author Juan Pablo Cano - Adnres Gonzalez
 *
 */
public class Mensaje
{
	//---------------------
	// Atributos
	//---------------------
	private int mensaje;
	private Cliente cliente;

	//---------------------
	// Constructor
	//---------------------
	
	/**
	 * 
	 * @param pMensaje
	 * @param pCliente
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
	 * 
	 * @return
	 */
	public int getMensaje() 
	{
		return mensaje;
	}

	/**
	 * 
	 * @param mensaje
	 */
	public void setMensaje(int mensaje) 
	{
		this.mensaje = mensaje;
	}

	/**
	 * 
	 * @return
	 */
	public Cliente getCliente() 
	{
		return cliente;
	}

	/**
	 * 
	 * @param cliente
	 */
	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}

	/**
	 * 
	 */
	public synchronized void dormir()
	{
		try
		{
			System.out.println("\nEl mensaje: " + mensaje + " est� en espera.\n");
			wait();
		}
		catch (Exception e)
		{
			System.err.println("\nEste es el error: " + (e.getMessage() != null? e.getMessage(): e) + "\n y viene del mensaje: " + mensaje);
		}
	}
	
	/**
	 * 
	 */
	public synchronized void despertar()
	{
		System.out.println("\nEl mensaje: " + mensaje + " desperto\n");
		notify();
	}
}