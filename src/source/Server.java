package source;

public class Server extends Thread {
    private int serverID;
    private Buffer buffer;

    public Server(int serverID, Buffer buffer) {
        setServerID(serverID);
        setBuffer(buffer);
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

    public void readMessage() {
        Message currentMessage;
        while (true) {
            currentMessage = getBuffer().discardMessage();
            if (currentMessage != null || getBuffer().getNumClients() <= 0) {
                break;
            } else if(currentMessage == null && getBuffer().getNumClients() > 0){
                Thread.yield();
            }
        }
        if (currentMessage != null) {
            System.out.println("[S " + getServerID() + " ] read message [M " + currentMessage.getMessage() + "]");
            currentMessage.setMessage(currentMessage.getMessage() + 1);
            System.out.println("[S " + getServerID() + " ] sent message [M " + currentMessage.getMessage() + "]");
            currentMessage.wakeMessage();
        }
    }

    public void run() {
        while (true) {
            if (getBuffer().getNumClients() > 0) {
                readMessage();
            } else if (getBuffer().getNumClients() <= 0) {
                System.out.println("[S " + getServerID() + " ] has no clients");
                break;
            }
        }
        System.err.println("[S " + getServerID() + " ] finished");
        Main.threadFinished();
        if (!this.isInterrupted()) {
            yield();
            interrupt();
        }
    }
}