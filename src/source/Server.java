package source;

public class Server extends Thread {
    private int serverID;
    private Buffer buffer;

    public Server(int serverID, Buffer buffer) {
        setServerID(serverID);
        setBuffer(buffer);
//        this.serverID = serverID;
//        this.buffer = buffer;
    }

    public Buffer getBuffer() {
        return buffer;
    }

    public void setBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    public int getServerID() {
        return serverID;
    }

    public void setServerID(int id) {
        this.serverID = id;
    }

    public void leerMensaje() {
        Message leyendo;
        while (true) {
            leyendo = buffer.discardMessage();
            if (leyendo != null || buffer.getNumClients() <= 0) {
                break;
            } else if (leyendo == null) {
                Thread.yield();
            }
        }
        if (leyendo != null) {
            System.out.println("Mensaje leido: " + leyendo.getMessage() + " por servidor: " + serverID);
            leyendo.setMessage(leyendo.getMessage() + 1);
            System.out.println("Mensaje enviado: " + leyendo.getMessage() + " por servidor: " + serverID);
            leyendo.wakeMessage();
        }
    }

    public void run() {
        while (true) {
            if (buffer.getNumClients() > 0) {
                leerMensaje();
            } else if (buffer.getNumClients() < 1) {
                System.out.println("No hay clientes disponibles, reportó: " + serverID);
                break;
            }
        }
        System.err.println("\nEl servidor: " + serverID + " termino");
    }
}