package source;

public class Servidor extends Thread
{
	private int id;
	
	private Buffer buffer;

	public Servidor(int pId, Buffer pBuffer)
	{
		id = pId;
		buffer = pBuffer;
	}

	public int darId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void leerMensaje()
	{
		Mensaje leyendo = buffer.soltarMensaje();
		leyendo.setMensaje(leyendo.getMensaje()+id);
		System.out.println(leyendo.getMensaje());
		leyendo.confirmarRespuesta();
	}
	
	public void run()
	{
		while(true)
			leerMensaje();	
	}
}

