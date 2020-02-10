package source;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        try {

            final BufferedReader bufferedReader = new BufferedReader(new FileReader(
                    new File("./data/settings.txt")));

            final int numClients = Integer.parseInt(bufferedReader.readLine().split(":")[1]);
            final int numServers = Integer.parseInt(bufferedReader.readLine().split(":")[1]);
            final int bufferSize = Integer.parseInt(bufferedReader.readLine().split(":")[1]);

            final Client[] clientArray = new Client[numClients];
            final Server[] serverArray = new Server[numServers];

            final Buffer buffer = new Buffer(bufferSize, numClients);

            System.out.println("Number of clients :" + numClients);
            System.out.println("Number of servers :" + numServers);
            System.out.println("Buffer size       :" + bufferSize + "\n");

            for (int i = 0; i < numServers; i++) {
                serverArray[i] = new Server((i + 1) * 100, buffer);
            }

            String readLine = bufferedReader.readLine();

            while (readLine != null) {
                int clientNumber = Integer.parseInt(readLine.split(":")[1]);
                clientArray[clientNumber - 1] = new Client((clientNumber) * 1000,
                        Integer.parseInt(readLine.split(":")[2]), buffer);
                readLine = bufferedReader.readLine();
            }

            for (int i = 0; i < numClients; i++) {
                clientArray[i].start();
                System.out.println("[C " + clientArray[i].getClientID() + "] started");
            }

            for (int i = 0; i < numServers; i++) {
                serverArray[i].start();
                System.out.println("[S " + serverArray[i].getServerID() + " ] started");

            }

            bufferedReader.close();
        } catch (Exception e) {
            System.err.println("An error occurred while attempting to read file: " + e.getMessage());
        }
    }
}
