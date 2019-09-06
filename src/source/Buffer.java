package source;

public class Buffer 
{
	private int capacidad;
	
	private Mensaje[] mensajes;
	
	public Buffer(int pCapacidad)
	{
		capacidad = pCapacidad;
		mensajes = new Mensaje[pCapacidad];
	}

	public int getCapacidad() 
	{
		return capacidad;
	}

	public void setCapacidad(int capacidad) 
	{
		this.capacidad = capacidad;
	}

	public Mensaje[] getMensajes() 
	{
		return mensajes;
	}

	public void setMensajes(Mensaje[] mensajes)
	{
		this.mensajes = mensajes;
	}
	
	public void guardarMensaje()
	{
		
	}
	
	public void soltarMensaje()
	{
		
	}
	
}
