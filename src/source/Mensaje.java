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

	public int getMensaje() 
	{
		return mensaje;
	}

	public void setMensaje(int mensaje) 
	{
		this.mensaje = mensaje;
	}

	public Cliente getCliente() 
	{
		return cliente;
	}

	public void setCliente(Cliente cliente) 
	{
		this.cliente = cliente;
	}

	public void dormir()
	{
		try
		{
			System.out.println("El mensaje: " + mensaje + " está en espera.");
			wait();
		}
		catch (Exception e)
		{
			System.err.println("\nEste es el error: " + (e.getMessage() != null? e.getMessage(): e) + "\n y viene del mensaje: " + mensaje);
		}
	}

	public void despertar()
	{
		System.out.println("El mensaje: " + mensaje + " despertó");
		notify();
	}

	//	public  void esperarRespuesta() 
	//	{
	//
	//		synchronized(cliente)
	//		{
	//			try 
	//			{
	//				cliente.wait();
	//			} catch (Exception e) 
	//			{
	//				System.err.println(e.getMessage());
	//			}
	//		}
	//	}

	//	public  void confirmarRespuesta()
	//	{
	//		synchronized(cliente)
	//		{
	//			cliente.notify();
	//		}
	//	}
}