package source;

public class Servidor extends Thread
{
	private int id;

	public Servidor(int pId)
	{
		id = pId;
	}

	public int darId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void leerMensaje()
	{
		Mensaje leyendo = Main.buffer.soltarMensaje(this);
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

