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
			
			String NClientes = br.readLine().split(":")[2];
			int  numeroClientes = Integer.parseInt(NClientes);
			
			String NServidores = br.readLine().split(":")[2];
			int numeroServidores = Integer.parseInt(NServidores);
			

			String Cclientes = br.readLine().split(":")[2];
			int consultaClientes = Integer.parseInt(Cclientes);
			
			String TBuffer = br.readLine().split(":")[2];
			int TamanioBuffer = Integer.parseInt(TBuffer);
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
