package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

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
			Cliente[] clientes = new Cliente[numeroClientes];

			String nServidores = br.readLine().split(":")[1];
			int numeroServidores = Integer.parseInt(nServidores);
			Servidor[] servidores = new Servidor[numeroServidores];

			String cClientes = br.readLine().split(":")[1];
			int nConsultaClientes = Integer.parseInt(cClientes);

			String tBuffer = br.readLine().split(":")[1];
			int tamanioBuffer = Integer.parseInt(tBuffer);

			for(int i = 0; i < numeroClientes; i++)
			{
				clientes[i] = new Cliente((i+1)*1000, nConsultaClientes);
			}

			for(int i = 0; i < numeroServidores; i++)
			{
				servidores[i] = new Servidor((i+1)*100);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
