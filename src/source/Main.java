package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class Main
{
	private static String archivoDatos = "./data/DatosCaso1.txt";
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


			String tBuffer = br.readLine().split(":")[1];
			int tamanioBuffer = Integer.parseInt(tBuffer);

			buffer = new Buffer(tamanioBuffer);
			String linea= br.readLine() ;
			
			for(int i = 0; i < numeroServidores; i++)
			{
				servidores[i] = new Servidor((i+1)*100);
				servidores[i].start();
			}
			
			while(linea !=null)
			{	
				int numCliente = Integer.valueOf(linea.split(":")[1]);
				clientes[numCliente-1] = new Cliente((numCliente+1)*1000, Integer.valueOf(linea.split(":")[2]));
				clientes[numCliente-1].start();
				linea = br.readLine();
			}
			br.close();
			fr.close();
			
			
		
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
