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
}
