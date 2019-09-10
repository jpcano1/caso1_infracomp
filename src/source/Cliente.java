package source;

public class Cliente extends Thread
{
	private int id;
	
	private Mensaje[] consultas;

	public Cliente(int pId, int numeroConsultas)
	{
		id = pId;
		consultas = new Mensaje[numeroConsultas];
	}

	public int darId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public Mensaje[] getConsultas()
	{
		return consultas;
	}

	public void setConsultas(Mensaje[] consultas) {
		this.consultas = consultas;
	}
	
	public void EscribirMensajes()
	{
		for (int i = 0; i < consultas.length; i++) {
			consultas[i] = new Mensaje(id+(i+1), this);
		}
	}
	
	public void enviarMensaje(Mensaje mensaje)
	{
		Main.buffer.guardarMensaje(mensaje);
	}
	
	public void run()
	{
		EscribirMensajes();
		for (int i = 0; i < consultas.length; i++)
		{
			System.err.println(consultas[i].getMensaje());
			enviarMensaje(consultas[i]);
		}
	}
	 
}