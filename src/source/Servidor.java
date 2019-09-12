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
		System.out.println("Mensaje leido: " + leyendo.getMensaje() + " por servidor: " + id);
		leyendo.setMensaje(leyendo.getMensaje() + 1);
		leyendo.despertar();
		System.out.println("Mensaje enviado: " + leyendo.getMensaje() + " por servidor: " + id);
	}

	public void run()
	{
		while(buffer.getCont() > 0)
		{
//			if(buffer.getMensajes().isEmpty())
//			{
//				yield();
//			}
			leerMensaje();
		}
	}
}

