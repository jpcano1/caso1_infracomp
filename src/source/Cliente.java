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
}