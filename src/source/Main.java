package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main
{

	private static String archivoDatos = ".data/DatosCaso1.txt";
	public static Buffer buffer;
	public static void main(String[] args) throws FileNotFoundException 
	{
		try 
		{
			FileReader fr = new FileReader(new File(archivoDatos));
			BufferedReader br = new BufferedReader(fr);
			
			String nClientes = br.readLine().split(":")[1];
			int  numeroClientes = Integer.parseInt(nClientes);
			
			String nServidores = br.readLine().split(":")[1];
			int numeroServidores = Integer.parseInt(nServidores);
			

			String cClientes = br.readLine().split(":")[1];
			int consultaClientes = Integer.parseInt(cClientes);
			
			String tBuffer = br.readLine().split(":")[1];
			int tamanioBuffer = Integer.parseInt(tBuffer);
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
