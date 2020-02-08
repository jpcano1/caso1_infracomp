package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Juan Pablo Cano - Nicolas Esteban Cárdenas - Ryan Bosher
 */
public class Main
{
	private static String archivoDatos = "./data/DatosCaso1.txt";
	public static Buffer buffer;
	public static void main(String[] args)
	{
		try
		{
			FileReader fr = new FileReader(new File(archivoDatos));
			BufferedReader br = new BufferedReader(fr);

			int  numeroClientes = Integer.parseInt(br.readLine().split(":")[1]);
			Cliente[] clientes = new Cliente[numeroClientes];
			
			System.out.println("El numero de clientes es: " + numeroClientes);

			int numeroServidores = Integer.parseInt(br.readLine().split(":")[1]);
			Servidor[] servidores = new Servidor[numeroServidores];
			
			System.out.println("El numero de servidores es: " + numeroServidores);

			int tamanioBuffer = Integer.parseInt(br.readLine().split(":")[1]);
			
			System.out.println("El tamano del buffer es: " + tamanioBuffer);
			
			buffer = new Buffer(tamanioBuffer, numeroClientes);
			
			for(int i = 0; i < numeroServidores; i++)
			{
				servidores[i] = new Servidor((i+1)*100, buffer);
			}
			
			String linea= br.readLine();
			
			while(linea !=null)
			{
				int numeroCliente = Integer.parseInt(linea.split(":")[1]);
				clientes[numeroCliente-1] = new Cliente((numeroCliente)*1000, Integer.parseInt(linea.split(":")[2]), buffer);
				System.out.println("El numero del cliente creado es:  " + numeroCliente);
				linea = br.readLine();
			}

			for(int i = 0; i < numeroClientes; i++)
			{
				clientes[i].start();
				System.out.println("Cliente " + (i+1) + " iniciado.");
			}

			for(int i = 0; i < numeroServidores; i++)
			{
				servidores[i].start();
				System.out.println("Servidor: " + (i+1) + " iniciado. ");
			}
			
			br.close();
			fr.close();
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
		}
	}
}
