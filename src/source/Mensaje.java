package source;

public class Mensaje
{
	private int mensaje;
	private Cliente cliente;
	public Mensaje(int pMensaje, Cliente pCliente)
	{
		mensaje = pMensaje;
		cliente = pCliente;
	}

	public int getMensaje() {
		return mensaje;
	}

	public void setMensaje(int mensaje) {
		this.mensaje = mensaje;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void dormir()
	{
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void despertar()
	{
		this.notify();
	}

	public  void esperarRespuesta() 
	{

		synchronized(cliente)
		{
			try 
			{
				cliente.wait();
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public  void confirmarRespuesta() 
	{
		synchronized(cliente)
		{
			cliente.notify();
		}
	}
}