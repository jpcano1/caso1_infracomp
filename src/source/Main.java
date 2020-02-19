package source;

import java.io.FileReader;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("./data/config_file.properties");
            Properties p = new Properties();
            p.load(reader);

            final int numClients = Integer.parseInt(p.getProperty("clientes"));
            final int numServers = Integer.parseInt(p.getProperty("servidores"));
            final int bufferSize = Integer.parseInt(p.getProperty("tam_buffer"));
            final int numMensajes = Integer.parseInt(p.getProperty("mensajes"));

            final Client[] clientArray = new Client[numClients];
            final Server[] serverArray = new Server[numServers];

            final Buffer buffer = new Buffer(bufferSize, numClients);

            System.out.println("Number of clients :" + numClients);
            System.out.println("Number of servers :" + numServers);
            System.out.println("Buffer size       :" + bufferSize);
            System.out.println("____________________");

            for (int i = 0; i < numServers; i++) {
                serverArray[i] = new Server((i + 1), buffer);
            }

            for(int i = 0; i < numClients; i++)
            {
                clientArray[i] = new Client((i+1), numMensajes, buffer);
            }
            
            System.out.println(messages);

            for (int i = 0; i < numClients; i++) {
                clientArray[i].start();
                System.out.println("[C " + clientArray[i].getClientID() + "] started");
            }

            for (int i = 0; i < numServers; i++) {
                serverArray[i].start();
                System.out.println("[S " + serverArray[i].getServerID() + " ] started");
            }
        } catch (Exception e) {
            System.err.println("An error occurred while attempting to read file: " + e);
        }
    }
}
